package pl.edu.wat.ai.app.user;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);
}
