package pl.edu.wat.ai.app.interfaces.rest.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.ai.app.user.User;
import pl.edu.wat.ai.app.user.UserService;

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
