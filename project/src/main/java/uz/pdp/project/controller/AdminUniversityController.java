
package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.project.service.AdminUniversityService;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminUniversityController {
    @Autowired
    AdminUniversityService adminUniversityService;
    @GetMapping("/all-student-by-university")
    public  HttpEntity<?> getStundentsOfUniversity(@RequestParam UUID universityId
            ,@RequestParam(defaultValue = "0") int page
            ,@RequestParam(defaultValue = "10") int size,@RequestParam String sortType){
        return ResponseEntity.ok(adminUniversityService.getStundentsOfUniversity(universityId,page,size,sortType));
    }
    @GetMapping("/all-universities")
    public  HttpEntity<?>   getAllUniversities(@RequestParam(defaultValue = "0") int page
            ,@RequestParam(defaultValue = "10") int size,@RequestParam String sortType){
        return  null;
    }

}
