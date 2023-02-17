package VTTP.FinalProj.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.mongodb.client.result.UpdateResult;

import VTTP.FinalProj.models.User;

import static VTTP.FinalProj.repositories.Queries.*;

@Repository
public class DatabaseRepository {
    

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MongoTemplate mongoTemplate;

    public static final String MONGO_COLLECTION_USERS_DATA = "userfavs";

    public boolean checkLoginSQL(String email, String password){
        SqlRowSet rs = jdbcTemplate.queryForRowSet(LOGIN_CHECK_Q, email, password);
        boolean login = false;
        while(rs.next()){
            login = rs.getInt("exist")>0;
        }
        return login;
    }

    public boolean registerSQL(String email, String password){
        int res = jdbcTemplate.update(REGISTER_Q, email, password);
        return res>0;
    }

    public int saveFav(User user){
        Criteria c = Criteria.where("email").is(user.getEmail());
        Query q = Query.query(c);
        Update update = new Update().set("favorties", user.getfavorites());
        UpdateResult res = mongoTemplate.upsert(q, update, Document.class, MONGO_COLLECTION_USERS_DATA);
        return (int) res.getModifiedCount();
    }
    
}



