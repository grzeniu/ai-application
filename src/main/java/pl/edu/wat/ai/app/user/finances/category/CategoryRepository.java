package pl.edu.wat.ai.app.user.finances.category;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends Repository<Category, Integer> {

    List<Category> findAll();

    Optional<Category> findById(Integer id);
}
