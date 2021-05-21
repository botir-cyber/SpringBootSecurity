package uz.pdp.project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uz.pdp.project.entity.User;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllByOrderByFullNameDesc();
    Page<User> findAllByUniversity_IdOrderByField(UUID university_id, Pageable pageable);
    Page<User> findAllByUniversity_IdOrderByFullName(UUID university_id, Pageable pageable);
    @Query(value = "select * from users where university_id =:universityId ",nativeQuery = true)
    Page<User> getUserByUniversityId(UUID universityId, Pageable pageable);
    Page<User> findAllByUniversity_IdOrderByPhoneNumber(UUID university_id, Pageable pageable);
    Page<User> findAllByUniversity_IdOrderByUsername(UUID university_id, Pageable pageable);
    Page<User> findAllByUniversity_Districts_Region_Id_OrderByUsername(UUID region_id, Pageable pageable);
    @Query(value = "select users.* from users where university_id in (select id from university " +
            " where districts_id in (select  id from district where region_id =:regionId )) ",nativeQuery = true)
    Page<User> getUserByRegionId(UUID regionId, Pageable pageable);
    Optional<User> findByPhoneNumber(String phoneNumber);
    Optional<User> findByUsername(String userName);
    Boolean existsByUsername(String username);
    Boolean existsByPhoneNumber(String phoneNumber);
}
