package VTTP.FinalProj.repositories;

public class Queries {
    public static final String LOGIN_CHECK_Q = "select (count(email)>0) as exist from users where BINARY email = ? and BINARY password = ?";
    public static final String LOGIN_Q = "select * from users where BINARY email = ?";
    public static final String REGISTER_Q = "insert ignore into users(email, password) values (?, ?)";
    public static final String ADD_COMMENT = "insert into comments(user_email, location_id, comments, rating, image_url) values(?,?,?,?,?)";
    public static final String GET_COMMENTS_BY_LOCATION_ID = "select * from comments where location_id = ?";
    public static final String DELETE_COMMENT = "delete from comments where post_id=?";
    public static final String DELETE_USER = "delete from users where email=?";
    public static final String DELETE_COMMENTS_BY_USER = "delete from comments where user_email=?";
    public static final String CHECK_IMAGES = "select count(image_url)>0 as dup from comments where image_url = ?";
    public static final String GET_IMG_URL = "select post_id, image_url as uuid from comments where post_id = ?";
} 