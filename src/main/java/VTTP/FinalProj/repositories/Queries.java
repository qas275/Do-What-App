package VTTP.FinalProj.repositories;

public class Queries {
    public static final String LOGIN_CHECK_Q = "select (count(email)>0) as exist from users where BINARY email = ? and BINARY password = ?";
    public static final String REGISTER_Q = "insert ignore into users(email, password) values (?, ?)";
}
