package uz.pdp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Region;


import java.util.List;
import java.util.UUID;

public interface RegionRepository extends JpaRepository<Region, UUID> {
    List<Region> findAllByOrderByFieldDesc();
    List<Region> findAllByCreatedByOrderByCreatedAtDesc(UUID createdBy);
}
