package uz.pdp.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.project.entity.Role;
import uz.pdp.project.entity.enums.RoleEnum;

import java.util.List;


public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAllByName(RoleEnum name);
}
