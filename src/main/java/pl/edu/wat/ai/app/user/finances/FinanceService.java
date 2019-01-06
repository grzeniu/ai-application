package pl.edu.wat.ai.app.user.finances;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.ai.app.config.jwt.JwtTokenUtil;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.FinanceDto;
import pl.edu.wat.ai.app.user.User;
import pl.edu.wat.ai.app.user.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private final UserRepository userRepository;
    private final FinanceFactory financeFactory;
    private final JwtTokenUtil jwtTokenUtil;

    public List<Finance> getIncomesByUser(String token){
        return getFinanceByUser(token).stream().filter(it -> it.getFinanceType().equals("INCOME")).collect(Collectors.toList());
    }

    public List<Finance> getExpensesByUser(String token){
        return getFinanceByUser(token).stream().filter(it -> it.getFinanceType().equals("EXPENSE")).collect(Collectors.toList());
    }

    public List<Finance> getFinanceByUser(String token) {
        return findUserByToken(token).getFinances();
    }

    public void addExpense(String token, List<FinanceDto> finances) {
        User user = findUserByToken(token);
        List<Expense> expenses = finances.stream().map(this::createExpanse).collect(Collectors.toList());

        user.getFinances().addAll(expenses);
        userRepository.save(user);
    }

    public void addIncome(String token, List<FinanceDto> finances) {
        User user = findUserByToken(token);
        List<Income> incomes = finances.stream().map(this::createIncome).collect(Collectors.toList());

        user.getFinances().addAll(incomes);
        userRepository.save(user);
    }

    public void deleteFinance(String token, Integer id) {
        User user = findUserByToken(token);
        List<Finance> financeToRemove = user.getFinances().stream().filter(it -> id.equals(it.getId())).collect(Collectors.toList());
        user.getFinances().removeAll(financeToRemove);
        userRepository.save(user);
    }

    private User findUserByToken(String token) {
        String usernameFromToken = jwtTokenUtil.getUsernameFromToken(token.substring(7));
        return userRepository.findByUsername(usernameFromToken).orElseThrow(EntityNotFoundException::new);
    }

    private Expense createExpanse(FinanceDto finance){
        Expense expense = new Expense();
        expense.changeMandatoryAttributes(financeFactory.create(finance.getDescription(), finance.getValue(), finance.getCategoryId()));
        return expense;
    }

    private Income createIncome(FinanceDto finance){
        Income income = new Income();
        income.changeMandatoryAttributes(financeFactory.create(finance.getDescription(), finance.getValue(), finance.getCategoryId()));
        return income;
    }
}
