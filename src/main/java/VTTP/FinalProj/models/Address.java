package VTTP.FinalProj.models;

import jakarta.json.JsonObject;

public class Address {
    private String block;
    private String streetName;
    private String floorNumber;
    private String unitNumber;
    private String buildingName;
    private String postalCode;
    public String getBlock() {
        return block;
    }
    public void setBlock(String block) {
        this.block = block;
    }
    public String getStreetName() {
        return streetName;
    }
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }
    public String getFloorNumber() {
        return floorNumber;
    }
    public void setFloorNumber(String floorNumber) {
        this.floorNumber = floorNumber;
    }
    public String getUnitNumber() {
        return unitNumber;
    }
    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
    public String getBuildingName() {
        return buildingName;
    }
    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public static Address createAddress(JsonObject jsonObject) {
        Address address = new Address();
        address.block = jsonObject.get("block").toString();
        address.streetName = jsonObject.getString("streetName");
        address.floorNumber = jsonObject.getString("floorNumber");
        address.unitNumber = jsonObject.getString("unitNumber");
        address.buildingName = jsonObject.getString("buildingName");
        address.postalCode = jsonObject.getString("postalCode");
        return address;
    }

}
