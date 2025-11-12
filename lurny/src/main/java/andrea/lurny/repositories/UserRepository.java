package andrea.lurny.repositories;

import andrea.lurny.entities.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(@NotBlank(message = "This username is not available") String username);

    boolean existsByEmail(@NotBlank(message = "You must add an email") @Email(message = "Add a valid email") String email);

    Optional<User> findByUsername(String username);
}
