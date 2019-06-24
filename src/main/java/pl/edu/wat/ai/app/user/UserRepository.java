package pl.edu.wat.ai.app.user;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends Repository<User, Integer> {
    Optional<User> findByUsername(String username);

    List<User> findAll();

    Optional<User> findById(Integer id);

    User save(User user);
}
