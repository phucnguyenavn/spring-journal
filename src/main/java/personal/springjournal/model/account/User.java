package personal.springjournal.model.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Entity(name = "User")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(
                    name = "user_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "user_role_user_id_fk")
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "role_id", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "user_role_role_id_fk")
            )
    )
    private Set<Role> roles = new HashSet<>();

}
