package by.epam.parsing.model.businesslogic.parsing;


import by.epam.parsing.model.businesslogic.builder.AbstractPlanesBuilder;
import by.epam.parsing.model.businesslogic.enums.PlaneEnum;
import by.epam.parsing.model.domain.childplanes.CargoPlane;
import by.epam.parsing.model.domain.childplanes.PassengerPlane;
import by.epam.parsing.model.domain.plane.Plane;
import org.apache.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PlanesStAXBuilder extends AbstractPlanesBuilder {

    private HashSet<Plane> planes = new HashSet<Plane>();
    private XMLInputFactory inputFactory;
    private static Logger log = Logger.getLogger(PlanesStAXBuilder.class);

    public PlanesStAXBuilder() {
        inputFactory = XMLInputFactory.newInstance();
    }

    public Set<Plane> getPlanes() {
        return planes;
    }
    public void buildSetPlanes(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (PlaneEnum.valueOf(name.replaceAll("-", "_").toUpperCase()) == PlaneEnum.CARGO_PLANE) {
                        CargoPlane cargoPlane = buildCargoPlane(reader);
                        planes.add(cargoPlane);
                    }

                    if (PlaneEnum.valueOf(name.replaceAll("-", "_").toUpperCase()) == PlaneEnum.PASSENGER_PLANE) {
                        PassengerPlane passengerPlane = buildPassengerPlane(reader);
                        planes.add(passengerPlane);
                    }
                }
            }
        } catch (XMLStreamException ex) {
            log.error("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            log.error("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                log.error("Impossible close file "+fileName+" : "+e);
            }
        }
    }

    private CargoPlane buildCargoPlane(XMLStreamReader reader) throws XMLStreamException {
        CargoPlane cargoPlane = new CargoPlane();
        cargoPlane.setId(reader.getAttributeValue(null, PlaneEnum.ID.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlaneEnum.valueOf(name.replaceAll("-", "_").toUpperCase())) {

                        case MODEL:
                            cargoPlane.setModel(getXMLText(reader));
                            break;
                        case ORIGIN:
                            cargoPlane.setOrigin(getXMLText(reader));
                            break;
                        case HEIGHT:
                            name = getXMLText(reader);
                            cargoPlane.setHeight(Integer.parseInt(name));
                            break;
                        case WIDTH:
                            name = getXMLText(reader);
                            cargoPlane.setWidth(Integer.parseInt(name));
                            break;
                        case LENGTH:
                            name = getXMLText(reader);
                            cargoPlane.setLength(Integer.parseInt(name));
                            break;
                        case WEIGHT_CAPACITY:
                            name = getXMLText(reader);
                            cargoPlane.setWeightCapacity(Integer.parseInt(name));
                            break;
                        case CARGO_TYPE:
                            cargoPlane.setCargoType(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlaneEnum.valueOf(name.replaceAll("-", "_").toUpperCase()) ==
                            PlaneEnum.CARGO_PLANE) {
                        return cargoPlane;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag");
    }

    private PassengerPlane buildPassengerPlane(XMLStreamReader reader) throws XMLStreamException {
        PassengerPlane passengerPlane = new PassengerPlane();
        passengerPlane.setId(reader.getAttributeValue(null, PlaneEnum.ID.getValue()));
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (PlaneEnum.valueOf(name.replaceAll("-", "_").toUpperCase())) {

                        case MODEL:
                            passengerPlane.setModel(getXMLText(reader));
                            break;
                        case ORIGIN:
                            passengerPlane.setOrigin(getXMLText(reader));
                            break;
                        case HEIGHT:
                            name = getXMLText(reader);
                            passengerPlane.setHeight(Integer.parseInt(name));
                            break;
                        case WIDTH:
                            name = getXMLText(reader);
                            passengerPlane.setWidth(Integer.parseInt(name));
                            break;
                        case LENGTH:
                            name = getXMLText(reader);
                            passengerPlane.setLength(Integer.parseInt(name));
                            break;
                        case SEATS_CAPACITY:
                            name = getXMLText(reader);
                            passengerPlane.setSeatsCapacity(Integer.parseInt(name));
                            break;
                        case PASSENGERS_TYPE:
                            passengerPlane.setPassengersType(getXMLText(reader));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (PlaneEnum.valueOf(name.replaceAll("-", "_").toUpperCase()) ==
                            PlaneEnum.PASSENGER_PLANE) {
                        return passengerPlane;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag");
    }

    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }

}
