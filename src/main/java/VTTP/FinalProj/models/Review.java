package VTTP.FinalProj.models;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Review {
    private String authorName;
    private String authorURL;
    private String language;
    private String profilePhoto;
    private int rating;
    private String text;
    private String time;
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public String getAuthorURL() {
        return authorURL;
    }
    public void setAuthorURL(String authorURL) {
        this.authorURL = authorURL;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public String getProfilePhoto() {
        return profilePhoto;
    }
    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public static Review[] createReviews(JsonArray jsonArray) {
        Review[] reviews = new Review[jsonArray.size()];
        for(int i = 0;i<jsonArray.size();i++){
            reviews[i] = Review.createReview(jsonArray.getJsonObject(i));
        }
        return reviews;
    }

    public static Review createReview(JsonObject jo){
        Review review = new Review();
        review.authorName = jo.getString("authorName");
        review.authorURL = jo.getString("authorURL");
        review.language = jo.getString("language");
        review.profilePhoto = jo.getString("profilePhoto");
        review.rating = jo.getInt("rating");
        review.text = jo.getString("text");
        review.time = jo.getString("time");
        return review;
    }


}
