package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.finances.Finance;
import pl.edu.wat.ai.app.finances.FinanceService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/finances")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FinancesController {

    private final FinanceService financeService;

    @GetMapping("/incomes")
    private ResponseEntity<List<Finance>> getAllIncomes(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(financeService.getIncomesByUser(token), HttpStatus.OK);
    }

    @GetMapping("/expenses")
    private ResponseEntity<List<Finance>> getAllExpenses(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(financeService.getExpensesByUser(token), HttpStatus.OK);
    }

    @PostMapping("/expenses")
    private ResponseEntity<String> addExpense(@RequestHeader("Authorization") String token,
                                              @RequestBody List<FinanceDto> financeDto) {
        financeService.addExpenses(token, financeDto);
        return new ResponseEntity<>("Done", HttpStatus.CREATED);
    }

    @PostMapping("/incomes")
    private ResponseEntity<String> addIncome(@RequestHeader("Authorization") String token,
                                             @RequestBody List<FinanceDto> financeDto) {
        financeService.addIncomes(token, financeDto);
        return new ResponseEntity<>("Done", HttpStatus.CREATED);
    }

    @PostMapping("/expense")
    private ResponseEntity<Finance> addExpense(@RequestHeader("Authorization") String token,
                                               @RequestBody FinanceDto financeDto) {
        return new ResponseEntity<>(financeService.addExpense(token, financeDto), HttpStatus.CREATED);
    }

    @PostMapping("/income")
    private ResponseEntity<Finance> addIncome(@RequestHeader("Authorization") String token,
                                              @RequestBody FinanceDto financeDto) {
        return new ResponseEntity<>(financeService.addIncome(token, financeDto), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity deleteFinance(@RequestHeader("Authorization") String token,
                                         @PathVariable Integer id) {
        financeService.deleteFinance(token, id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
