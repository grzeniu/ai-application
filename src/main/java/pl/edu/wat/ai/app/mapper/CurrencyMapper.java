package pl.edu.wat.ai.app.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import pl.edu.wat.ai.app.currency.Currency;
import pl.edu.wat.ai.app.interfaces.rest.currency.CurrencyDto;

@Mapper
public interface CurrencyMapper {

    CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

    @Mapping(target = "from", source = "currency.fromCurrency")
    @Mapping(target = "to", source = "currency.toCurrency")
    CurrencyDto currencyToDto(Currency currency);

}
