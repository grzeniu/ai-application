package pl.edu.wat.ai.app.interfaces.rest.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.currency.Currency;
import pl.edu.wat.ai.app.currency.CurrencyService;
import pl.edu.wat.ai.app.mappers.CurrencyMapper;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/currencies")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CurrencyController {

    private final CurrencyService currencyService;

    @GetMapping("/convert")
    public ResponseEntity<CurrencyDto> getOne(@RequestParam() String from, @RequestParam() String to) {
        Currency currency = currencyService.getSpecifiedCurrencies(from, to);
        return new ResponseEntity<>(CurrencyMapper.INSTANCE.currencyToDto(currency), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CurrencyDto>> getCurrencyRates() {
        return new ResponseEntity<>(
                currencyService.getCurrencies().stream().map(CurrencyMapper.INSTANCE::currencyToDto).collect(Collectors.toList()),
                HttpStatus.OK
        );
    }
}
