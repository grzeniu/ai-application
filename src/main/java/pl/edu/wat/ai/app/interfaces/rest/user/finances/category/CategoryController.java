package pl.edu.wat.ai.app.interfaces.rest.user.finances.category;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.mappers.CategoryMapper;
import pl.edu.wat.ai.app.user.finances.category.CategoryService;
import pl.edu.wat.ai.app.user.finances.category.Category;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("categories")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/pl")
    private ResponseEntity<List<CategoryDto>> getCategoriesWithPolishNames() {
        return new ResponseEntity<>(categoryService.findAll().stream().map(CategoryMapper.INSTANCE::categoryToPlDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/eng")
    private ResponseEntity<List<CategoryDto>> getCategoriesWithEnglishNames() {
        return new ResponseEntity<>(categoryService.findAll().stream().map(CategoryMapper.INSTANCE::categoryToEngDto).collect(Collectors.toList()), HttpStatus.OK);
    }

}
