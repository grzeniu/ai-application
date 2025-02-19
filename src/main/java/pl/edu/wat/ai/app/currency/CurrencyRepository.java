package pl.edu.wat.ai.app.currency;

import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CurrencyRepository extends Repository<Currency, Integer> {
    Optional<Currency> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);

    Optional<Currency> findById(Integer id);

    List<Currency> findAll();

    Currency save(Currency currency);
}
