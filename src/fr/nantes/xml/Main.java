package fr.nantes.xml;

import fr.nantes.xml.saxb.SaxbParser;

/**
 * Created by ughostephan on 01/03/2016.
 */
public class Main {

    public static void main(String args[]) {
        new SaxbParser(XmlUtils.XML_FILE_NAME).parseXML();
    }
}
