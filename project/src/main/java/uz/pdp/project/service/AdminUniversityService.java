package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.pdp.project.entity.User;
import uz.pdp.project.payload.ResUser;
import uz.pdp.project.payload.UserProjection;
import uz.pdp.project.repository.UniversityRepository;
import uz.pdp.project.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class AdminUniversityService implements AdminUniversityInterface{
    @Autowired
    UserRepository userRepository;
    @Autowired
    UniversityRepository universityRepository;
    @Override
    public Page<?> getStundentsOfUniversity(UUID universityId, int page, int size, String sortType) {
       Page<ResUser> resUsers=null;
        Page<User>  users;
        switch (sortType){
            case "byFullName":
               users = userRepository
                        .getUserByUniversityId(universityId, PageRequest.of(page, size));
                resUsers = users.map(ResUser::new);
                break;
            case "byUsername":
                users = userRepository
                        .findAllByUniversity_IdOrderByUsername(universityId, PageRequest.of(page, size));
                resUsers = users.map(ResUser::new);
                break;
            case "byRegionId":
             //   users = userRepository
               //         .findAllByUniversity_Districts_Region_Id_OrderByUsername(universityId, PageRequest.of(page, size));
                users = userRepository
                        .getUserByRegionId(universityId, PageRequest.of(page, size));
                resUsers = users.map(ResUser::new);
                break;
            case "byField":

                List<UserProjection> usersOfUniversityOrderByField = universityRepository.getUsersOfUniversityOrderByField(universityId,page,size);
               Integer usersOfUniversityOrderByFieldCount =
                        universityRepository.getUsersOfUniversityOrderByFieldCount(universityId);

                return new PageImpl<UserProjection>(usersOfUniversityOrderByField,
                        PageRequest.of(page, size),usersOfUniversityOrderByFieldCount);

            default:
                users = userRepository
                        .findAllByUniversity_IdOrderByUsername(universityId, PageRequest.of(page, size));
                resUsers = users.map(ResUser::new);
        }

        return resUsers;
    }
}
