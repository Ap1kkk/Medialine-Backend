package ru.medialine.service;

import ru.medialine.model.User;
import ru.medialine.security.UserSecurity;
import ru.medialine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service("userServiceImpl")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailWithStatusActive(email);
        if (user == null) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
        return UserSecurity.fromUser(user);
    }
}
