package uz.pdp.project.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import uz.pdp.project.entity.enums.RoleEnum;


import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Role  implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    public Role(RoleEnum name) {
        this.name = name;
    }
    @Override
    public String getAuthority() {
        return this.name.name();
    }
}
