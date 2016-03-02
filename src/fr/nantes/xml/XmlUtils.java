package fr.nantes.xml;

import fr.nantes.xml.objects.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by ronan on 01/03/16.
 */
public class XmlUtils {
    public static final String RESSOURCES_PATH = "ressources/";
    public static final String HTML_PATH = "html/";
    public static final String CONFERENCES_PATH = "conferences/";
    public static final String XML_FILE_NAME = RESSOURCES_PATH + "TALN-RECITAL-BIB.xml";
    public static final String OUTPUT_FILE_NAME = "TALN_RECITAL.html";

    public static void saveInFile(final String html, final String filePath, final String fileName) {
        //Récupération du chemin courant
        final File repCourant = new java.io.File(new java.io.File("").getAbsolutePath());

        //Le repertoire source du fichier à créer :
        final String DIRECTORY = repCourant + "/" + filePath;

        try {
            //Instantiation du PrintWriter avec le chemin du fichier en paramètre
            PrintWriter writer = new PrintWriter(DIRECTORY + fileName, "UTF-8");
            //Ecriture de la chaine dans le fichier
            writer.write(html);
            //Fermeture du fichier
            writer.close();
            //Affichage de trace
            System.out.println("Fichier \"" + fileName + "\" correctement créé à cet endroit : \"" + DIRECTORY + "\"");
        } catch(FileNotFoundException e) {
            //Création du répertoire source si inexistant
            File dir = new File(DIRECTORY);
            dir.mkdirs();
            System.out.println("Dossier \"" + DIRECTORY + "\" créé car inexistant");
            //Appelle à nouveau de la fonction pour créer le fichier
            saveInFile(html, filePath, fileName);
        } catch (UnsupportedEncodingException e) {
            //Affichage de la trace en cas d'erreur d'encodage
            System.err.println(e.getMessage());
        }
    }

    public static void generateHomePage(List<Conference> conferences) {
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
                "              <a href=\"\" title=\"Adhésion\">\n" +
                "                <img class=\"bouton_rouge\" src=\"TALN_RECITAL_fichiers/rub3.gif\" alt=\"Adhésion\">\n" +
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
            final String conferencePageUrl = generateConferencePage(conference);

            html.append("<a href=\"" + conferencePageUrl + "\">" + conference.getEdition().getAcronyme() +
                    " : " + conference.getEdition().getTitre() + "</a><br>\n" +
                    "Organisation : " + getPresidentsFormat(conference.getEdition().getPresidents()) + "<br>\n" +
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

        saveInFile(html.toString(), HTML_PATH, OUTPUT_FILE_NAME);
    }

    public static String generateConferencePage(Conference conference) {
        final String fileName = conference.getEdition().getAcronyme().replace("'", "_").toUpperCase() + ".html";

        final StringBuilder html = new StringBuilder();
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMMM yyyy");

        html.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\n" +
                "<html lang=\"fr\">\n" +
                "<head>\n" +
                "    <title>" + conference.getEdition().getAcronyme() + " : " + conference.getEdition().getTitre() + "</title>\n" +
                "\n" +
                "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"../TALN_RECITAL_fichiers/structure.css\" type=\"text/css\">\n" +
                "    <link rel=\"stylesheet\" href=\"../TALN_RECITAL_fichiers/styles.css\" type=\"text/css\">\n" +
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
                "                <a href=\"../TALN_RECITAL.html\" title=\"Accueil du site\">\n" +
                "                    <img src=\"../TALN_RECITAL_fichiers/logo.gif\" alt=\"Accueil du site\" border=\"0\">\n" +
                "                </a>\n" +
                "            </td>\n" +
                "            <td class=\"bouton\">\n" +
                "                <a href=\"../TALN_RECITAL.html\" title=\"Accueil du site\">\n" +
                "                    <img class=\"bouton_rouge\" src=\"../TALN_RECITAL_fichiers/rub1.gif\" alt=\"Accueil du site\">\n" +
                "                </a>\n" +
                "            </td>\n" +
                "            <td class=\"bouton\">\n" +
                "                <a href=\"\" title=\"Adhésion\">\n" +
                "                    <img class=\"bouton_rouge\" src=\"../TALN_RECITAL_fichiers/rub3.gif\" alt=\"Adhésion\">\n" +
                "                </a>\n" +
                "            </td>\n" +
                "            <td class=\"bouton\">\n" +
                "                <a href=\"\" title=\"Contact\">\n" +
                "                    <img class=\"bouton_rouge\" src=\"../TALN_RECITAL_fichiers/rub5.gif\" alt=\"Contact\">\n" +
                "                </a>\n" +
                "            </td>\n" +
                "            <td class=\"bouton\">\n" +
                "                <a href=\"\" title=\"Plan du site\">\n" +
                "                    <img class=\"bouton_rouge\" src=\"../TALN_RECITAL_fichiers/rub6.gif\" alt=\"Plan du site\">\n" +
                "                </a>\n" +
                "            </td>\n" +
                "        </tr>\n" +
                "        </tbody>\n" +
                "    </table>\n" +
                "\n" +
                "    <img src=\"../TALN_RECITAL_fichiers/motif.gif\" height=\"20\" width=\"100%\">\n" +
                "</div>\n" +
                "\n" +
                "<div class=\"contenu\" id=\"principal\">\n" +
                "    <div class=\"surtitre\">" + getPresidentsFormat(conference.getEdition().getPresidents()) + ", " + conference.getEdition().getVille() + " (" + conference.getEdition().getPays() + ")</div>\n" +
                "\n" +
                "    <h1>" + conference.getEdition().getAcronyme() + " : " + conference.getEdition().getTitre() + "</h1>\n" +
                "\n" +
                "    <a href=\"" + conference.getEdition().getSiteWeb() + "\"><img src=\"../TALN_RECITAL_fichiers/puce.gif\" alt=\"-\">Site web</a>\n" +
                "\n" +
                "    <br/>\n" +
                "    <br/>\n" +
                "\n" +
                "    <div class=\"soustitre\">" +
                conference.getEdition().getDateDebut().getDay() +
                " au " +
                simpleDateFormat.format(conference.getEdition().getDateFin()) +
                "    </div>\n" +
                "\n" +
                "    <div class=\"texte\">\n" +
                "        <p>\n" +
                "        <h3>Statistiques de la conférence</h3>\n" +
                "        <p>\n" + getConferenceStats(conference.getEdition().getStatistiques()) + "</p>\n" +
                "\n" +
                "        <h3>Papiers primés</h3>\n" +
                "\n" +
                "        <p>\n" +
                "            Comme tous les ans, le Comité Permanent de la conférence associé à leurs comités de sélection respectifs ont\n" +
                "            décerné un prix aux articles jugés les meilleurs de la conférence.\n" +
                "        </p>\n" +
                "\n" +
                "        <p>\n" +
                "            <strong>Prix TALN</strong>\n" +
                "\n" +
                "            <br><br>\n" +
                "\n" +
                getBestArticle(conference) +
                "        </p>\n" +
                "\n" +
                "        <h3>Actes</h3>\n" +
                "\n" +
                getArticles(conference) +
                "    </div>\n" +
                "</body>\n" +
                "</html>\n");

        saveInFile(html.toString(), HTML_PATH + CONFERENCES_PATH, fileName);

        return CONFERENCES_PATH + fileName;
    }

    public static String getPresidentsFormat(final List<String> presidents) {
        String formatedStr = "";

        for (int i = 0; i < presidents.size(); i++) {
            if (i == 0) {
                formatedStr += presidents.get(i);
            } else if (i != (presidents.size() - 1)) {
                formatedStr += ", " + presidents.get(i);
            } else {
                formatedStr += " et " + presidents.get(i);
            }
        }

        return formatedStr;
    }

    public static String getConferenceStats(final List<Acceptations> stats) {
        String formatedStr = "";

        for (Acceptations a : stats) {
            formatedStr += "<img src=\"../TALN_RECITAL_fichiers/puce.gif\" alt=\"-\">" + a.getSoumissions() + " soumissions d'articles " + a.getId() + "<br>";
            formatedStr += "<img src=\"../TALN_RECITAL_fichiers/puce.gif\" alt=\"-\">" + a.getName() + " articles " + a.getId() + " acceptés<br>";
        }
        return formatedStr;
    }

    public static String getBestArticle(final Conference conference) {
        String formatedStr = "";

        for (String articleId : conference.getEdition().getMeilleurArticle()) {
            final Article article = conference.getArticleById(articleId);

            if (article != null) {
                formatedStr += "<img src=\"../TALN_RECITAL_fichiers/puce.gif\" alt=\"-\"> " + getAuteursFormat(article) + " : <i>" + article.getTitre() + "</i><br>";
            }
        }


        return formatedStr;
    }

    public static String getAuteursFormat(final Article article) {
        final List<Auteur> auteurs  = article.getAuteurs();

        String formatedStr = "";

        // Noms
        for (int i = 0; i < auteurs.size(); i++) {
            if (i == 0) {
                formatedStr += auteurs.get(i).getNom();
            } else if (i != (auteurs.size() - 1)) {
                formatedStr += ", " + auteurs.get(i).getNom();
            } else {
                formatedStr += " et " + auteurs.get(i).getNom();
            }


        }

        //Affiliations
        Set<String> tmpAffiliationsId = new HashSet<>();

        for (Auteur auteur : auteurs) {
            for (int id : auteur.getAffiliationsId()) {
                tmpAffiliationsId.add(String.valueOf(id));
            }
        }

        if(tmpAffiliationsId.size() != 0) {
            List<String> affiliationsId = new ArrayList(tmpAffiliationsId);

            formatedStr += " (";
            for (int i = 0; i < affiliationsId.size(); i++) {
                final Affiliation affiliation = article.getAffiliationById(Integer.parseInt(affiliationsId.get(i)));

                if(affiliation != null) {
                    if(i == 0) {
                        formatedStr += affiliation.getName();
                    } else {
                        formatedStr += " - " +  affiliation.getName();
                    }
                }
            }
            formatedStr += ")";
        }



        return formatedStr;
    }

    public static String getArticleFormat(final List<Article> articles) {
        Collections.sort(articles);

        String formatedStr = "";

        for (Article article : articles) {
            formatedStr += "<img src=\"../TALN_RECITAL_fichiers/puce.gif\" alt=\"-\">" + getAuteursFormat(article) + ", <b>" + article.getTitre() + "</b><br>";
        }

        return formatedStr;
    }

    public static String getArticles(final Conference conference) {
        String formatedStr = "";

        for (Type type : conference.getEdition().getTypeArticles()) {
            formatedStr += "<p>" +
                    "<strong>" + type.getName() + "</strong>\n" +
                    "<br><br>\n" +
                    getArticleFormat(conference.getArticleByType(type.getId())) +
                    "</p>";
        }

        return formatedStr;
    }
}
