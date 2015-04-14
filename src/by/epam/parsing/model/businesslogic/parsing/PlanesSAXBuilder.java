package by.epam.parsing.model.businesslogic.parsing;



import by.epam.parsing.model.businesslogic.builder.AbstractPlanesBuilder;
import by.epam.parsing.model.domain.plane.Plane;
import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.Set;

public class PlanesSAXBuilder extends AbstractPlanesBuilder {
    private PlanesHandler ph;
    private XMLReader reader;
    private Set<Plane> planes;
    private static Logger log = Logger.getLogger(PlanesSAXBuilder.class);


    public PlanesSAXBuilder () {
        ph = new PlanesHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(ph);
        } catch (SAXException e) {
            log.error("ошибка SAX парсера: " + e);
        }
    }

    public Set<Plane> getPlanes() {
        return planes;
    }

    @Override
    public void buildSetPlanes(String fileName) {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            log.error("ошибка SAX парсера: " + e);
        } catch (IOException e) {
            log.error("ошибка I/О потока: " + e);
        }
        planes = ph.getPlanes();
    }
}
