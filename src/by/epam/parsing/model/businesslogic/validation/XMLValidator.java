package by.epam.parsing.model.businesslogic.validation;


import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.IOException;

public class XMLValidator {

    private static Logger log = Logger.getLogger(XMLValidator.class);

    public void validateXML(){
        String schemaLang = "http://www.w3.org/2001/XMLSchema";
        // get validation driver:
        SchemaFactory factory = SchemaFactory.newInstance(schemaLang);
        // create schema by reading it from an XSD file:
        Schema schema = null;
        try {
            schema = factory.newSchema(new StreamSource("data/planeSchema.xsd"));
        } catch (SAXException e) {
            log.error(e.getMessage());
        }
        Validator validator = schema.newValidator();
        // at last perform validation:
        try {
            validator.validate(new StreamSource("data/plane.xml"));
        } catch (SAXException e) {
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

}
