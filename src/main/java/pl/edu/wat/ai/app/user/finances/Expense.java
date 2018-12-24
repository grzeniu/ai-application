package pl.edu.wat.ai.app.user.finances;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@DiscriminatorValue("EXPENSES")
public class Expense extends Finance {
}
