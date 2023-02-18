package VTTP.FinalProj.models;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class User {
    private String email;
    private int numFavs;
    private TIHLocation[] favorites;
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
}
