package uz.pdp.project.service;

import uz.pdp.project.entity.Region;
import uz.pdp.project.payload.*;

import java.util.List;
import java.util.UUID;

public interface RegionServiceInterface {
    public List<ResRegion> getAllRegions();
    public ResRegion getOne(UUID id);
    public ApiResponseModel editAndCreateRegion(ReqRegion reqRegion);
    public ApiResponse deleteRegion(UUID id);
    public List<Region> getMyRegions(UUID id) ;
}
