package main.java.fr.nantes.iut.xmlproject;

import main.java.fr.nantes.iut.xmlproject.objects.Conference;
import main.java.fr.nantes.iut.xmlproject.utils.XmlProjectUtils;
import main.java.fr.nantes.iut.xmlproject.parsers.JaxbParser;

import java.util.List;

/**
 * Created by ughostephan on 01/03/2016.
 */
public class LaunchJaxbParser {

    public static void main(String args[]) {

        final List<Conference> conferences = new JaxbParser(XmlProjectUtils.XML_FILE_NAME).parseXML();

        if (conferences != null) {
            System.out.println(conferences.size() + " conférences ont été récupérés du fichier XML\n");
            XmlProjectUtils.generateHomePage(conferences);
        }
    }
}
