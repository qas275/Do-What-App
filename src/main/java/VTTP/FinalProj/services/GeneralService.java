package VTTP.FinalProj.services;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import VTTP.FinalProj.models.LocationComment;
import VTTP.FinalProj.models.User;
import VTTP.FinalProj.repositories.ApiRepository;
import VTTP.FinalProj.repositories.DatabaseRepository;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;

@Service
public class GeneralService {
    
    @Autowired
    DatabaseRepository dbRepo;
    @Autowired
    ApiRepository aRepo;

    public boolean checkLogin(String email, String password){
        return dbRepo.checkLoginSQL(email, password);
    } 

    public boolean register(String email, String password){
        return dbRepo.registerSQL(email, password);
    } 

    public String search(String keyword){
        if(dbRepo.checkRedis(keyword)){
            System.out.println("Return redis");
            return dbRepo.loadRedis(keyword);
        }else{
            String resp = aRepo.searchRestaurantByKeyword(keyword);
            dbRepo.saveRedis(keyword, resp);
            return resp;
        }
    }

    public int updateFav(User user){
        return dbRepo.updateFav(user);
    }

    public String loadFav(String email){
        return dbRepo.loadFav(email);
    }

    //TODO implement transactional
    public void addComment(String email, String location_id, Integer rating, String comment, MultipartFile image){
        String imageUUID = "";
        try{
            imageUUID = dbRepo.savePictureSpaces(image, email);

        }catch(IOException e){
            e.printStackTrace();
        }
        dbRepo.saveComment(email, location_id, imageUUID, rating, comment);
    }

    public JsonArray loadComment(String locationUUID){
        SqlRowSet rs = dbRepo.loadCommentsByLocationId(locationUUID);
        JsonArrayBuilder jab = Json.createArrayBuilder();
        while(rs.next()){
            jab.add(LocationComment.toCommentJSON(rs));
        }
        return jab.build();
    }

    public int deleteComment(String post_id){
        return dbRepo.deleteComment(post_id);
    }

    @Transactional
    public void deleteUserAndComments(String email){
        
    }
}
