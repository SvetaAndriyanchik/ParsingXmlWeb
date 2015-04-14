package by.epam.parsing.model.businesslogic.builder;


import by.epam.parsing.model.domain.plane.Plane;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPlanesBuilder {

    protected Set<Plane> planes;
    public AbstractPlanesBuilder() {
        planes = new HashSet<Plane>();
    }
    public AbstractPlanesBuilder(Set<Plane> planes) {
        this.planes = planes;
    }
    public Set<Plane> getPlanes() {
        return planes;
    }
    abstract public void buildSetPlanes(String fileName);
}
