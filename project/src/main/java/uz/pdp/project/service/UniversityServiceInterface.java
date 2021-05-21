package uz.pdp.project.service;

import uz.pdp.project.payload.*;

import java.util.List;
import java.util.UUID;

public interface UniversityServiceInterface {
    public List<ResUniversity> getAllUniversities();
    public ResUniversity getOne(UUID id);
    public ApiResponseModel editAndCreateUniversity(ReqUniversity reqUniversity);
    public ApiResponse deleteUniversity(UUID id);
}
