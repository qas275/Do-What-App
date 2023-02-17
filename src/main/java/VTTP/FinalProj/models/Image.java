package VTTP.FinalProj.models;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Image {
    private String uuid;
    private String url;
    private String libUUID;
    private String fileMedium;
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getLibUUID() {
        return libUUID;
    }
    public void setLibUUID(String libUUID) {
        this.libUUID = libUUID;
    }
    public String getFileMedium() {
        return fileMedium;
    }
    public void setFileMedium(String fileMedium) {
        this.fileMedium = fileMedium;
    }
    public static Image[] createImages(JsonArray jsonArray) {
        Image[] images = new Image[jsonArray.size()];
        for(int i = 0;i<jsonArray.size();i++){
            images[i] = Image.createImage(jsonArray.getJsonObject(i));
        }
        return images;
    }

    public static Image createImage(JsonObject jo){
        Image image = new Image();
        image.uuid = jo.getString("uuid");
        image.url = jo.getString("url");
        image.libUUID = jo.getString("libraryUuid");
        image.fileMedium = jo.getString("primaryFileMediumUuid");
        return image;
    }
    

}
