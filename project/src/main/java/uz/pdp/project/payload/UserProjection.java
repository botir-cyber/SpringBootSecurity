package uz.pdp.project.payload;

import uz.pdp.project.entity.Role;
import uz.pdp.project.entity.University;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.List;
import java.util.UUID;

public interface UserProjection {
     UUID getId();
     String getFull_name();
     String getUsername();
     String getPhone_number();
     String getField();
     University getUniversity_id();
}
