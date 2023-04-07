package VTTP.FinalProj.models;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails{
    private String email;
    private String password; //need to implement in angular
    private int numFavs;
    private TIHLocation[] favorites;
    private Role role;
    public int getNumFavs() {
        return numFavs;
    }
    public void setNumFavs(int numFavs) {
        this.numFavs = numFavs;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public TIHLocation[] getfavorites() {
        return favorites;
    }
    public void setfavorites(TIHLocation[] favorites) {
        this.favorites = favorites;
    }


    public static User createUser(JsonObject userJSON){
        User user = new User();
        user.role = Role.USER;
        user.email = userJSON.getString("email");
        JsonArray locations = userJSON.getJsonArray("favorites");
        user.favorites = new TIHLocation[locations.size()];
        user.numFavs = userJSON.getInt("numFavs");
        for(int i=0; i<locations.size();i++){
            System.out.println(i+ " \n\n\n\n");
            System.out.println("WHAT IS LOCATION \n\n\n\n");
            System.out.println(locations.getJsonObject(i));
            user.favorites[i] = TIHLocation.createLoc(locations.getJsonObject(i));
            System.out.println(user.favorites[i].getLocation().getLatitude());
            
        }
        return user;
    }
    public static Optional<User> createUser(SqlRowSet userSQL){
        User user = new User();
        user.role = Role.USER;
        user.email = userSQL.getString("email");
        user.password = userSQL.getString("password");
        return Optional.of(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public boolean isAccountNonExpired() {
        //TODO checks
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        //TODO checks
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        //TODO checks
        return true;
    }
    @Override
    public boolean isEnabled() {
        // TODO checks
        return true;
    }
}
