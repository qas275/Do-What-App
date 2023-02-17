package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class Address {
    private int block;
    private String street;
    private String floor;
    private String unit;
    private String building;
    private int postal;
    public int getBlock() {
        return block;
    }
    public void setBlock(int block) {
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
    public int getPostal() {
        return postal;
    }
    public void setPostal(int postal) {
        this.postal = postal;
    }
    public static Address createAddress(JsonObject jsonObject) {
        Address address = new Address();
        address.block = jsonObject.getInt("block");
        address.street = jsonObject.getString("streetName");
        address.floor = jsonObject.getString("floorNumber");
        address.unit = jsonObject.getString("unitNumber");
        address.building = jsonObject.getString("buildingName");
        address.postal = jsonObject.getInt("postalCode");
        return address;
    }

}
