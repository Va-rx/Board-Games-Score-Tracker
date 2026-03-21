package com.boardgames.hub.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {
    @NotBlank(message = "Email can't be empty.")
    @Email(message = "Provide a correct email format.")
    private String email;
    @NotBlank(message = "Nickname can't be empty.")
    @Size(min = 3, max = 20, message = "Nickname must have from 3 to 20 characters.")
    private String nickname;
    @NotBlank(message = "Password can't be empty.")
    @Size(min = 8, max = 72, message = "Password must have from 8 to 20 characters.")
    private String password;
}
