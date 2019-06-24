package pl.edu.wat.ai.app.mapper;

import pl.edu.wat.ai.app.currency.Currency;
import pl.edu.wat.ai.app.interfaces.rest.currency.CurrencyDto;

public class CurrencyMapperImpl implements CurrencyMapper {
    @Override
    public CurrencyDto currencyToDto(Currency currency) {
        CurrencyDto currencyDto = new CurrencyDto();
        currencyDto.setId(currency.getId());
        currencyDto.setTo(currency.getToCurrency());
        currencyDto.setFrom(currency.getFromCurrency());
        currencyDto.setRate(currency.getRate());
        return currencyDto;
    }
}
