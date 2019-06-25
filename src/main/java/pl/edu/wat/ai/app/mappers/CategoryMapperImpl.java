package pl.edu.wat.ai.app.mappers;

import pl.edu.wat.ai.app.interfaces.rest.user.finances.category.CategoryDto;
import pl.edu.wat.ai.app.user.finances.category.Category;

public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public CategoryDto categoryToEngDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getEnglishName());
        return categoryDto;
    }

    @Override
    public CategoryDto categoryToPlDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getPolishName());
        return categoryDto;
    }

}
