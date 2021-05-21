package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.payload.ReqDistrict;
import uz.pdp.project.payload.ReqUser;
import uz.pdp.project.service.DistrictService;

import java.util.UUID;

@RestController
@RequestMapping("api/district")
public class DistrictController {
    @Autowired
    DistrictService districtService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> getAllDistricts(){
        return ResponseEntity.ok(districtService.getAllDistricts());
    }
    @GetMapping("{id}")
    public HttpEntity<?> getOneDistrict(@PathVariable(name="id") UUID id){

        return ResponseEntity.ok().body(districtService.getOne(id));
    }
    @GetMapping("region/{id}")
    public HttpEntity<?> getDistrictByRegion(@PathVariable(name="id") UUID id){

        return ResponseEntity.ok().body(districtService.getByRegion(id));
    }
    @PostMapping
    public HttpEntity<?> createDistrict(@RequestBody ReqDistrict reqDistrict){
        return ResponseEntity.ok().body(districtService.editAndCreateDistrict(reqDistrict));
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteDistrict(@PathVariable UUID id){
        return ResponseEntity.ok().body(districtService.deleteDistrict(id));
    }
}
