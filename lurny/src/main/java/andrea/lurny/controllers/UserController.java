package andrea.lurny.controllers;

import andrea.lurny.entities.User;
import andrea.lurny.payloads.AvatarDTO;
import andrea.lurny.payloads.UserRegisterDTO;
import andrea.lurny.payloads.UserResponseDTO;
import andrea.lurny.payloads.UserUpdateDTO;
import andrea.lurny.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    // GET PROFILO UTENTE
    @GetMapping("/me")
    public UserResponseDTO getMyProfile() {
        User current = getCurrentUser();
        return userService.findById(current.getId());
    }

    // UPDATE COMPLETO DEL PROFILO (NO PASSWORD)
    @PutMapping("/me")
    public UserResponseDTO updateMyProfile(@RequestBody @Valid UserUpdateDTO body) {
        User current = getCurrentUser();
        return userService.update(current.getId(), body);
    }

    // UPDATE SOLO AVATAR (opzionale)
    @PutMapping("/me/avatar")
    public UserResponseDTO updateAvatar(@RequestBody AvatarDTO body) {
        User current = getCurrentUser();
        return userService.updateAvatar(current.getId(), body.avatar());
    }

    // DELETE ACCOUNT
    @DeleteMapping("/me")
    public void deleteMyProfile() {
        User current = getCurrentUser();
        userService.delete(current.getId());
    }
}


