package fr.nantes.iut.xmlproject.objects;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by ronan on 01/03/16.
 */
@XmlRootElement( name = "acceptations" )
public class Acceptations {
    private String id;
    private int soumissions;
    private String name;

    public String getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(String id) {
        this.id = id;
    }

    public int getSoumissions() {
        return soumissions;
    }

    @XmlAttribute(name = "soumissions")
    public void setSoumissions(int soumissions) {
        this.soumissions = soumissions;
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
        return "Acceptations{" +
                "id='" + id + '\'' +
                ", soumissions=" + soumissions +
                ", name='" + name + '\'' +
                '}';
    }
}
