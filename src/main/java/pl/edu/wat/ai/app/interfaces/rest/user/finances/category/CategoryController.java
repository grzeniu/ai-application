package pl.edu.wat.ai.app.interfaces.rest.user.finances.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.finances.category.Category;
import pl.edu.wat.ai.app.finances.category.CategoryRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    private final CategoryRepository categoryRepository;

    @GetMapping("/pl")
    private ResponseEntity<List<CategoryDto>> getCategoriesWithPolishNames() {
        return new ResponseEntity<>(categoryRepository.findAll().stream().map(this::mapToDtoUsingPolishNames).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/eng")
    private ResponseEntity<List<CategoryDto>> getCategoriesWithEnglishNames() {
        return new ResponseEntity<>(categoryRepository.findAll().stream().map(this::mapToDtoUsingEnglishNames).collect(Collectors.toList()), HttpStatus.OK);
    }

    private CategoryDto mapToDtoUsingPolishNames(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getPolishName())
                .build();
    }

    private CategoryDto mapToDtoUsingEnglishNames(Category category) {
        return CategoryDto.builder()
                .id(category.getId())
                .name(category.getEnglishName())
                .build();
    }
}
