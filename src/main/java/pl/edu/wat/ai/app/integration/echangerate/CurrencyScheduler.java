package pl.edu.wat.ai.app.integration.echangerate;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.wat.ai.app.currency.Currency;
import pl.edu.wat.ai.app.currency.CurrencyService;

@Component
@RequiredArgsConstructor
class CurrencyScheduler {
    private final CurrencyService currencyService;
    private final JmsProducer jmsProducer;

    @Scheduled(fixedDelay = 120000)
    void getCurrencyBySymbol() {
        currencyService.getCurrencies().stream().map(this::mapToJmsMessage).forEach(jmsProducer::send);
    }

    private JmsMessage mapToJmsMessage(Currency currency) {
        return JmsMessage.builder()
                .id(currency.getId())
                .fromCurrency(currency.getFromCurrency())
                .toCurrency(currency.getToCurrency())
                .build();
    }
}
