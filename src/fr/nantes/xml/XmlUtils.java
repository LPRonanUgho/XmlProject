package fr.nantes.xml;

import fr.nantes.xml.saxb.objects.Article;
import fr.nantes.xml.saxb.objects.Conference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
public class XmlUtils {
    public static final String RESSOURCES_PATH = "ressources/";
    public static final String HTML_PATH = "html/";
    public static final String XML_FILE_NAME = RESSOURCES_PATH + "TALN-RECITAL-BIB.xml";
    public static final String OUTPUT_FILE_NAME = "TALN_RECITAL.html";

    public static void saveInFile(final String html) {
        //Récupération du chemin courant
        final File repCourant = new java.io.File(new java.io.File("").getAbsolutePath());

        //Le repertoire source du fichier à créer :
        final String DIRECTORY = repCourant + "/" + HTML_PATH;

        //Déclaration des variables utiles
        PrintWriter writer;

        try {
            //Instantiation du PrintWriter avec le chemin du fichier en paramètre
            writer = new PrintWriter(DIRECTORY + OUTPUT_FILE_NAME, "UTF-8");
            //Ecriture de la chaine dans le fichier
            writer.write(html);
            //Fermeture du fichier
            writer.close();
        } catch(FileNotFoundException e) {
            //Création du répertoire source si inexistant
            File dir = new File(DIRECTORY);
            dir.mkdirs();
            //Appelle à nouveau de la fonction pour créer le fichier
            saveInFile(html);
        } catch(UnsupportedEncodingException e) {
            //Affichage de la trace en cas d'erreur d'encodage
            e.printStackTrace();
        }
    }

    public static void generateHTML(List<Conference> conferences) {
        String html = "";
        for (Conference conference : conferences) {
            html += conference.toString() + "\n\n";
        }
        saveInFile(html);
    }
}
