package pl.edu.wat.ai.app.user.finances;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.wat.ai.app.interfaces.rest.user.finances.FinanceDto;
import pl.edu.wat.ai.app.user.User;
import pl.edu.wat.ai.app.user.UserService;
import pl.edu.wat.ai.app.util.Assert;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FinanceService {

    private static final String INCOME = "INCOME";
    private static final String EXPENSES = "EXPENSES";

    private final ExpenseRepository expenseRepository;
    private final IncomeRepository incomeRepository;
    private final FinanceFactory financeFactory;
    private final UserService userService;

    public List<Finance> getIncomesByUser(String username) {
        return getFinanceByUser(username).stream()
                .filter(it -> it.getFinanceType().equals(INCOME))
                .collect(Collectors.toList());
    }

    public List<Finance> getExpensesByUser(String username) {
        return getFinanceByUser(username).stream()
                .filter(it -> it.getFinanceType().equals(EXPENSES))
                .collect(Collectors.toList());
    }

    public List<Finance> getFinanceByUser(String username) {
        return userService.findByUsername(username).getFinances();
    }

    public List<Finance> getFinanceByUserByDate(String username, Integer month, Integer year) {
        Assert.notNull(month, "month cannot be null");
        Assert.notNull(year, "year cannot be null");

        return userService.findByUsername(username)
                .getFinances()
                .stream()
                .filter(it -> it.getCreatedDate().getMonthValue() == month && it.getCreatedDate().getYear() == year)
                .collect(Collectors.toList());
    }

    @Transactional
    public Expense addExpense(String username, FinanceDto finance) {
        Assert.notNull(finance, "Finance cannot be null");

        User user = userService.findByUsername(username);
        Expense expense = createExpanse(finance);

        Expense savedExpense = expenseRepository.save(expense);
        user.getFinances().add(expense);
        userService.updateUserFinancies(user);
        return expenseRepository.findById(savedExpense.getId()).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Income addIncome(String username, FinanceDto finance) {
        Assert.notNull(finance, "Finance cannot be null");

        User user = userService.findByUsername(username);
        Income income = createIncome(finance);

        Income savedIncome = incomeRepository.save(income);
        user.getFinances().add(income);
        userService.updateUserFinancies(user);
        return incomeRepository.findById(savedIncome.getId()).orElseThrow(EntityNotFoundException::new);
    }

    public void deleteFinance(String username, Integer id) {
        Assert.notNull(id, "Finance id cannot be null");

        User user = userService.findByUsername(username);
        List<Finance> financeToRemove = user.getFinances().stream()
                .filter(it -> id.equals(it.getId()))
                .collect(Collectors.toList());

        user.getFinances().removeAll(financeToRemove);
        userService.updateUserFinancies(user);
    }

    private Expense createExpanse(FinanceDto finance) {
        Expense expense = new Expense();
        expense.changeMandatoryAttributes(financeFactory.create(finance.getDescription(), finance.getValue(), finance.getCategoryId()));
        return expense;
    }

    private Income createIncome(FinanceDto finance) {
        Income income = new Income();
        income.changeMandatoryAttributes(financeFactory.create(finance.getDescription(), finance.getValue(), finance.getCategoryId()));
        return income;
    }
}
