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
    public static final String TEMPLATES_PATH = RESSOURCES_PATH + "templates/";

    public static final String XML_FILE_NAME = RESSOURCES_PATH + "TALN-RECITAL-BIB.xml";
    public static final String HTML_RESSOURSES_PATH = "TALN_RECITAL_fichiers/";

    public static final String HOME_PAGE_FILE_NAME = "TALN_RECITAL.html";
    public static final String HOME_PAGE_TEMPLATE_NAME = "HOME_PAGE_TEMPLATE.html";
    public static final String CONFERENCE_PAGE_TEMPLATE_NAME = "CONFERENCE_PAGE_TEMPLATE.html";

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
        } catch (FileNotFoundException e) {
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
        final String htmlTemplateFile = TEMPLATES_PATH + HOME_PAGE_TEMPLATE_NAME;

        String htmlString = "";
        try {
            htmlString = new Scanner(new File(htmlTemplateFile)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            //TODO : Error - file not found
        }

        htmlString = htmlString.replace("$ressourcesPath", HTML_RESSOURSES_PATH);
        htmlString = htmlString.replace("$conferences", getConferences(conferences));

        saveInFile(htmlString, HTML_PATH, HOME_PAGE_FILE_NAME);
    }

    public static String generateConferencePage(Conference conference) {
        final String fileName = conference.getEdition().getAcronyme().replace("'", "_").toUpperCase() + ".html";

        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMMM yyyy");

        final String htmlTemplateFile = TEMPLATES_PATH + CONFERENCE_PAGE_TEMPLATE_NAME;

        String htmlString = "";
        try {
            htmlString = new Scanner(new File(htmlTemplateFile)).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            //TODO : Error - file not found
        }

        htmlString = htmlString.replace("$ressourcesPath", HTML_RESSOURSES_PATH);
        htmlString = htmlString.replace("$homePage", HOME_PAGE_FILE_NAME);

        htmlString = htmlString.replace("$acronyme", conference.getEdition().getAcronyme());
        htmlString = htmlString.replace("$titre", conference.getEdition().getTitre());
        htmlString = htmlString.replace("$presidents", getPresidentsFormat(conference.getEdition().getPresidents()));
        htmlString = htmlString.replace("$ville", conference.getEdition().getVille());
        htmlString = htmlString.replace("$pays", conference.getEdition().getPays());
        htmlString = htmlString.replace("$siteWeb", conference.getEdition().getSiteWeb());
        htmlString = htmlString.replace("$dateDebut", String.valueOf(conference.getEdition().getDateDebut().getDay()));
        htmlString = htmlString.replace("$dateFin", simpleDateFormat.format(conference.getEdition().getDateFin()));
        htmlString = htmlString.replace("$statistiques", getConferenceStats(conference.getEdition().getStatistiques()));
        htmlString = htmlString.replace("$bestArticles", getBestArticle(conference));
        htmlString = htmlString.replace("$articles", getArticles(conference));

        saveInFile(htmlString, HTML_PATH + CONFERENCES_PATH, fileName);

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

    public static String getConferences(final List<Conference> conferences) {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMMMM yyyy");
        String formatedStr = "";

        Collections.sort(conferences, Collections.reverseOrder());

        for (Conference conference : conferences) {
            final String conferencePageUrl = generateConferencePage(conference);

            formatedStr += "<a href=\"" + conferencePageUrl + "\">" + conference.getEdition().getAcronyme() +
                    " : " + conference.getEdition().getTitre() + "</a><br>\n" +
                    "Organisation : " + getPresidentsFormat(conference.getEdition().getPresidents()) + "<br>\n" +
                    "Date et lieu: " +
                    conference.getEdition().getDateDebut().getDay() +
                    " au " +
                    simpleDateFormat.format(conference.getEdition().getDateFin()) +
                    ", " + conference.getEdition().getVille() +
                    ", " + conference.getEdition().getPays() + ".<br>\n <br>";
        }

        return formatedStr;
    }

    public static String getConferenceStats(final List<Acceptations> stats) {
        String formatedStr = "";

        for (Acceptations a : stats) {
            formatedStr += "<img src=\"../" + HTML_RESSOURSES_PATH + "/puce.gif\" alt=\"-\">" + a.getSoumissions() + " soumissions d'articles " + a.getId() + "<br>";
            formatedStr += "<img src=\"../" + HTML_RESSOURSES_PATH + "/puce.gif\" alt=\"-\">" + a.getName() + " articles " + a.getId() + " acceptés<br>";
        }
        return formatedStr;
    }

    public static String getBestArticle(final Conference conference) {
        String formatedStr = "";

        for (String articleId : conference.getEdition().getMeilleurArticle()) {
            final Article article = conference.getArticleById(articleId);

            if (article != null) {
                formatedStr += "<img src=\"../" + HTML_RESSOURSES_PATH + "/puce.gif\" alt=\"-\"> " + getAuteursFormat(article) + " : <i>" + article.getTitre() + "</i><br>";
            }
        }


        return formatedStr;
    }

    public static String getAuteursFormat(final Article article) {
        final List<Auteur> auteurs = article.getAuteurs();

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

        if (tmpAffiliationsId.size() != 0) {
            List<String> affiliationsId = new ArrayList(tmpAffiliationsId);

            formatedStr += " (";
            for (int i = 0; i < affiliationsId.size(); i++) {
                final Affiliation affiliation = article.getAffiliationById(Integer.parseInt(affiliationsId.get(i)));

                if (affiliation != null) {
                    if (i == 0) {
                        formatedStr += affiliation.getName();
                    } else {
                        formatedStr += " - " + affiliation.getName();
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
            formatedStr += "<img src=\"../" + HTML_RESSOURSES_PATH + "/puce.gif\" alt=\"-\">" + getAuteursFormat(article) + ", <b>" + article.getTitre() + "</b><br>";
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
