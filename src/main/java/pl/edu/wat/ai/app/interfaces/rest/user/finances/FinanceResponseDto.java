package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.Builder;
import lombok.Data;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.category.CategoryDto;

import java.time.LocalDateTime;

@Data
@Builder
public class FinanceResponseDto {
    private final Integer id;
    private final String description;
    private final String value;
    private final String financeType;
    private final String createdBy;
    private final LocalDateTime createdDate;
    private final CategoryDto category;
}
