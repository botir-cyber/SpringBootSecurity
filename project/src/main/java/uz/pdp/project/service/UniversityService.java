package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import uz.pdp.project.entity.University;
import uz.pdp.project.entity.User;
import uz.pdp.project.payload.*;
import uz.pdp.project.repository.DistrictRepository;
import uz.pdp.project.repository.UniversityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UniversityService implements UniversityServiceInterface{
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<ResUniversity> getAllUniversities() {
        List<University> allByOrderByNameDesc = universityRepository.findAllByOrderByNameDesc();
        List<ResUniversity> collect = allByOrderByNameDesc.stream().map(ResUniversity::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ResUniversity getOne(UUID id) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()){
            University university=byId.get();
            return new ResUniversity(university);
        } else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUniversity");
        }
    }

    @Override
    public ApiResponseModel editAndCreateUniversity(ReqUniversity reqUniversity) {
        Optional<University> byId = Optional.empty();
        if (reqUniversity.getId()!=null){
            byId=universityRepository.findById(reqUniversity.getId());
        }
        University university;
        if (byId.isPresent()){
            university=byId.get();
        }else {
            if (reqUniversity.getId()!=null){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Id topilmadi");
            }
            university=new University();
        }

        districtRepository.findById(reqUniversity.getDistrictId())
                .ifPresent(university::setDistricts);


        university.setAddress(reqUniversity.getAddress());
        university.setName(reqUniversity.getName());
        University save =universityRepository.save(university);

        return new ApiResponseModel(true,byId.isPresent()?"O`zgartrildi!!!":"Saqlandi",new ResUniversity(save));
    }

    @Override
    public ApiResponse deleteUniversity(UUID id) {
        Optional<University> byId = universityRepository.findById(id);
        if (byId.isPresent()){
            universityRepository.deleteById(id);
            return new ApiResponse(true,"University o`chirildi");
        }else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUniversity");
        }
    }
}
