package uz.pdp.project.service;

import org.springframework.data.domain.Page;
import uz.pdp.project.payload.ResUser;

import java.util.UUID;

public interface AdminUniversityInterface {
     Page<?> getStundentsOfUniversity(
            UUID universityId, int page
            , int size,String sortType);
}
