package andrea.lurny.services;

import andrea.lurny.entities.User;
import andrea.lurny.exceptions.UnauthorizedException;
import andrea.lurny.payloads.LoginDTO;
import andrea.lurny.payloads.UserLoginDTO;
import andrea.lurny.payloads.UserRegisterDTO;
import andrea.lurny.payloads.UserResponseDTO;
import andrea.lurny.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools tools;

    @Autowired
    private PasswordEncoder bcrypt;

    // LOGIN + GENERA TOKEN
    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {

        // Cerca l'utente tramite username (se non trovato → null)
        User found = userService.findEntityByUsername(body.username());
        if (found == null) {
            throw new UnauthorizedException("Invalid credentials!");
        }

        // Controllo password
        if (!bcrypt.matches(body.password(), found.getPassword())) {
            throw new UnauthorizedException("Invalid credentials!");
        }

        // Genera token
        return tools.createToken(found);
    }

    // REGISTRAZIONE (usa UserService)
    public UserResponseDTO register(UserRegisterDTO body) {
        return userService.register(body);
    }
}


