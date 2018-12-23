package pl.edu.wat.ai.app.currency;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyRepository extends JpaRepository<Currency, Integer> {
    Optional<Currency> findByFromCurrencyAndToCurrency(String fromCurrency, String toCurrency);
}
