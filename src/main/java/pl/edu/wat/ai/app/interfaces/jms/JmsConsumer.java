package pl.edu.wat.ai.app.interfaces.jms;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pl.edu.wat.ai.app.currency.Currency;
import pl.edu.wat.ai.app.currency.CurrencyProcessing;

@Slf4j
@Component
@RequiredArgsConstructor
public class JmsConsumer {

    private final CurrencyProcessing currencyProcessing;

    @JmsListener(destination = "MY_QUEUE")
    public void receive(JmsMessage message) {
        log.info("Received message " + message);
        Currency updatedCurrency = RestClient.fetchRate(currencyProcessing.getCurrencyById(message.getId()));
        currencyProcessing.updateCurrencyRate(updatedCurrency);
    }
}
