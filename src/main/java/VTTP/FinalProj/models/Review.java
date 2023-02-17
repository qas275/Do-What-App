package VTTP.FinalProj.models;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

public class Review {
    private String author;
    private String url;
    private String lang;
    private String profilePhoto;
    private int rating;
    private String text;
    private String time;
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getLang() {
        return lang;
    }
    public void setLang(String lang) {
        this.lang = lang;
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
        review.author = jo.getString("authorName");
        review.url = jo.getString("authorURL");
        review.lang = jo.getString("language");
        review.profilePhoto = jo.getString("profilePhoto");
        review.rating = jo.getInt("rating");
        review.text = jo.getString("text");
        review.time = jo.getString("time");
        return review;
    }


}
