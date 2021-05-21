package uz.pdp.project.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.project.entity.Region;


import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResRegion {
    public ResRegion(Region region) {
        this.id=region.getId();
        this.field=region.getField();
    }

    private UUID id;
    private String field;
}
