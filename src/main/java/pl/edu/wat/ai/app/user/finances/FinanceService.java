package pl.edu.wat.ai.app.user.finances;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;
    private final FinanceFactory financeFactory;
    private final JwtTokenUtil jwtTokenUtil;

    public List<Finance> getIncomesByUser(String token){
        return getFinanceByUser(token).stream().filter(it -> it.getFinanceType().equals("INCOME")).collect(Collectors.toList());
    }

    public List<Finance> getExpensesByUser(String token){
        return getFinanceByUser(token).stream().filter(it -> it.getFinanceType().equals("EXPENSES")).collect(Collectors.toList());
    }

    public List<Finance> getFinanceByUser(String token) {
        return findUserByToken(token).getFinances();
    }

    public User addExpenses(String token, List<FinanceDto> finances) {
        User user = findUserByToken(token);
        List<Expense> expenses = finances.stream().map(this::createExpanse).collect(Collectors.toList());

        user.getFinances().addAll(expenses);
        return userRepository.save(user);
    }

    public User addIncomes(String token, List<FinanceDto> finances) {
        User user = findUserByToken(token);
        List<Income> incomes = finances.stream().map(this::createIncome).collect(Collectors.toList());

        user.getFinances().addAll(incomes);
        return userRepository.save(user);
    }

    @Transactional
    public Expense addExpense(String token, FinanceDto finance) {
        User user = findUserByToken(token);
        Expense expense = createExpanse(finance);

        Expense savedExpense = expenseRepository.save(expense);
        user.getFinances().add(expense);
        userRepository.save(user);
        return savedExpense;
    }

    @Transactional
    public Income addIncome(String token, FinanceDto finance) {
        User user = findUserByToken(token);
        Income income = createIncome(finance);

        Income savedIncome = incomeRepository.save(income);
        user.getFinances().add(income);
        userRepository.save(user);
        return savedIncome;
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
