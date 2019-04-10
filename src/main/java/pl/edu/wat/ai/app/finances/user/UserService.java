package pl.edu.wat.ai.app.finances.user;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wat.ai.app.interfaces.rest.jwt.JwtTokenUtil;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service(value = "userService")
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password."));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User findByToken(String token) {
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        return userRepository.findByUsername(usernameFromToken).orElseThrow(EntityNotFoundException::new);
    }
    @Transactional
    public User updateUserLimit(String token, String newLimit){
        User user = findByToken(token);
        user.updateLimit(newLimit);
        return user;
    }
}
