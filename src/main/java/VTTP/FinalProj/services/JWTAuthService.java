package VTTP.FinalProj.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import VTTP.FinalProj.models.JWTRequest;
import VTTP.FinalProj.models.JWTResponse;
import VTTP.FinalProj.models.User;
import VTTP.FinalProj.repositories.DatabaseRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JWTAuthService {

    private final DatabaseRepository dbRepo;
    private final JWTUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public Optional<JWTResponse> authenticate(JWTRequest request){
        System.out.println("WITHIN SERVICE "+request.getPassword());
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            // TODO: handle exception
            return Optional.empty();
        }
        System.out.println("AFTER AUTH");
        Optional<User> optUser = dbRepo.loadUser(request.getEmail());
        System.out.println(optUser.get().getEmail());
        String JWT = jwtUtil.generateToken(optUser.get());
        return Optional.of(JWTResponse.builder()
            .jwt(JWT)
            .build());
    }

    public boolean checkLogin(String email, String password){
        return dbRepo.checkLoginSQL(email, passwordEncoder.encode(password));
    } 

    public boolean register(String email, String password){
        return dbRepo.registerSQL(email, passwordEncoder.encode(password));
    } 
}