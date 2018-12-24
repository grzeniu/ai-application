package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinanceDto {
    private final String description;
}
