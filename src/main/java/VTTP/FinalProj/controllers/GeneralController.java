package VTTP.FinalProj.controllers;

import java.io.StringReader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
    
    
    
    @GetMapping(path = "/load")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> load(@RequestParam String email){
        String res = gSvc.loadFav(email);
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }
    
    @GetMapping(path = "/search")
    @ResponseBody
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> searchRestaurant(@RequestParam String keyword){
        String responseBody = gSvc.search(keyword);
        return ResponseEntity.status(HttpStatus.OK).header("Access-Control-Allow-Origin", "*").body(responseBody);
    }

    @PostMapping(path = "/updateFav")
    @ResponseBody
    public ResponseEntity<String> addFavouritePlace(@RequestBody String userJSONString){
        JsonObject userJSON = Json.createReader(new StringReader(userJSONString)).readObject();
        System.out.println(userJSON.toString());
        //TODO saving
        User user = User.createUser(userJSON);
        int res = gSvc.updateFav(user);
        JsonObject jo = Json.createObjectBuilder().add("modified count", res).build();
        return ResponseEntity.status(HttpStatus.OK).body(jo.toString());
    }

    @PostMapping(path = "/comment",
    consumes=MediaType.MULTIPART_FORM_DATA_VALUE, 
    produces=MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> addComment(@RequestPart (required = false) MultipartFile image,
    @RequestPart String email,
    @RequestPart String location_id,
    @RequestPart String comment,
    @RequestPart String rating
    ){
        gSvc.addComment(email, location_id, Integer.parseInt(rating), comment, image);
        JsonObject jo = Json.createObjectBuilder().add("response", "ok").build();
        return ResponseEntity.status(HttpStatus.OK).body(jo.toString());
    }

    @GetMapping(path = "/getComments/{UUID}")
    @ResponseBody
    public ResponseEntity<String> getComments(@PathVariable String UUID){
        String res = gSvc.loadComment(UUID).toString();
        return ResponseEntity.status(HttpStatus.OK).body(res);
    }

    @DeleteMapping(path = "/deleteComment/{post_id}")
    @ResponseBody
    public ResponseEntity<String> deleteComments(@PathVariable String post_id){
        int res = gSvc.deleteComment(post_id);
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("DELETED COUNT", res);
        JsonObject jo = job.build();
        return ResponseEntity.status(HttpStatus.OK).body(jo.toString());
    }

    @DeleteMapping(path = "/deleteUser")
    @ResponseBody
    public ResponseEntity<String> deleteUser(@RequestParam String email){
        JsonObjectBuilder job = Json.createObjectBuilder();
        try {
            gSvc.deleteUserAndComments(email);
            job.add("response", "ok").build();
        } catch (Exception e) {
            System.out.println("failure to delete");
            job.add("response", e.toString()).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(job.build().toString());
    }


}
