package andrea.lurny.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterDTO(

        @NotBlank(message = "First name is required")
        @Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
        String firstName,

        @NotBlank(message = "Last name is required")
        @Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
        String lastName,

        @NotBlank(message = "Username is required")
        @Size(min = 2, max = 20, message = "Username must be between 2 and 20 characters")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is invalid")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 6, max = 15, message = "Password must be at least 6 characters long")
        String password,

        String avatar

) {}
