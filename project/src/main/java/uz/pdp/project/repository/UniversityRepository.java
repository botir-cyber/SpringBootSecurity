package uz.pdp.project.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.project.entity.University;
import uz.pdp.project.entity.User;
import uz.pdp.project.payload.UserProjection;

import java.util.List;
import java.util.UUID;

public interface UniversityRepository extends JpaRepository<University, UUID> {
    List<University> findAllByOrderByNameDesc();

      @Query(value = "select cast(id as varchar) as id,username,phone_number,field,full_name,cast(university_id as varchar) as university_id from users where university_id =:universityId  limit :size offset :page*:size",nativeQuery = true)
      List<UserProjection> getUsersOfUniversityOrderByField(UUID universityId, int page, int size);
/*    @Query(value = "select * from users where cast(university_id as varchar)=:universityId  order by field,username ", nativeQuery = true)
    List<User> getUsersOfUniversityOrderByField(String universityId, Pageable pageable);*/

    @Query(value = "select count(cast(id as varchar)) from users where university_id =:universityId ", nativeQuery = true)
    Integer getUsersOfUniversityOrderByFieldCount(UUID universityId);


    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "DROP Function IF EXISTS hex_to_int(character varying);" +
            "CREATE OR REPLACE FUNCTION hex_to_int(hexval varchar) RETURNS integer AS $$\n" +
            "DECLARE\n" +
            "    result  int;\n" +
            "BEGIN\n" +
            "    EXECUTE 'SELECT x' || quote_literal(hexval) || '::int' INTO result;\n" +
            "    RETURN result;\n" +
            "END;\n" +
            "$$ LANGUAGE plpgsql IMMUTABLE STRICT;",nativeQuery = true)
    void createQueryForHexToDecimal();

}
