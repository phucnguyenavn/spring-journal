package personal.springutility.model.journal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "UserCreatedPage")
@Table(name = "user_created_page")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatedPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "userCreatedPage")
    private Set<Page> pages;

    @Column(name = "user_id")
    private Integer userId;

}
