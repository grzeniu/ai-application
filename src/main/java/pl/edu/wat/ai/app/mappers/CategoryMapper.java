package pl.edu.wat.ai.app.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.category.CategoryDto;
import pl.edu.wat.ai.app.user.finances.category.Category;

@Mapper
public interface CategoryMapper {

    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(target = "name", source = "category.englishName")
    CategoryDto categoryToEngDto(Category category);

    @Mapping(target = "name", source = "category.polishName")
    CategoryDto categoryToPlDto(Category category);
}
