package by.epam.parsing.model.businesslogic.builder;


import by.epam.parsing.model.businesslogic.parsing.PlanesDOMBuilder;
import by.epam.parsing.model.businesslogic.parsing.PlanesSAXBuilder;
import by.epam.parsing.model.businesslogic.parsing.PlanesStAXBuilder;

public class PlaneBuilderFactory {
    private enum TypeParser {
        SAX, STAX, DOM
    }
    public AbstractPlanesBuilder createPlaneBuilder(String typeParser) {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new PlanesDOMBuilder();
            case STAX:
                return new PlanesStAXBuilder();
            case SAX:
                return new PlanesSAXBuilder();
            default:
                throw new EnumConstantNotPresentException(
                        type.getDeclaringClass(), type.name());
        }
    }
}
