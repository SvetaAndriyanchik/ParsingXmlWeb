package by.epam.parsing.model.domain.plane;


public abstract class Plane {

    private String model;
    private String origin;
    private int length;
    private int height;
    private int width;
    private String id;

    public Plane() {
    }

    public Plane(String model, String origin, int length, int height, int width) {
        this.model = model;
        this.origin = origin;
        this.length = length;
        this.height = height;
        this.width = width;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public String toString() {
        String str;
        str = String.join(" ", "Model: ", model,
                "ID: ", id,
                "Origin: ", origin,
                "Length: ", Integer.toString(length),
                "Height: ", Integer.toString(height),
                "Width: ", Integer.toString(width)
                );
        return str;
    }
}
