package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.Data;

@Data
public class FinanceDto {
    private String description;
    private String value;
    private Integer categoryId;
}
