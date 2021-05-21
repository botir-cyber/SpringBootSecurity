package uz.pdp.project.service;

import uz.pdp.project.payload.*;

import java.util.List;
import java.util.UUID;

public interface DistrictServiceInterface {
    public List<ResDistrict> getAllDistricts();
    public List<ResDistrict> getByRegion(UUID id);
    public ResDistrict getOne(UUID id);
    public ApiResponseModel editAndCreateDistrict(ReqDistrict reqDistrict);
    public ApiResponse deleteDistrict(UUID id);
}
