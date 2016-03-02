package fr.nantes.iut.xmlproject.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by ughostephan on 02/03/2016.
 */
@XmlRootElement(name = "conferences")
public class Conferences {

    List<Conference> conferences;

    public List<Conference> getConferences() {
        return conferences;
    }

    @XmlElement(name = "conference")
    public void setConferences(List<Conference> conferences) {
        this.conferences = conferences;
    }
}
