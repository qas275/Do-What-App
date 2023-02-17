package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class GeoCode {
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
    public static GeoCode createGeoCode(JsonObject geoJSON) {
        GeoCode geoCode = new GeoCode();
        geoCode.latitude  =geoJSON.getString("latitude");
        geoCode.longitude  =geoJSON.getString("longitude");
        return geoCode;
    }    
}
