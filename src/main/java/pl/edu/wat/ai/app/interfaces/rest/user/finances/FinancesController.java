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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.wat.ai.app.mappers.FinancesMapper;
import pl.edu.wat.ai.app.user.finances.FinanceService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/finances")
@CrossOrigin(origins = "*", maxAge = 3600)
public class FinancesController {

    private final FinanceService financeService;

    @GetMapping()
    private ResponseEntity<List<FinanceResponseDto>> getAllFinances(Principal principal) {
        return new ResponseEntity<>(financeService.getFinanceByUser(principal.getName()).stream()
                .map(FinancesMapper.INSTANCE::financeToResponseDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/incomes")
    private ResponseEntity<List<FinanceResponseDto>> getAllIncomes(Principal principal) {
        return new ResponseEntity<>(financeService.getIncomesByUser(principal.getName()).stream()
                .map(FinancesMapper.INSTANCE::financeToResponseDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/expenses")
    private ResponseEntity<List<FinanceResponseDto>> getAllExpenses(Principal principal) {
        return new ResponseEntity<>(financeService.getExpensesByUser(principal.getName()).stream()
                .map(FinancesMapper.INSTANCE::financeToResponseDto).collect(Collectors.toList()), HttpStatus.OK);
    }

    @PostMapping("/expense")
    private ResponseEntity<FinanceResponseDto> addExpense(Principal principal, @RequestBody FinanceDto financeDto) {
        return new ResponseEntity<>(FinancesMapper.INSTANCE.financeToResponseDto(financeService.addExpense(principal.getName(), financeDto)), HttpStatus.CREATED);
    }

    @PostMapping("/income")
    private ResponseEntity<FinanceResponseDto> addIncome(Principal principal, @RequestBody FinanceDto financeDto) {
        return new ResponseEntity<>(FinancesMapper.INSTANCE.financeToResponseDto(financeService.addIncome(principal.getName(), financeDto)), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity deleteFinance(Principal principal, @PathVariable Integer id) {
        financeService.deleteFinance(principal.getName(), id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
