package uz.pdp.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import uz.pdp.project.entity.Region;
import uz.pdp.project.payload.*;
import uz.pdp.project.repository.RegionRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegionService implements RegionServiceInterface{
    @Autowired
    RegionRepository regionRepository;

    @Override
    public List<ResRegion> getAllRegions() {
        List<Region> allByOrderByNameDesc = regionRepository.findAllByOrderByFieldDesc();
        List<ResRegion> collect = allByOrderByNameDesc.stream().map(ResRegion::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public ResRegion getOne(UUID id) {
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent()){
            Region region = byId.get();
            return new ResRegion(region);
        }else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateRegion");
        }
    }

    @Override
    public ApiResponseModel editAndCreateRegion(ReqRegion reqRegion) {
        Optional<Region> byId =Optional.empty();
        if (reqRegion.getId()!=null){
            byId=regionRepository.findById(reqRegion.getId());
        }
        Region region;
        if (byId.isPresent()){
            region = byId.get();
        }else {
            if (reqRegion.getId()!=null){
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,"Id topilmadi");
            }
            region=new Region();
        }
        region.setField(reqRegion.getField());
        Region save = regionRepository.save(region);

        return new ApiResponseModel(true,byId.isPresent()?"O`zgartrildi!!!":"Saqlandi",new ResRegion(save));

    }

    @Override
    public ApiResponse deleteRegion(UUID id) {
        Optional<Region> byId = regionRepository.findById(id);
        if (byId.isPresent()){
            regionRepository.deleteById(id);
            return new ApiResponse(true,"Region o`chirildi");
        }else {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST,
                    "Missing the required parameter 'id' when calling updateRegion");
        }
    }
    @Override
    public List<Region> getMyRegions(UUID id) {
      return  regionRepository.findAllByCreatedByOrderByCreatedAtDesc(id);
    }
}
