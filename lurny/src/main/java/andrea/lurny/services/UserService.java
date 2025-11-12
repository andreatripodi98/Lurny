package andrea.lurny.services;

import andrea.lurny.entities.User;
import andrea.lurny.exceptions.AlreadyExistingException;
import andrea.lurny.exceptions.NotFoundException;
import andrea.lurny.payloads.UserDTO;
import andrea.lurny.repositories.UserRepository;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    //Find user
    public  User findById(UUID id){
        return  userRepository.findById(id).orElseThrow(() ->new NotFoundException("User not found"));
    }

    //Customize user
    public User findByIdAndUpdate(UUID id, UserDTO body){
        User foundUser = findById(id);
        foundUser.setUsername(body.username());
        foundUser.setEmail(body.email());
        foundUser.setPassword(body.password());
        foundUser.setFirstName(body.firstName());
        foundUser.setLastName(body.lastName());
        User updatedUser = userRepository.save(foundUser);
        return updatedUser;
    }

    // Delete user
    public void findByIdAndDelete(UUID id){
        User deletedUser = findById(id);
        userRepository.delete(deletedUser);
    }

    public User registerUser(UserDTO body) {
        if (userRepository.existsByUsername(body.username())) {
            throw new AlreadyExistingException("The chosen username is already in use");
        }

        if (userRepository.existsByEmail(body.email())) {
            throw new AlreadyExistingException("The provided email is already in use");
        }

        User u = new User();
        u.setFirstName(body.firstName());
        u.setLastName(body.lastName());
        u.setUsername(body.username());
        u.setEmail(body.email());
        u.setPassword(body.password());

        return userRepository.save(u);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

}