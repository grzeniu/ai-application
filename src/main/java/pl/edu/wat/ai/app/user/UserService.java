package pl.edu.wat.ai.app.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wat.ai.app.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Assert.notNull(username, "Username cannot be empty");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    public User findByUsername(String username) {
        Assert.notNull(username, "Username cannot be empty");

        return userRepository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    public User updateUserFinancies(User user) {
        Assert.notNull(user, "User cannot be empty");

        return userRepository.save(user);
    }

    @Transactional
    public User updateUserLimit(String username, String newLimit) {
        Assert.notNull(username, "username cannot be empty");
        Assert.notNull(username, "newLimit cannot be empty");

        User user = findByUsername(username);
        user.updateLimit(newLimit);
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public User save(User user) {
        Assert.notNull(user, "User cannot be empty");

        return userRepository.save(user);
    }
}
