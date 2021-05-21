package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.project.payload.ReqUser;
import uz.pdp.project.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("{id}")
    public HttpEntity<?> getOneUser(@PathVariable(name="id") UUID id){

        return ResponseEntity.ok().body(userService.getOne(id));
    }
    @PostMapping
    public HttpEntity<?> createUser(@RequestBody ReqUser reqUser){
        return ResponseEntity.ok().body(userService.editAndCreateUser(reqUser));
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> deleteUser(@PathVariable UUID id){
        return ResponseEntity.ok().body(userService.deleteUser(id));
    }
}

