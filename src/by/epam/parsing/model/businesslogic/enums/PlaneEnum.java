package by.epam.parsing.model.businesslogic.enums;


public enum PlaneEnum {
    PLANES("planes"),
    CARGO_PLANE("cargo-plane"),
    PASSENGER_PLANE("passenger-plane"),
    MODEL("model"),
    ORIGIN("origin"),
    PARAMETERS("parameters"),
    LENGTH("length"),
    WIDTH("width"),
    HEIGHT("height"),
    WEIGHT_CAPACITY("weight-capacity"),
    CARGO_TYPE("cargo-type"),
    PRICE("price"),
    SEATS_CAPACITY("seats-capacity"),
    ID("id"),
    PASSENGERS_TYPE("passengers-type");

    private String value;
    private PlaneEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
