package by.epam.parsing.model.domain.childplanes;


import by.epam.parsing.model.domain.plane.Plane;

public class CargoPlane extends Plane {

    private String cargoType;
    private int weightCapacity;

    public CargoPlane() {
    }

    public CargoPlane(String model, String origin, int length, int height, int width, String cargoType, int weightCapacity) {
        super(model, origin, length, height, width);
        this.cargoType = cargoType;
        this.weightCapacity = weightCapacity;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public int getWeightCapacity() {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity) {
        this.weightCapacity = weightCapacity;
    }

    @Override
    public String toString() {
        String str;
        str = String.join(" ",
                super.toString(),
                "Type of cargo: ", cargoType,
                "Weigth capacity: ", Integer.toString(weightCapacity), "\n");
        return str;
    }
}
