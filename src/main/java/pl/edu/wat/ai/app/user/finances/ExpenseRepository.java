package pl.edu.wat.ai.app.user.finances;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface ExpenseRepository extends Repository<Expense ,Integer> {

    Expense save(Expense expense);

    Optional<Expense> findById(Integer id);
}
