package com.devcareerhub.user_service.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "First Name is required")
        @Size(max = 100)
        String firstName,

        @NotBlank(message = "Last Name is required")
        @Size(max = 100)
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @Pattern(
                regexp = "^\\+?[0-9]{10,15}$",
                message = "Invalid phone number"
        )
        String phone) {
}
