package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.payload.ReqUniversity;
import uz.pdp.project.payload.ReqUser;
import uz.pdp.project.service.UniversityService;

import java.util.UUID;

@RestController
@RequestMapping("api/university")
public class UniversityController {
    @Autowired
    UniversityService universityService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> getAllUniversities(){
        return ResponseEntity.ok(universityService.getAllUniversities());
    }
    @GetMapping("{id}")
    public HttpEntity<?> getOneUniversity(@PathVariable(name="id") UUID id){

        return ResponseEntity.ok().body(universityService.getOne(id));
    }
    @PostMapping
    public HttpEntity<?> createUniversity(@RequestBody ReqUniversity reqUniversity){
        return ResponseEntity.ok().body(universityService.editAndCreateUniversity(reqUniversity));
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteUniversity(@PathVariable UUID id){
        return ResponseEntity.ok().body(universityService.deleteUniversity(id));
    }
}
