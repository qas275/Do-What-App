package VTTP.FinalProj.models;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

public class User {
    private String email;
    private Location[] favorites;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Location[] getfavorites() {
        return favorites;
    }
    public void setfavorites(Location[] favorites) {
        this.favorites = favorites;
    }


    public static User createUser(JsonObject userJSON){
        User user = new User();
        user.email = userJSON.getString("email");
        JsonArray locations = userJSON.getJsonArray("favorites");
        user.favorites = new Location[locations.size()];
        for(int i=0; i<locations.size();i++){
            user.favorites[i] = Location.createLoc(locations.getJsonObject(i));
        }
        return user;
    }
}
