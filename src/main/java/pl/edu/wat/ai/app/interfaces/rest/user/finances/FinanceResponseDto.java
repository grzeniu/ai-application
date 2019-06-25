package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.Data;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.category.CategoryDto;

import java.time.LocalDateTime;

@Data
public class FinanceResponseDto {
    private Integer id;
    private String description;
    private String value;
    private String financeType;
    private String createdBy;
    private LocalDateTime createdDate;
    private CategoryDto category;
}
