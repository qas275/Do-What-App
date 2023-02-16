package VTTP.FinalProj.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import static VTTP.FinalProj.repositories.Queries.*;

@Repository
public class DatabaseRepository {
    

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    MongoTemplate mongoTemplate;

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
    
}



