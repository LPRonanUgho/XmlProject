package fr.nantes.xml.jaxb;

import fr.nantes.xml.XMLParserInterface;
import fr.nantes.xml.XmlUtils;
import fr.nantes.xml.objects.Conferences;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.text.SimpleDateFormat;

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
    @Override
    public void parseXML() {
        try {
            JAXBContext jc = JAXBContext.newInstance(Conferences.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            Conferences conferences = (Conferences) unmarshaller.unmarshal(new File(xmlFileName));
            //System.out.println(conferences);
            XmlUtils.generateHomePage(conferences.getConferences());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
