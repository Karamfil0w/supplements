package com.example.supplements.model.dtos;

import com.example.supplements.validations.matchingPasswords.PasswordMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@PasswordMatch(password = "password", confirmPassword = "confirmPassword")
public class UserRegistrationDto {
    @Size(min = 2, max = 20, message = "Username must be at least 3 letters long")
    @NotBlank
    private String username;
    @Email(message = "Please enter a valid email address")
    @NotBlank
    private String email;
    @Size(min = 4, message = "Password must be at least 4 digits long")
    @NotBlank
    private String password;
    @Size(min = 4, message = "Password must match")
    @NotBlank
    private String confirmPassword;

    public UserRegistrationDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
