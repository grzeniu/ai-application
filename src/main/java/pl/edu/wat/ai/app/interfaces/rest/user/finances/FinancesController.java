package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.ai.app.user.finances.Finance;
import pl.edu.wat.ai.app.user.finances.FinanceService;

import java.util.List;

@RestController
@RequestMapping("/finances")
@RequiredArgsConstructor
public class FinancesController {

    private final FinanceService financeService;

    @GetMapping()
    private ResponseEntity<List<Finance>> getAll(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(financeService.getFinanceByUser(token), HttpStatus.OK);
    }

    @PostMapping("/expenses")
    private ResponseEntity<String> addExpense(@RequestHeader("Authorization") String token,
                                              @RequestBody FinanceDto financeDto) {
        financeService.addExpense(token, financeDto.getDescription(), financeDto.getValue(),financeDto.getCategoryId());
        return new ResponseEntity<>("Done", HttpStatus.CREATED);
    }

    @PostMapping("/incomes")
    private ResponseEntity<String> addIncome(@RequestHeader("Authorization") String token,
                                             @RequestBody FinanceDto financeDto) {
        financeService.addIncome(token, financeDto.getDescription(), financeDto.getValue(), financeDto.getCategoryId());
        return new ResponseEntity<>("Done", HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity deleteFinance(@RequestHeader("Authorization") String token,
                                         @PathVariable Integer id) {
        financeService.deleteFinance(token, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
