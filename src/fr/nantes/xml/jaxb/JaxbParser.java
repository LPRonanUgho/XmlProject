package fr.nantes.xml.jaxb;

import fr.nantes.xml.XMLParserInterface;

import java.text.SimpleDateFormat;

/**
 * Created by ughostephan on 01/03/2016.
 */
public class JaxbParser implements XMLParserInterface {

    private String xmlFileName;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");

    /**
     * Constructor
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

    }
}
