package pl.edu.wat.ai.app.interfaces.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.interfaces.rest.jwt.JwtTokenUtil;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.AuthToken;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.LoginUserDto;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.NewUserDto;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.UserDto;
import pl.edu.wat.ai.app.mappers.UserMapper;
import pl.edu.wat.ai.app.user.User;
import pl.edu.wat.ai.app.user.UserService;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/generate-token")
    public ResponseEntity<AuthToken> register(@Valid @RequestBody LoginUserDto loginUser) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userService.findByUsername(loginUser.getUsername());
        final String token = jwtTokenUtil.generateToken(user);
        return new ResponseEntity<>(new AuthToken(token, user.getUsername()), HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody NewUserDto user) {
        User newUser = userService.save(UserMapper.INSTANCE.newUserToUser(user, bcryptEncoder));
        return new ResponseEntity<>(UserMapper.INSTANCE.userToDto(newUser), HttpStatus.CREATED);
    }

}
