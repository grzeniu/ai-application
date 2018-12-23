package pl.edu.wat.ai.app.rest.currencyconverterapi;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pl.edu.wat.ai.app.currency.CurrencyRepository;

@Component
@RequiredArgsConstructor
class CurrencyScheduler {
    private final CurrencyRepository currencyRepository;

    @Scheduled(fixedDelay = 60000)
    void getCurrencyBySymbol() {
        currencyRepository.findAll().stream().map(RestClient::fetchRate).forEach(currencyRepository::save);
    }
}
