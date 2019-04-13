package pl.edu.wat.ai.app.interfaces.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.user.User;
import pl.edu.wat.ai.app.user.UserRepository;
import pl.edu.wat.ai.app.interfaces.rest.jwt.JwtTokenUtil;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.AuthToken;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.LoginUserDto;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.NewUserDto;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.UserDto;
import pl.edu.wat.ai.app.interfaces.rest.user.dto.UserDtoMapper;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/token")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final BCryptPasswordEncoder bcryptEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserRepository userRepository;

    @PostMapping("/generate-token")
    public ResponseEntity<AuthToken> register(@Valid @RequestBody LoginUserDto loginUser) throws AuthenticationException {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final User user = userRepository.findByUsername(loginUser.getUsername()).orElseThrow(EntityNotFoundException::new);
        final String token = jwtTokenUtil.generateToken(user);
        return new ResponseEntity<>(new AuthToken(token, user.getUsername()), HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserDto> saveUser(@Valid @RequestBody NewUserDto user) {
        return new ResponseEntity<>(saveNewUser(user), HttpStatus.CREATED);
    }

    private UserDto saveNewUser(NewUserDto dto) {
        return UserDtoMapper.mapUserToDto(userRepository.save(User.builder()
                .username(dto.getUsername())
                .firstName(dto.getFirstname())
                .lastName(dto.getLastname())
                .password(bcryptEncoder.encode(dto.getPassword()))
                .mail(dto.getMail())
                .userMonthlyLimit("")
                .build()));
    }


}
