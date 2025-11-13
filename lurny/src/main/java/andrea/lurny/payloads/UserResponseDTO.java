package andrea.lurny.payloads;

import java.util.UUID;

public record UserResponseDTO(
        UUID id,
        String firstName,
        String lastName,
        String username,
        String email
) {}

