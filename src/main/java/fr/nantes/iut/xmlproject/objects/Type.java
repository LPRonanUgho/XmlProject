package main.java.fr.nantes.iut.xmlproject.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by ronan on 01/03/16.
 */
@XmlRootElement( name = "type" )
public class Type {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    @XmlAttribute( name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlValue
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Type{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
