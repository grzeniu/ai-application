package pl.edu.wat.ai.app.interfaces.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.user.User;
import pl.edu.wat.ai.app.user.UserService;

import java.security.Principal;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/limit")
    public ResponseEntity<String> getUserLimit(Principal principal) {
        return new ResponseEntity<>(userService.findByUsername(principal.getName()).getUserMonthlyLimit(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<User> updateUserLimit(Principal principal, @RequestParam String limit) {
        return new ResponseEntity<>(userService.updateUserLimit(principal.getName(), limit), HttpStatus.NO_CONTENT);
    }
}
