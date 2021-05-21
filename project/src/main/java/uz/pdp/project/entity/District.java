package uz.pdp.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import uz.pdp.project.entity.template.AbsEntity;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class District extends AbsEntity {


    private String name;

    @ManyToOne
    private  Region region;

    @OneToMany(mappedBy = "districts")
    private List<University> universities;

    public District(String name, Region region) {
        this.region=region;
        this.name=name;
    }
}
