package VTTP.FinalProj.controllers;

import java.io.StringReader;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import VTTP.FinalProj.models.User;
import VTTP.FinalProj.services.GeneralService;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

@Controller
@RequestMapping
@CrossOrigin(origins = "*")
public class GeneralController {
    
    @Autowired
    GeneralService gSvc;

    @GetMapping(path = "/login")
    @ResponseBody
    public ResponseEntity<String> checkLogin(@RequestParam String email, @RequestParam String password){
        JsonObjectBuilder job = Json.createObjectBuilder();
        if(gSvc.checkLogin(email, password)){
            job.add("login", "true");
        }else{
            job.add("login", "false");
        }
        return ResponseEntity.status(HttpStatus.OK).body(job.build().toString());
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public ResponseEntity<String> register(@RequestBody MultiValueMap<String, String> body){
        String email = body.getFirst("email");
        String password = body.getFirst("password");
        JsonObjectBuilder job = Json.createObjectBuilder();
        if(gSvc.register(email, password)){
            job.add("registration", "true");
        }else{
            job.add("registration", "false");
        }
        return ResponseEntity.status(HttpStatus.OK).body(job.build().toString());
    }

    @GetMapping(path = "/load")
    @ResponseBody
    public ResponseEntity<String> load(@RequestParam String email){
        String res = gSvc.loadFav(email);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @GetMapping(path = "/search")
    @ResponseBody
    public ResponseEntity<String> searchRestaurant(@RequestParam String keyword){
        String resp = gSvc.search(keyword);
        return ResponseEntity.status(HttpStatus.OK).body(resp);
    }

    @PostMapping(path = "/addFav")
    @ResponseBody
    public ResponseEntity<String> addFavouritePlace(@RequestBody String userJSONString){
        JsonObject userJSON = Json.createReader(new StringReader(userJSONString)).readObject();
        System.out.println(userJSON.toString());
        //TODO saving
        User user = User.createUser(userJSON);
        int res = gSvc.saveFav(user);
        JsonObject jo = Json.createObjectBuilder().add("modified count", res).build();
        return ResponseEntity.status(HttpStatus.OK).body(jo.toString());
    }
}
