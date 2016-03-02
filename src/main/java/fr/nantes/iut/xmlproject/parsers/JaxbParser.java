package fr.nantes.iut.xmlproject.parsers;

import fr.nantes.iut.xmlproject.objects.Conference;
import fr.nantes.iut.xmlproject.objects.Conferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

/**
 * Created by ughostephan on 01/03/2016.
 */
public class JaxbParser implements XMLParserInterface {

    private String xmlFileName;

    /**
     * Constructor
     *
     * @param xmlFileName
     */
    public JaxbParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * {@inheritDoc}
     */
    public List<Conference> parseXML() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Conferences.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Conferences conferences = (Conferences) unmarshaller.unmarshal(new File(xmlFileName));
            return conferences.getConferences();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
