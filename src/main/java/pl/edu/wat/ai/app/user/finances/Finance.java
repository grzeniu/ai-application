package pl.edu.wat.ai.app.user.finances;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Finance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String description;
}
