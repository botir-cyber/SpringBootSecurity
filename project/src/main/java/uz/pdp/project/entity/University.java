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
public class University  extends AbsEntity {
    @Id
//@Type(type = "org.hibernate.type.PostgresUUIDType")
    @Type(type="pg-uuid")
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String name;
    private String address;
    private String description;
    private String images;

    @ManyToOne
    private District districts;


    public University(String name, String address, String description, String images, District district) {
        this.name=name;
        this.districts=district;
        this.address=address;
        this.images=images;
        this.description=description;
    }
}
