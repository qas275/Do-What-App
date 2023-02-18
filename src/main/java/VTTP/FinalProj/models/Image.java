package VTTP.FinalProj.models;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Image {
    private String uuid;
    private String url;
    private String libraryUuid;
    private String primaryFileMediumUuid;
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
    public String getLibraryUuid() {
        return libraryUuid;
    }
    public void setLibraryUuid(String libraryUuid) {
        this.libraryUuid = libraryUuid;
    }
    public String getPrimaryFileMediumUuid() {
        return primaryFileMediumUuid;
    }
    public void setPrimaryFileMediumUuid(String primaryFileMediumUuid) {
        this.primaryFileMediumUuid = primaryFileMediumUuid;
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
        image.libraryUuid = jo.getString("libraryUuid");
        image.primaryFileMediumUuid = jo.getString("primaryFileMediumUuid");
        return image;
    }
    

}
