package VTTP.FinalProj.models;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class LocationComment {
    private String comment;
    private Integer rating;
    private String email;
    private String imageUUID;
    private String locationUUID;
    private String post_id;
    public String getPost_id() {
        return post_id;
    }
    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getImageUUID() {
        return imageUUID;
    }
    public void setImageUUID(String imageUUID) {
        this.imageUUID = imageUUID;
    }
    public String getLocationUUID() {
        return locationUUID;
    }
    public void setLocationUUID(String locationUUID) {
        this.locationUUID = locationUUID;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public Integer getRating() {
        return rating;
    }
    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public static LocationComment createComment(SqlRowSet rs){
        LocationComment comment = new LocationComment();
        comment.comment = rs.getString("comments");
        comment.email = rs.getString("email");
        comment.imageUUID = rs.getString("image_uuid");
        comment.locationUUID = rs.getString("location_id");
        comment.rating = rs.getInt("rating");
        return comment;
    }

    public static JsonObject toCommentJSON(SqlRowSet rs){
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("post_id", rs.getString("post_id"));
        job.add("comment", rs.getString("comments"));
        job.add("email", rs.getString("user_email"));
        job.add("rating", rs.getInt("rating"));
        job.add("imageUUID", rs.getString("image_url"));
        job.add("locationUUID", rs.getString("location_id"));
        JsonObject comment = job.build();
        return comment;
    }
}
