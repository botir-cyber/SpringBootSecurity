package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.aop.anatation.CheckUser;
import uz.pdp.project.entity.User;
import uz.pdp.project.payload.ReqRegion;
import uz.pdp.project.security.CurrentUser;
import uz.pdp.project.service.RegionService;

import java.util.UUID;

@RestController
@RequestMapping("api/region")
public class RegionController {
    @Autowired
    RegionService regionService;


    @RequestMapping(method = RequestMethod.GET)
    @CheckUser(roles = "ADMIN")
    public HttpEntity<?> getAllRegions() {
        return ResponseEntity.ok(regionService.getAllRegions());
    }

    @GetMapping("{id}")
    public HttpEntity<?> getOneRegion(@PathVariable(name = "id") UUID id) {

        return ResponseEntity.ok().body(regionService.getOne(id));
    }

    @PostMapping
    public HttpEntity<?> createRegion(@RequestBody ReqRegion reqRegion) {
        return ResponseEntity.ok().body(regionService.editAndCreateRegion(reqRegion));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("my")
    public HttpEntity<?> getCreatedREgionsByMe(@CurrentUser User user) {
        return ResponseEntity.ok(regionService.getMyRegions(user.getId()));
    }

    @DeleteMapping("{id}")
    public HttpEntity<?> deleteRegion(@PathVariable UUID id) {
        return ResponseEntity.ok().body(regionService.deleteRegion(id));
    }
}
