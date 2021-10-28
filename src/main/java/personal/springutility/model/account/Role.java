package personal.springutility.model.account;

import lombok.Data;

import javax.persistence.*;

@Table(name = "role")
@Entity(name = "Role")
@Data
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String role;
}
