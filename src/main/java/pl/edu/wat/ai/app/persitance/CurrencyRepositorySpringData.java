package pl.edu.wat.ai.app.persitance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.ai.app.currency.Currency;
import pl.edu.wat.ai.app.currency.CurrencyRepository;

interface CurrencyRepositorySpringData extends JpaRepository<Currency, Integer>, CurrencyRepository {
}
