package VTTP.FinalProj.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import VTTP.FinalProj.models.JWTRequest;
import VTTP.FinalProj.models.JWTResponse;
import VTTP.FinalProj.services.JWTAuthService;
import VTTP.FinalProj.services.MailService;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

@RestController
@RequestMapping(path = "/auth")
@CrossOrigin(origins = "*")
public class AuthenticationController {
    
    @Autowired
    private JWTAuthService jwtAuthService;

    @Autowired
    private MailService mailService;

    @PostMapping(path = "/login")
    public ResponseEntity<String> authenticateReq(@RequestBody JWTRequest authenticationRequest) throws Exception {
        // JWTResponse resp = new JWTResponse();
        JsonObjectBuilder job = Json.createObjectBuilder();
        try {
            System.out.println(authenticationRequest.getEmail());
            System.out.println(authenticationRequest.getPassword());
            Optional<JWTResponse> resp = jwtAuthService.authenticate(authenticationRequest); // continue debug here
            if(!resp.isEmpty()){
                System.out.println("HEHEHEHE");
                job.add("login", "true");
                job.add("jwt", resp.get().getJwt());
            }else{
                job.add("login", "false");
            }
        }
        catch (UsernameNotFoundException e) {
            job.add("login", "false");
        }
        return ResponseEntity.status(HttpStatus.OK).body(job.build().toString());
    }

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(@RequestBody MultiValueMap<String, String> body){
        String email = body.getFirst("email");
        String password = body.getFirst("password");
        JsonObjectBuilder job = Json.createObjectBuilder();
        if(jwtAuthService.register(email, password)){

            job.add("registration", "true");
            String subject = "Account Created - %s".formatted(email);
            String emailBody = "Welcome to ZUOMO APP. \n\n Email: %s \n Password: %s ".formatted(email, password);
            mailService.sendEmail(email, subject, emailBody);
        }else{
            job.add("registration", "false");
        }
        String res = job.build().toString();
        System.out.println(res);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
}
