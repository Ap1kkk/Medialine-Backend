package ru.medialine.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCredentialsDto {
    private Long id;
    private String email;
    private String password;
}
