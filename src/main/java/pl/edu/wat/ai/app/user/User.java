package pl.edu.wat.ai.app.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import pl.edu.wat.ai.app.user.finances.Finance;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "my_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
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

    @OneToMany(cascade = CascadeType.ALL)
    private List<Finance> finances;
}
