package pl.edu.wat.ai.app.user.finances;

import java.util.Optional;

public interface ExpenseRepository {

    Expense save(Expense expense);

    Optional<Expense> findById(Integer id);
}
