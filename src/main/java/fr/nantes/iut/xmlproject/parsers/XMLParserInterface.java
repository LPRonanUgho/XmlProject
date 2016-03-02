package main.java.fr.nantes.iut.xmlproject.parsers;

import main.java.fr.nantes.iut.xmlproject.objects.Conference;

import java.util.List;

/**
 * Created by ughostephan on 01/03/2016.
 */
public interface XMLParserInterface {
    /**
     * Méthode permettant de retourner une liste de conférence après avoir
     * parser le fichier XML les contenants.
     */
    List<Conference> parseXML();
}
