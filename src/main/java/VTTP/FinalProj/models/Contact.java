package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class Contact {
    private String primaryContactNo;

    public String getContact() {
        return primaryContactNo;
    }

    public void setContact(String primaryContactNo) {
        this.primaryContactNo = primaryContactNo;
    }

    public static Contact createContact(JsonObject jsonObject) {
        Contact contact = new Contact();
        contact.primaryContactNo = jsonObject.getString("primaryContactNo");
        return contact;
    }
}
