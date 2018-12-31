package pl.edu.wat.ai.app.user.finances;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.wat.ai.app.config.jwt.JwtTokenUtil;
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

    public List<Finance> getFinanceByUser(String token) {
        return findUserByToken(token).getFinances();
    }

    public void addExpense(String token, String description, Long value, Integer categoryId) {
        User user = findUserByToken(token);
        Expense expense = new Expense();
        expense.changeMandatoryAttributes(financeFactory.create(description, value, categoryId));

        user.getFinances().add(expense);
        userRepository.save(user);
    }

    public void addIncome(String token, String description, Long value, Integer categoryId) {
        User user = findUserByToken(token);
        Income income = new Income();
        income.changeMandatoryAttributes(financeFactory.create(description, value, categoryId));

        user.getFinances().add(income);
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
}
