package andrea.lurny.controllers;

import andrea.lurny.entities.User;
import andrea.lurny.payloads.UserRegisterDTO;
import andrea.lurny.payloads.UserResponseDTO;
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

    // Recupera l'utente autenticato dal SecurityContext
    private User getCurrentUser() {
        return (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
    }

    // Ottiene i dati dell'utente loggato
    @GetMapping("/me")
    public UserResponseDTO getMyProfile() {
        User current = getCurrentUser();
        return userService.findById(current.getId());
    }

    // Aggiorna i dati dell'utente loggato
    @PutMapping("/me")
    public UserResponseDTO updateMyProfile(@RequestBody @Valid UserRegisterDTO body) {
        User current = getCurrentUser();
        return userService.update(current.getId(), body);
    }

    // Elimina l'utente loggato
    @DeleteMapping("/me")
    public void deleteMyProfile() {
        User current = getCurrentUser();
        userService.delete(current.getId());
    }
}

