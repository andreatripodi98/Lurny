package andrea.lurny.payloads;

public record UserUpdateDTO(
        String firstName,
        String lastName,
        String username,
        String email,
        String avatar
) {}

