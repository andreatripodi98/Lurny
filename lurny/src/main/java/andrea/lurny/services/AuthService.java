package andrea.lurny.services;

import andrea.lurny.entities.User;
import andrea.lurny.exceptions.UnauthorizedException;
import andrea.lurny.payloads.LoginDTO;
import andrea.lurny.payloads.UserLoginDTO;
import andrea.lurny.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService service;

    @Autowired
    private JWTTools tools;

    @Autowired
    private PasswordEncoder bcrypt;

    public String checkCredentialsAndGenerateToken(UserLoginDTO body) {
        User found = service.findEntityByUsername(body.username());

        if (bcrypt.matches(body.password(), found.getPassword())) {
            return tools.createToken(found);
        } else {
            throw new UnauthorizedException("Invalid credentials!");
        }
    }
}

