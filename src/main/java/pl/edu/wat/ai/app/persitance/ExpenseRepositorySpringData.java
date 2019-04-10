package pl.edu.wat.ai.app.persitance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.ai.app.finances.Expense;
import pl.edu.wat.ai.app.finances.ExpenseRepository;

interface ExpenseRepositorySpringData extends JpaRepository<Expense ,Integer>, ExpenseRepository {
}
