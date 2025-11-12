package andrea.lurny.payloads;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(@NotBlank(message = "Please add a valid username") String username,
                       @NotBlank(message = "The password is not correct") String password) {
}
