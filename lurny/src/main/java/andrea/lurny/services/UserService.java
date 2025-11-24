package andrea.lurny.services;

import andrea.lurny.entities.User;
import andrea.lurny.exceptions.AlreadyExistingException;
import andrea.lurny.exceptions.NotFoundException;
import andrea.lurny.exceptions.UnauthorizedException;
import andrea.lurny.payloads.UserLoginDTO;
import andrea.lurny.payloads.UserRegisterDTO;
import andrea.lurny.payloads.UserResponseDTO;
import andrea.lurny.payloads.UserUpdateDTO;
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
                u.getEmail(),
                u.getAvatar()
        );
    }

    public User findEntityById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    public User findEntityByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    public UserResponseDTO findById(UUID id) {
        User user = findEntityById(id);
        return map(user);
    }

    //  UPDATE PROFILO
    public UserResponseDTO update(UUID id, UserUpdateDTO body) {
        User user = findEntityById(id);

        if (body.firstName() != null) user.setFirstName(body.firstName());
        if (body.lastName() != null) user.setLastName(body.lastName());
        if (body.username() != null) user.setUsername(body.username());
        if (body.email() != null) user.setEmail(body.email());
        if (body.avatar() != null) user.setAvatar(body.avatar());

        return map(userRepository.save(user));
    }

    //  UPDATE AVATAR SOLO
    public UserResponseDTO updateAvatar(UUID id, String avatar) {
        User user = findEntityById(id);
        user.setAvatar(avatar);
        return map(userRepository.save(user));
    }

    public void delete(UUID id) {
        User user = findEntityById(id);
        userRepository.delete(user);
    }

    //  REGISTER
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

        // avatar opzionale
        if (body.avatar() != null) {
            u.setAvatar(body.avatar());
        }

        return map(userRepository.save(u));
    }

}
