package uz.pdp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import uz.pdp.project.entity.District;


import java.util.List;
import java.util.UUID;

public interface DistrictRepository extends JpaRepository<District, UUID> {
    List<District> findAllByOrderByNameDesc();
    List<District> findAllByRegion_Id(UUID region_id);
}
