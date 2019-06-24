package pl.edu.wat.ai.app.interfaces.rest.currency;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrencyDto {

    private Integer id;
    private String from;
    private String to;
    private Double rate;

    public CurrencyDto() {
    }
}

