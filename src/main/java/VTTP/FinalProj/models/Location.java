package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class Location {
    private Double latitude;
    private Double longitude;
    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    public static Location createLocation(JsonObject geoJSON) {
        Location location = new Location();
        location.latitude = Double.parseDouble(geoJSON.get("latitude").toString());
        location.longitude = Double.parseDouble(geoJSON.get("longitude").toString());
        return location;
    }    
}
