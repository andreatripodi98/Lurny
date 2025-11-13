package andrea.lurny.services;

import andrea.lurny.entities.User;
import andrea.lurny.exceptions.AlreadyExistingException;
import andrea.lurny.exceptions.NotFoundException;
import andrea.lurny.exceptions.UnauthorizedException;
import andrea.lurny.payloads.UserLoginDTO;
import andrea.lurny.payloads.UserRegisterDTO;
import andrea.lurny.payloads.UserResponseDTO;
import andrea.lurny.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;


    private UserResponseDTO map(User u) {
        return new UserResponseDTO(
                u.getId(),
                u.getFirstName(),
                u.getLastName(),
                u.getUsername(),
                u.getEmail()
        );
    }
    public User findEntityById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }
    public User findEntityByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    // Get user by ID
    public UserResponseDTO findById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return map(user);
    }

    // Update user
    public UserResponseDTO update(UUID id, UserRegisterDTO body) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        user.setFirstName(body.firstName());
        user.setLastName(body.lastName());
        user.setUsername(body.username());
        user.setEmail(body.email());
        user.setPassword(bcrypt.encode(body.password()));

        return map(userRepository.save(user));
    }

    // Delete user
    public void delete(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        userRepository.delete(user);
    }

    // Register new user
    public UserResponseDTO register(UserRegisterDTO body) {
        if (userRepository.existsByUsername(body.username())) {
            throw new AlreadyExistingException("Username already in use");
        }

        if (userRepository.existsByEmail(body.email())) {
            throw new AlreadyExistingException("Email already in use");
        }

        User u = new User();
        u.setFirstName(body.firstName());
        u.setLastName(body.lastName());
        u.setUsername(body.username());
        u.setEmail(body.email());
        u.setPassword(bcrypt.encode(body.password()));

        return map(userRepository.save(u));
    }

    // Login
    public User login(UserLoginDTO body) {
        User user = userRepository.findByUsername(body.username())
                .orElseThrow(() -> new NotFoundException("User not found"));

        if (!bcrypt.matches(body.password(), user.getPassword())) {
            throw new UnauthorizedException("Invalid credentials");
        }

        return user;
    }
}
