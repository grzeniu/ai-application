package pl.edu.wat.ai.app.user.finances;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.ai.app.user.finances.category.Category;
import pl.edu.wat.ai.app.user.finances.category.CategoryRepository;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
class FinanceFactory {

    private final CategoryRepository categoryRepository;

    ValidFinance create(String description, String value, Integer categoryId) {
        return new ValidFinance(description, value, findCategory(categoryId));
    }

    private Category findCategory(Integer id) {
        return categoryRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Getter
    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    static final class ValidFinance {
        private final String description;
        private final String value;
        private final Category category;
    }
}
