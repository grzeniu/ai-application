package pl.edu.wat.ai.app.interfaces.rest.user.finances;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wat.ai.app.config.jwt.JwtTokenUtil;
import pl.edu.wat.ai.app.user.User;
import pl.edu.wat.ai.app.user.UserRepository;
import pl.edu.wat.ai.app.user.finances.Expense;
import pl.edu.wat.ai.app.user.finances.Finance;
import pl.edu.wat.ai.app.user.finances.Income;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/finances")
@RequiredArgsConstructor
public class FinancesController {

    private final UserRepository userRepository;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping()
    private ResponseEntity<List<Finance>> getAll(@RequestHeader("Authorization") String token) {
        return new ResponseEntity<>(findUserByToken(token).getFinances(), HttpStatus.OK);
    }

    @PostMapping("/expenses")
    private ResponseEntity<String> addExpense(@RequestHeader("Authorization") String token,
                                              @RequestBody FinanceDto financeDto) {
        User user = findUserByToken(token);
        Expense expense = new Expense();
        expense.setDescription(financeDto.getDescription());

        user.getFinances().add(expense);
        userRepository.save(user);
        return new ResponseEntity<>("Done", HttpStatus.CREATED);
    }

    @PostMapping("/incomes")
    private ResponseEntity<String> addIncome(@RequestHeader("Authorization") String token,
                                             @RequestBody FinanceDto financeDto) {
        User user = findUserByToken(token);
        Income income = new Income();
        income.setDescription(financeDto.getDescription());

        user.getFinances().add(income);
        userRepository.save(user);
        return new ResponseEntity<>("Done", HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    private ResponseEntity deleteFinance(@RequestHeader("Authorization") String token,
                                           @PathVariable Integer id){
        User user = findUserByToken(token);
        List<Finance> financeToRemove = user.getFinances().stream().filter(it -> id.equals(it.getId())).collect(Collectors.toList());
        user.getFinances().removeAll(financeToRemove);
        userRepository.save(user);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private User findUserByToken(String token) {
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        return userRepository.findByUsername(usernameFromToken).orElseThrow(EntityNotFoundException::new);
    }
}
