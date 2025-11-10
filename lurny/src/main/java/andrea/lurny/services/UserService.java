package andrea.lurny.services;

import andrea.lurny.entities.User;
import andrea.lurny.exceptions.NotFoundException;
import andrea.lurny.payloads.UserDTO;
import andrea.lurny.repositories.UserRepository;
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
}