package andrea.lurny.controllers;



import andrea.lurny.entities.User;
import andrea.lurny.exceptions.ValidationException;
import andrea.lurny.payloads.LoginDTO;
import andrea.lurny.payloads.LoginResponseDTO;
import andrea.lurny.payloads.UserDTO;
import andrea.lurny.services.AuthService;
import andrea.lurny.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody LoginDTO body) {
        return new LoginResponseDTO(authService.checkCredentialsAndGenerateToken(body));
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public User register(@RequestBody @Valid UserDTO body, BindingResult validationResult) {

        if (validationResult.hasErrors()) {

            List<String> errors = validationResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toList();

            throw new ValidationException(errors);
        }

        return userService.registerUser(body);
    }

}


