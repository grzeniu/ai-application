package pl.edu.wat.ai.app.user.finances.category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    List<Category> findAll();

    Optional<Category> findById(Integer id);
}
