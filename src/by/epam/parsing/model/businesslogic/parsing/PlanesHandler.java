package by.epam.parsing.model.businesslogic.parsing;



import by.epam.parsing.model.businesslogic.enums.PlaneEnum;
import by.epam.parsing.model.domain.childplanes.CargoPlane;
import by.epam.parsing.model.domain.childplanes.PassengerPlane;
import by.epam.parsing.model.domain.plane.Plane;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class PlanesHandler extends DefaultHandler {
    private Set<Plane> planes;
    private Plane current = null;
    private PlaneEnum currentEnum = null;
    private EnumSet<PlaneEnum> parametersEnum;
    private EnumSet<PlaneEnum> classesEnums;

    public PlanesHandler() {
        planes = new HashSet<Plane>();
        classesEnums = EnumSet.range(PlaneEnum.PLANES, PlaneEnum.PASSENGER_PLANE);
        parametersEnum = EnumSet.range(PlaneEnum.MODEL, PlaneEnum.PASSENGERS_TYPE);

    }
    public Set<Plane> getPlanes() {
        return planes;
    }
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        PlaneEnum planeEnum = PlaneEnum.valueOf(localName.replaceAll("-", "_").toUpperCase());
        if(classesEnums.contains(planeEnum)){
            switch(planeEnum){
                case CARGO_PLANE:
                    current = new CargoPlane();
                    current.setId(attrs.getValue(0));
                    break;
                case PASSENGER_PLANE:
                    current = new PassengerPlane();
                    current.setId(attrs.getValue(0));
                    break;
            }
        }
        else{
            currentEnum = planeEnum;
        }

    }
    public void endElement(String uri, String localName, String qName) {
        PlaneEnum planeEnum = PlaneEnum.valueOf(localName.replaceAll("-", "_").toUpperCase());
        if (classesEnums.contains(planeEnum)) {
            planes.add(current);
        }
    }

    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length).trim();
        if (currentEnum != null) {
            switch (currentEnum) {
                case MODEL:
                    current.setModel(s);
                    break;
                case ORIGIN:
                    current.setOrigin(s);
                    break;
                case HEIGHT:
                    current.setHeight(Integer.parseInt(s));
                    break;
                case WIDTH:
                    current.setWidth(Integer.parseInt(s));
                    break;
                case LENGTH:
                    current.setLength(Integer.parseInt(s));
                    break;
                case WEIGHT_CAPACITY:
                    if(current instanceof CargoPlane) {
                        ((CargoPlane)current).setWeightCapacity(Integer.parseInt(s));
                    }
                    break;
                case CARGO_TYPE:
                    if(current instanceof CargoPlane) {
                        ((CargoPlane)current).setCargoType(s);
                    }
                    break;
                case PASSENGERS_TYPE:
                    if(current instanceof PassengerPlane){
                        ((PassengerPlane)current).setPassengersType(s);
                    }
                    break;
                case SEATS_CAPACITY:
                    if(current instanceof PassengerPlane){
                        ((PassengerPlane)current).setSeatsCapacity(Integer.parseInt(s));
                    }
                default:
                    break;
            }
        }
        currentEnum = null;
    }
}
