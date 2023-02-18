package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class Location {
    private String latitude;
    private String longitude;
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public static Location createLocation(JsonObject geoJSON) {
        Location location = new Location();
        location.latitude  =geoJSON.get("latitude").toString();
        location.longitude  =geoJSON.get("longitude").toString();
        return location;
    }    
}
