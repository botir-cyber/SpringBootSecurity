package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.project.entity.University;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResUniversity {
    public ResUniversity(University university) {
        this.id=university.getId();
        this.name=university.getName();
        this.address=university.getAddress();
    }

    private UUID id;
    private String name;
    private String address;
}
