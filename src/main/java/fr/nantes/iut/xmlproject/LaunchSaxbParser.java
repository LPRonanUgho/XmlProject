package fr.nantes.iut.xmlproject;

import fr.nantes.iut.xmlproject.objects.Conference;
import fr.nantes.iut.xmlproject.parsers.SaxbParser;
import fr.nantes.iut.xmlproject.utils.XmlProjectUtils;

import java.util.List;

/**
 * Created by ughostephan on 01/03/2016.
 */
public class LaunchSaxbParser {

    public static void main(String args[]) {

        final List<Conference> conferences = new SaxbParser(XmlProjectUtils.XML_FILE_NAME).parseXML();

        if (conferences != null) {
            System.out.println(conferences.size() + " conférences ont été récupérés du fichier XML\n");
            XmlProjectUtils.generateHomePage(conferences);
        }
    }
}
