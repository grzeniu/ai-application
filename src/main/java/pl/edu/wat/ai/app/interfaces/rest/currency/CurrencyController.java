package pl.edu.wat.ai.app.interfaces.rest.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.ai.app.currency.Currency;
import pl.edu.wat.ai.app.currency.CurrencyRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyRepository currencyRepository;

    @GetMapping("/convert")
    public ResponseEntity<CurrencyDto> getOne(@RequestParam() String from, @RequestParam() String to) {
        Currency currency = currencyRepository.findByFromCurrencyAndToCurrency(from,to).orElseThrow(EntityNotFoundException::new);
        return new ResponseEntity<>(mapToDto(currency), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CurrencyDto>> getCurrencyRates() {
        return new ResponseEntity<>(
                currencyRepository.findAll().stream().map(this::mapToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }

    private CurrencyDto mapToDto(Currency currency) {
        return CurrencyDto.builder()
                .id(currency.getId())
                .rate(currency.getRate())
                .from(currency.getFromCurrency())
                .to(currency.getToCurrency())
                .build();
    }
}
