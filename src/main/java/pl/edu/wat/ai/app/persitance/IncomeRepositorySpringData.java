package pl.edu.wat.ai.app.persitance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.wat.ai.app.finances.Income;
import pl.edu.wat.ai.app.finances.IncomeRepository;

interface IncomeRepositorySpringData extends JpaRepository<Income, Integer>, IncomeRepository {
}
