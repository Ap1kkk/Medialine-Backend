package ru.medialine.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.medialine.dto.CredentialsDto;
import ru.medialine.dto.UpdateCredentialsDto;
import ru.medialine.exception.database.AlreadyExistException;
import ru.medialine.exception.database.DatabaseException;
import ru.medialine.exception.database.EntityNotFoundException;
import ru.medialine.model.User;
import ru.medialine.model.enums.Role;
import ru.medialine.model.enums.UserStatus;
import ru.medialine.repository.UserRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public User tryGetById(Long id) throws EntityNotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find user by id " + id));
    }


    public User tryGetByEmail(String email) throws EntityNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null)
            throw new EntityNotFoundException("Unable to find user by email " + email);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addAdmin(CredentialsDto credentials) throws AlreadyExistException {
        if(userRepository.findByEmail(credentials.getEmail()) != null)
            throw new AlreadyExistException("Cannot create admin. Email " + credentials.getEmail() + " already in use");

        User admin = new User();
        admin.setEmail(credentials.getEmail());
        admin.setPassword(passwordEncoder.encode(credentials.getPassword()));
        admin.setRole(Role.ADMIN);
        admin.setStatus(UserStatus.ACTIVE);

        return userRepository.save(admin);
    }

    public User updateAdmin(UpdateCredentialsDto credentials) throws EntityNotFoundException {
        User user = tryGetByEmail(credentials.getOldEmail());

        user.setEmail(credentials.getNewEmail());
        user.setPassword(credentials.getPassword());

        return userRepository.save(user);
    }

    public void deleteAdmin(String email) throws EntityNotFoundException {
        User user = tryGetByEmail(email);
        if(user.getRole() == Role.SUPER_ADMIN) {
            throw new DatabaseException("Unable to delete super admin");
        }
        userRepository.delete(user);
    }
}
