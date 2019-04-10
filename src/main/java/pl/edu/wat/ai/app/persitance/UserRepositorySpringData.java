package pl.edu.wat.ai.app.persitance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.ai.app.finances.user.User;
import pl.edu.wat.ai.app.finances.user.UserRepository;

interface UserRepositorySpringData extends JpaRepository<User, Integer>, UserRepository {
}
