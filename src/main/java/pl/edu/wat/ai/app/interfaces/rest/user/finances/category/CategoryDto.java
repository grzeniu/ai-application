package pl.edu.wat.ai.app.interfaces.rest.user.finances.category;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDto {
    private final Integer id;
    private final String name;
}
