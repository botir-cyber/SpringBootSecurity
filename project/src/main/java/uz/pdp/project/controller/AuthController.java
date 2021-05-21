package uz.pdp.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import uz.pdp.project.entity.User;
import uz.pdp.project.payload.ReqSignUp;
import uz.pdp.project.security.AuthService;
import uz.pdp.project.security.CurrentUser;

@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("register")
    public HttpEntity<?> register(@RequestBody ReqSignUp reqSignUp){
        return authService.register(reqSignUp);
    }
    @PostMapping("login")
    public HttpEntity<?> login(@RequestBody ReqSignUp reqSignUp){
        return ResponseEntity.ok(authService.login(reqSignUp.getPhoneNumber(),reqSignUp.getPassword()));
    }
    @GetMapping("me")
    public HttpEntity<?> userMe(@CurrentUser User user){
        return  ResponseEntity.ok(user);
    }
}
