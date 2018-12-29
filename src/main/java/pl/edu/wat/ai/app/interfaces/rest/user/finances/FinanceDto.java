package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class FinanceDto {
    private final String description;
    private final Long value;
}
