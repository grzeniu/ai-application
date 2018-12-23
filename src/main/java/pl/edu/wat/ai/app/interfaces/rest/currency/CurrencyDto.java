package pl.edu.wat.ai.app.interfaces.rest.currency;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class CurrencyDto {
    private final Integer id;
    private final String from;
    private final String to;
    private final Double rate;
}

