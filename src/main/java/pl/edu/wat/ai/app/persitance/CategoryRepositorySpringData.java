package pl.edu.wat.ai.app.persitance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.ai.app.user.finances.category.Category;
import pl.edu.wat.ai.app.user.finances.category.CategoryRepository;

interface CategoryRepositorySpringData extends JpaRepository<Category, Integer>, CategoryRepository {
}
