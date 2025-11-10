package andrea.lurny.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserDTO(@NotBlank(message = "Please type your username")
                      String username,
                      @NotBlank(message = "You must add an email")
                      @Email(message = "Add a valid email")
                      String email,
                      @NotBlank(message = "You must add a password")
                      @Size(min = 6, message = "The password must have at least 6 characters")
                      @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).{6,}$\n", message = "The password must contain at least a capital letter and a number")//PASSWORD CON ALMENO UNA LETTERA MAIUSCOLA E UN NUMERO
                      String password,
                      @NotBlank(message = "Please add your name")
                      @Size(min = 2, max = 30, message = "The name must contain a minimum of 2 characters")
                      String firstName,
                      @NotBlank(message = "Please add your surname")
                      @Size(min = 2, max = 30, message = "The surname must contain a minimum of 2 characters")
                      String lastName) {


}
