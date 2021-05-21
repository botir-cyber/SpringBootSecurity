package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.project.entity.District;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResDistrict {

    public ResDistrict(District district){
        this.id=district.getId();
        this.name=district.getName();
    }

    private UUID id;
    private String name;
}
