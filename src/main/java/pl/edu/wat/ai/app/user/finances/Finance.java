package pl.edu.wat.ai.app.user.finances;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import pl.edu.wat.ai.app.user.finances.category.Category;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Audited
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;

    private Long value;

    @CreatedBy
    private String createdBy;

    @CreatedDate
    private LocalDateTime createdDate;

    @NotAudited
    @OneToOne
    private Category category;

    void changeMandatoryAttributes(FinanceFactory.ValidFinance validFinance){
        this.setDescription(validFinance.getDescription());
        this.setValue(validFinance.getValue());
        this.setCategory(validFinance.getCategory());
    }
}
