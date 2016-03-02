package fr.nantes.iut.xmlproject.objects;

import javax.xml.bind.annotation.*;

/**
 * Created by ronan on 01/03/16.
 */
@XmlRootElement( name = "affiliation" )
public class Affiliation {
    private int affiliationId;
    private String name;

    public int getAffiliationId() {
        return affiliationId;
    }

    @XmlAttribute( name = "affiliationId")
    public void setAffiliationId(int affiliationId) {
        this.affiliationId = affiliationId;
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
        return "Affiliation{" +
                "affiliationId=" + affiliationId +
                ", name='" + name + '\'' +
                '}';
    }
}
