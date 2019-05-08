package pl.edu.wat.ai.app.user.finances;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Audited
@DiscriminatorValue("INCOME")
public class Income extends Finance {
}
