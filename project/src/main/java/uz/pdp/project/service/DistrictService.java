package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import uz.pdp.project.entity.District;
import uz.pdp.project.entity.User;
import uz.pdp.project.payload.*;
import uz.pdp.project.repository.DistrictRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DistrictService implements DistrictServiceInterface{
    @Autowired
    DistrictRepository districtRepository;
    @Override
    public List<ResDistrict> getAllDistricts() {
        List<District> allByOrderByNameDesc = districtRepository.findAllByOrderByNameDesc();
        List<ResDistrict> collect = allByOrderByNameDesc.stream().map(ResDistrict::new).collect(Collectors.toList());
        return collect;
    }
    @Override
    public List<ResDistrict> getByRegion(UUID id) {
        List<District> allByOrderByNameDesc = districtRepository.findAllByRegion_Id(id);
        List<ResDistrict> collect = allByOrderByNameDesc.stream().map(ResDistrict::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ResDistrict getOne(UUID id) {
        Optional<District> byId = districtRepository.findById(id);
        if (byId.isPresent()){
            District district = byId.get();
            return new ResDistrict(district);
        }else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }

    @Override
    public ApiResponseModel editAndCreateDistrict(ReqDistrict reqDistrict) {
        Optional<District> byId =Optional.empty();
        if (reqDistrict.getId()!=null){
            byId=districtRepository.findById(reqDistrict.getId());
        }
        District district;
        if (byId.isPresent()){
            district = byId.get();
        }else {
            if (reqDistrict.getId()!=null){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Id topilmadi");
            }
            district=new District();
        }
        district.setName(reqDistrict.getName());

        District save = districtRepository.save(district);

        return new ApiResponseModel(true,byId.isPresent()?"O`zgartrildi!!!":"Saqlandi",new ResDistrict(save));
    }

    @Override
    public ApiResponse deleteDistrict(UUID id) {
        Optional<District> byId = districtRepository.findById(id);
        if (byId.isPresent()){
            districtRepository.deleteById(id);
            return new ApiResponse(true,"District o`chirildi");
        }else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateUser");
        }
    }
}
