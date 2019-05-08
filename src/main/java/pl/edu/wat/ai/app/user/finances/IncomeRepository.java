package pl.edu.wat.ai.app.user.finances;

import java.util.Optional;

public interface IncomeRepository {

    Income save(Income Income);

    Optional<Income> findById(Integer id);
}
