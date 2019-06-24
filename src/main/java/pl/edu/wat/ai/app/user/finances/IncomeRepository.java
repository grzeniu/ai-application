package pl.edu.wat.ai.app.user.finances;

import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface IncomeRepository extends Repository<Income, Integer> {

    Income save(Income Income);

    Optional<Income> findById(Integer id);
}
