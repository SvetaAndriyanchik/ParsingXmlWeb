package by.epam.parsing.model.domain.childplanes;


import by.epam.parsing.model.domain.plane.Plane;

public class PassengerPlane extends Plane {

    private int seatsCapacity;
    private String passengersType;

    public PassengerPlane(){}

    public PassengerPlane(String model, String origin, int length, int height, int width, int seatsCapacity, String passengersType) {
        super(model, origin, length, height, width);
        this.seatsCapacity = seatsCapacity;
        this.passengersType = passengersType;
    }

    public int getSeatsCapacity() {
        return seatsCapacity;
    }

    public void setSeatsCapacity(int seatsCapacity) {
        this.seatsCapacity = seatsCapacity;
    }

    public String getPassengersType() {
        return passengersType;
    }

    public void setPassengersType(String passengersType) {
        this.passengersType = passengersType;
    }

    @Override
    public String toString() {
        String str = String.join(" ",
                super.toString(),
                "Passengers type: ", passengersType,
                "Sears Capacity: ", Integer.toString(seatsCapacity), "\n");
        return str;
    }
}
