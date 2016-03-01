package fr.nantes.xml;

import fr.nantes.xml.saxb.objects.Conference;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Collections;
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

        try {
            //Instantiation du PrintWriter avec le chemin du fichier en paramètre
            PrintWriter writer = new PrintWriter(DIRECTORY + OUTPUT_FILE_NAME, "UTF-8");
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
        final StringBuilder html = new StringBuilder();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMMM yyyy");

        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                    "<html lang=\"fr\">\n" +
                    "<head>\n" +
                    "    <meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\">\n" +
                    "    <title>Conférence TALN/RECITAL</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"TALN_RECITAL_fichiers/structure.css\" type=\"text/css\">\n" +
                    "    <link rel=\"stylesheet\" href=\"TALN_RECITAL_fichiers/styles.css\" type=\"text/css\">\n" +
                    "</head>\n" +
                    "<body dir=\"ltr\">\n" +
                    "<style>\n" +
                    "    .bouton A IMG {\n" +
                    "        border: 0px;\n" +
                    "    }\n" +
                    "\n" +
                    "    .bouton A:hover IMG {\n" +
                    "        border: 0px;\n" +
                    "        background-color: #FFFFFF;\n" +
                    "        filter: alpha(opacity=100);\n" +
                    "    }\n" +
                    "\n" +
                    "    .warning {\n" +
                    "        margin-bottom: 10px;\n" +
                    "        background-color: white;\n" +
                    "    }\n" +
                    "</style>\n" +
                    "<div id=\"banniere\">\n" +
                    "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                    "        <tbody>\n" +
                    "        <tr>\n" +
                    "            <td class=\"bouton\">\n" +
                    "              <a href=\"\" title=\"Accueil du site\">\n" +
                    "                <img src=\"TALN_RECITAL_fichiers/logo.gif\" alt=\"Accueil du site\" border=\"0\">\n" +
                    "              </a>\n" +
                    "            </td>\n" +
                    "            <td class=\"bouton\">\n" +
                    "              <a href=\"\" title=\"Accueil du site\">\n" +
                    "                <img class=\"bouton_rouge\" src=\"TALN_RECITAL_fichiers/rub1.gif\" alt=\"Accueil du site\">\n" +
                    "              </a>\n" +
                    "            </td>\n" +
                    "            <td class=\"bouton\">\n" +
                    "              <a href=\"\" title=\"Adh�sion\">\n" +
                    "                <img class=\"bouton_rouge\" src=\"TALN_RECITAL_fichiers/rub3.gif\" alt=\"Adh�sion\">\n" +
                    "              </a>\n" +
                    "            </td>\n" +
                    "            <td class=\"bouton\">\n" +
                    "              <a href=\"\" title=\"Contact\">\n" +
                    "                <img class=\"bouton_rouge\" src=\"TALN_RECITAL_fichiers/rub5.gif\" alt=\"Contact\">\n" +
                    "              </a>\n" +
                    "            </td>\n" +
                    "            <td class=\"bouton\">\n" +
                    "              <a href=\"\" title=\"Plan du site\">\n" +
                    "                <img class=\"bouton_rouge\" src=\"TALN_RECITAL_fichiers/rub6.gif\" alt=\"Plan du site\">\n" +
                    "              </a>\n" +
                    "            </td>\n" +
                    "        </tr>\n" +
                    "      </tbody>\n" +
                    "    </table>\n" +
                    "\n" +
                    "    <img src=\"TALN_RECITAL_fichiers/motif.gif\" height=\"20\" width=\"100%\">\n" +
                    "</div>\n" +
                    "\n" +
                    "<div id=\"principal\" class=\"contenu\">\n" +
                    "    <div class=\"cartouche\">\n" +
                    "        <h1>Conférence TALN/RECITAL</h1>\n" +
                    "    </div>\n" +
                    "\n" +
                    "    <h2>\n" +
                    "        Les éditions de la conférence\n" +
                    "    </h2>");

        Collections.sort(conferences, Collections.reverseOrder());

       for (Conference conference : conferences) {
            html.append("<a href=\"\">" + conference.getEdition().getAcronyme() +
                    " : " + conference.getEdition().getTitre() + "</a><br>\n" +
                    "Organisation : " + conference.getEdition().getPresidents() + "<br>\n" +
                    "Date et lieu: " +
                    conference.getEdition().getDateDebut().getDay() +
                    " au " +
                    simpleDateFormat.format(conference.getEdition().getDateFin()) +
                    ", " + conference.getEdition().getVille() +
                    ", " + conference.getEdition().getPays() + ".<br>\n <br>");
        }

        html.append("</div>\n" +
                "</body>\n" +
                "</html>");

        saveInFile(html.toString());
    }
}