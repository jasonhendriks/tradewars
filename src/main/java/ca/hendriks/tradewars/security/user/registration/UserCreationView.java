package ca.hendriks.tradewars.security.user.registration;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@PasswordMatches
record UserCreationView(
        String id,
        @Email
        @NotEmpty(message = "Email is required.")
        String email,
        @NotEmpty(message = "Password is required.")
        String password,
        @NotEmpty(message = "Password Confirmation is required.")
        String passwordConfirmation) {
}
