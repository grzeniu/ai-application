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
@DiscriminatorColumn(name = "financeType", discriminatorType = DiscriminatorType.STRING)
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    private String value;

    @Column(name = "financeType", insertable = false, updatable = false)
    private String financeType;

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
