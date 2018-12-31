package pl.edu.wat.ai.app.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import pl.edu.wat.ai.app.user.finances.Finance;

import javax.persistence.*;
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

    @Audited
    @OneToMany(cascade = CascadeType.ALL)
    private List<Finance> finances;
}
