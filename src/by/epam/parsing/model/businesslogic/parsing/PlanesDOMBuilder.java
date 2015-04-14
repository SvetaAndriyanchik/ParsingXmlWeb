package by.epam.parsing.model.businesslogic.parsing;



import by.epam.parsing.model.businesslogic.builder.AbstractPlanesBuilder;
import by.epam.parsing.model.domain.childplanes.CargoPlane;
import by.epam.parsing.model.domain.childplanes.PassengerPlane;
import by.epam.parsing.model.domain.plane.Plane;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PlanesDOMBuilder extends AbstractPlanesBuilder {

    private Set<Plane> planes;
    private DocumentBuilder docBuilder;
    private static Logger log = Logger.getLogger(PlanesDOMBuilder.class);

    public PlanesDOMBuilder() {
        this.planes = new HashSet<Plane>();
// создание DOM-анализатора
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
           log.error("Ошибка конфигурации парсера: " + e);
        }
    }

    public Set<Plane> getPlanes() {
        return planes;
    }

    public void buildSetPlanes(String fileName) {
        Document doc = null;
        try {
            doc = docBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList cargoPlanesList = root.getElementsByTagName("cargo-plane");

            for (int i = 0; i < cargoPlanesList.getLength(); i++) {
                Element planeElement = (Element) cargoPlanesList.item(i);
                Plane plane = buildCargoPlane(planeElement);
                planes.add(plane);
            }

            NodeList passengerPlanesList = root.getElementsByTagName("passenger-plane");
            for (int i = 0; i < passengerPlanesList.getLength(); i++) {
                Element planeElement = (Element) passengerPlanesList.item(i);
                Plane plane = buildPassengerPlane(planeElement);
                planes.add(plane);
            }
        } catch (IOException e) {
            log.error("File error or I/O error: " + e);
        } catch (SAXException e) {
            log.error("Parsing failure: " + e);
        }
    }
    private Plane buildCargoPlane(Element planeElement) {
        CargoPlane cargoPlane = new CargoPlane();
        cargoPlane.setId(planeElement.getAttribute("id"));
        cargoPlane.setModel(getElementTextContent(planeElement, "model"));
        cargoPlane.setOrigin(getElementTextContent(planeElement, "origin"));
        cargoPlane.setHeight(Integer.parseInt(getElementTextContent(planeElement, "height")));
        cargoPlane.setLength(Integer.parseInt(getElementTextContent(planeElement, "length")));
        cargoPlane.setWidth(Integer.parseInt(getElementTextContent(planeElement, "width")));
        cargoPlane.setCargoType(getElementTextContent(planeElement, "cargo-type"));
        cargoPlane.setWeightCapacity(Integer.parseInt(getElementTextContent(planeElement, "weight-capacity")));
        return cargoPlane;
    }

    private Plane buildPassengerPlane(Element planeElement) {
        PassengerPlane passengerPlane = new PassengerPlane();
        passengerPlane.setId(planeElement.getAttribute("id"));
        passengerPlane.setModel(getElementTextContent(planeElement, "model"));
        passengerPlane.setOrigin(getElementTextContent(planeElement, "origin"));
        passengerPlane.setHeight(Integer.parseInt(getElementTextContent(planeElement, "height")));
        passengerPlane.setLength(Integer.parseInt(getElementTextContent(planeElement, "length")));
        passengerPlane.setWidth(Integer.parseInt(getElementTextContent(planeElement, "width")));
        passengerPlane.setPassengersType(getElementTextContent(planeElement, "passengers-type"));
        passengerPlane.setSeatsCapacity(Integer.parseInt(getElementTextContent(planeElement, "seats-capacity")));
        return passengerPlane;
    }

    private static String getElementTextContent(Element element, String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        String text = node.getTextContent();
        return text;

    }
}
