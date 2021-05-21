package uz.pdp.project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import uz.pdp.project.entity.User;
import uz.pdp.project.entity.enums.RoleEnum;
import uz.pdp.project.payload.ReqSignUp;
import uz.pdp.project.repository.RoleRepository;
import uz.pdp.project.repository.UserRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtTokenProvider tokenProvider;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (userRepository.existsByUsername(s)) {
            return userRepository.findByUsername(s).get();

        } else if (userRepository.existsByPhoneNumber(s)) {
            return userRepository.findByPhoneNumber(s).get();
        }
        throw new UsernameNotFoundException(s);
    }

    public HttpEntity<?> register(ReqSignUp reqSignUp) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(reqSignUp.getPhoneNumber());
        if (optionalUser.isPresent()) {
            return ResponseEntity.status(403).body( "Bunday telefon raqamli foydalanuvchi ro'yxatdan o'tgan!");
        } else {
            userRepository.save(
                    new User(
                            passwordEncoder.encode(reqSignUp.getPassword()),
                            reqSignUp.getUsername(),
                            reqSignUp.getPhoneNumber(),
                            roleRepository.findAllByName(RoleEnum.USER)));

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(reqSignUp.getUsername(), reqSignUp.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = tokenProvider.generateTokenNotRemember(authentication);
            return ResponseEntity.ok( token);
        }
    }
    public String login(String phoneNumber, String password){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateTokenNotRemember(authentication);
        return token;
    }

    public UserDetails loadUserById(UUID fromString) {
        Optional<User> byId = userRepository.findById(fromString);
        if (byId.isPresent()){
            return  byId.get();
        }else {
            throw new UsernameNotFoundException(fromString.toString());
        }
    }
}
