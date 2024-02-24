package ru.medialine.controller.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.medialine.dto.CredentialsDto;
import ru.medialine.dto.UpdateCredentialsDto;
import ru.medialine.model.User;
import ru.medialine.service.AdminService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/superadmin")
@RequiredArgsConstructor
public class SuperAdminController {
    private final AdminService adminService;

    @GetMapping()
    public List<User> getAllUsers() {
        return adminService.getAllUsers();
    }

    @PostMapping()
    public User addAdmin(@ModelAttribute CredentialsDto credentials) {
        return adminService.addAdmin(credentials);
    }

    @PatchMapping()
    public User updateAdmin(@ModelAttribute UpdateCredentialsDto credentialsDto) {
        return adminService.updateAdmin(credentialsDto);
    }

    @DeleteMapping()
    public void deleteAdmin(@ModelAttribute String email) {
        adminService.deleteAdmin(email);
    }
}
