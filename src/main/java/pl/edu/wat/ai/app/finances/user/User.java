package pl.edu.wat.ai.app.finances.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import pl.edu.wat.ai.app.finances.Finance;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Entity
@Builder
@Audited
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "my_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String username;
    @JsonIgnore
    private String password;
    private String mail;
    private String userMonthlyLimit;

    @Audited
    @OneToMany(cascade = CascadeType.ALL)
    private List<Finance> finances;

    void updateLimit(String newLimit) {
        this.userMonthlyLimit = newLimit;
    }
}
