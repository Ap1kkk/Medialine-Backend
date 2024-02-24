package ru.medialine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCredentialsDto {
    private String oldEmail;
    private String newEmail;
    private String password;
}
