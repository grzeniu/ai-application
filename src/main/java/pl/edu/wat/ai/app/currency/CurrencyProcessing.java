package pl.edu.wat.ai.app.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.ai.app.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CurrencyProcessing {

    private final CurrencyRepository currencyRepository;

    public List<Currency> getCurrencies() {
        return currencyRepository.findAll();
    }

    public Currency getSpecifiedCurrencies(String fromCurrency, String toCurrency) {
        Assert.notNull(fromCurrency, "getSpecifiedCurrencies - fromCurrency cannot be null");
        Assert.notNull(toCurrency, "getSpecifiedCurrencies - toCurrency cannot be null");

        return currencyRepository.findByFromCurrencyAndToCurrency(fromCurrency, toCurrency)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Currency getCurrencyById(Integer id) {
        Assert.notNull(id, "getCurrencyById - id cannot be null");

        return currencyRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public void updateCurrencyRate(Currency currency) {
        Assert.notNull(currency, "updateCurrencyRate - currency cannot be null");

        currencyRepository.save(currency);
    }
}
