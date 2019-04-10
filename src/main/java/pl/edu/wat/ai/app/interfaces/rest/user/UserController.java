package pl.edu.wat.ai.app.interfaces.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.finances.user.User;
import pl.edu.wat.ai.app.finances.user.UserService;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> listUser() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable int id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/limit")
    public ResponseEntity<String> getUserLimit(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(userService.findByToken(token).getUserMonthlyLimit(), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<User> updateUserLimit(@RequestHeader("Authorization") String token, @RequestParam String limit) {
        return new ResponseEntity<>(userService.updateUserLimit(token, limit), HttpStatus.NO_CONTENT);
    }
}
