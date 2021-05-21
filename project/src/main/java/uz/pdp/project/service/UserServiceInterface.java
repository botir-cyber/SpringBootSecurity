package uz.pdp.project.service;

import uz.pdp.project.payload.ApiResponse;
import uz.pdp.project.payload.ApiResponseModel;
import uz.pdp.project.payload.ReqUser;
import uz.pdp.project.payload.ResUser;

import java.util.List;
import java.util.UUID;

public interface UserServiceInterface {
    public List<ResUser> getAllUsers();
    public ResUser getOne(UUID id);
    public ApiResponseModel editAndCreateUser(ReqUser reqUser);
    public ApiResponse deleteUser(UUID id);
}
