package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class Address {
    private String block;
    private String street;
    private String floor;
    private String unit;
    private String building;
    private String postal;
    public String getBlock() {
        return block;
    }
    public void setBlock(String block) {
        this.block = block;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getFloor() {
        return floor;
    }
    public void setFloor(String floor) {
        this.floor = floor;
    }
    public String getUnit() {
        return unit;
    }
    public void setUnit(String unit) {
        this.unit = unit;
    }
    public String getBuilding() {
        return building;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
    public String getPostal() {
        return postal;
    }
    public void setPostal(String postal) {
        this.postal = postal;
    }
    public static Address createAddress(JsonObject jsonObject) {
        Address address = new Address();
        address.block = jsonObject.get("block").toString();
        address.street = jsonObject.getString("streetName");
        address.floor = jsonObject.getString("floorNumber");
        address.unit = jsonObject.getString("unitNumber");
        address.building = jsonObject.getString("buildingName");
        address.postal = jsonObject.getString("postalCode");
        return address;
    }

}
