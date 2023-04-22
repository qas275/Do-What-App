package VTTP.FinalProj.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.mongodb.client.result.UpdateResult;
import VTTP.FinalProj.models.User;
import jakarta.json.Json;
import jakarta.json.JsonObjectBuilder;

import static VTTP.FinalProj.repositories.Queries.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DatabaseRepository {
    

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    AmazonS3 s3Client;

    @Autowired
    @Qualifier("redis_results_cache")
    RedisTemplate<String, String> redisTemplate;

    public static final String MONGO_COLLECTION_USERS_DATA = "userfavs";

    public boolean checkLoginSQL(String email, String password){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(LOGIN_CHECK_Q, email, password);
        boolean login = false;
        while(rs.next()){
            login = rs.getInt("exist")>0;
        }
        return login;
    }

    public Optional<User> loadUser(String email){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(LOGIN_Q, email);
        rs.next();
        Optional<User> user  = User.createUser(rs);
        return user;
    }

    public boolean registerSQL(String email, String password){
        System.out.println(email);
        int res = jdbcTemplate.update(REGISTER_Q, email, password);
        System.out.println("HELLO"+res);
        return res>0;
    }

    public int updateFav(User user){
        Criteria c = Criteria.where("email").is(user.getEmail());
        Query q = Query.query(c);
        Update update = new Update().set("favorites", user.getfavorites()).set("numFavs", user.getNumFavs());
        UpdateResult res = mongoTemplate.upsert(q, update, Document.class, MONGO_COLLECTION_USERS_DATA);
        return (int) res.getModifiedCount();
    }
    
    
    public String loadFav(String email){
        Criteria c = Criteria.where("email").is(email);
        Query q = Query.query(c);
        List<Document> res = mongoTemplate.find(q, Document.class, MONGO_COLLECTION_USERS_DATA);
        if(!res.isEmpty()){
            return res.get(0).toJson();
        }else{
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("email", email);
            job.add("numFavs", 0);
            return job.build().toString();
        }
    }

    public boolean checkRedis(String keyword){
        System.out.println(keyword);
        boolean res= redisTemplate.hasKey(keyword);
        return res;
    }

    public String loadRedis(String keyword){
        return redisTemplate.opsForValue().get(keyword);        
    }

    public boolean saveRedis(String keyword, String resp){
        return redisTemplate.opsForValue().setIfAbsent(keyword, resp, Duration.ofMinutes(60));
    }

    public int saveComment(String email, String location_id, String imageUUID, Integer rating, String comment){
        int res =  jdbcTemplate.update(ADD_COMMENT, email, location_id, comment, rating, imageUUID);
        return res;
    }
    
    public SqlRowSet loadCommentsByLocationId(String location_id){
        SqlRowSet res = jdbcTemplate.queryForRowSet(GET_COMMENTS_BY_LOCATION_ID, location_id);
        return res;
    }

    public int deleteComment(String post_id){
        return jdbcTemplate.update(DELETE_COMMENT, post_id);
    }

    public int deleteUser(String email){
        return jdbcTemplate.update(DELETE_USER, email);
    }

    public int deleteUserComments(String email){
        return jdbcTemplate.update(DELETE_COMMENTS_BY_USER, email);
    }

    public Optional<String> savePictureSpaces(MultipartFile image, String email) throws IOException{
        //set user data
        if(null==image){
            return Optional.empty();
        }
        Map<String, String> userData = new HashMap<>();
        userData.put("email",email);

        //set metadata of file so later when retrieving can understand
        userData.put("uploadTime",new Date().toString());
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(image.getSize());
        metadata.setContentType(image.getContentType());
        metadata.setUserMetadata(userData);

        //create random uuid key for object
        String key = UUID.randomUUID().toString().substring(0,8);
        boolean duplicate = true;
        while(duplicate){
            SqlRowSet rs = jdbcTemplate.queryForRowSet(CHECK_IMAGES, key);
            rs.next();
            duplicate = rs.getInt(1) > 0;
            if(duplicate){
                key = UUID.randomUUID().toString().substring(0,8);
            }
        }
        //create a upload request
        PutObjectRequest putReq = new PutObjectRequest("vttpws", "todo/%s".formatted(key), image.getInputStream(),metadata);//bucket name, key of (directory/object key in this case)
        //allow public access to read only
        putReq.withCannedAcl(CannedAccessControlList.PublicRead);
        
        //use client to send specified request
        s3Client.putObject(putReq);

        return Optional.of(key);
    }

    public void deletePictureSpaces(String post_id) throws IOException{
        SqlRowSet rs = jdbcTemplate.queryForRowSet(GET_IMG_URL, post_id);

        rs.next();

        String imageUUID = rs.getString("uuid");

        //create delete req
        DeleteObjectRequest delReq = new DeleteObjectRequest("vttpws", "todo/%s".formatted(imageUUID));//bucket name, key of (directory/object key in this case)
        
        //use client to send specified request
        s3Client.deleteObject(delReq);
    }
    
    
    
}



