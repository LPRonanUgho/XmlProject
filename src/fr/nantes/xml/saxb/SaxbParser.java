package fr.nantes.xml.saxb;

import fr.nantes.xml.XMLParserInterface;
import fr.nantes.xml.XmlUtils;
import fr.nantes.xml.saxb.objects.*;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ughostephan on 01/03/2016.
 */
public class SaxbParser extends DefaultHandler implements XMLParserInterface {

    private List<Conference> conferences = new ArrayList<Conference>();
    private String xmlFileName;
    private String tmpValue;

    private Conference tmpConference;
    private Type tmpType;
    private Acceptations tmpAcceptations;
    private Article tmpArticle;
    private ArrayList<Auteur> tmpListAuteurs;
    private Auteur tmpAuteur;
    private List<Integer> tmpListAffiliationsId;
    private Affiliation tmpAffiliation;

    private boolean nodeEdition = false;
    private boolean nodePresident = false;
    private boolean nodeTypeArticles = false;
    private boolean nodeStatistiques = false;
    private boolean nodeMeilleurArticle = false;

    private boolean nodeArticles = false;
    private boolean nodeArticle = false;
    private boolean nodeAuteur = false;
    private boolean nodeAuteurs = false;
    private boolean nodeAffilitions = false;

    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");

    /**
     * Constructor
     * @param xmlFileName
     */
    public SaxbParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parseXML() {
        parseDocument();
        XmlUtils.generateHomePage(conferences);
    }

    private void parseDocument() {
        // parse
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlFileName, this);
        } catch (ParserConfigurationException e) {
            System.err.println("ParserConfig error");
        } catch (SAXException e) {
            System.err.println("SAXException : xml not well formed");
        } catch (IOException e) {
            System.err.println("IO error");
        }
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        // if current element is book , create new book
        // clear tmpValue on start of element

        tmpValue = "";

        if (elementName.equalsIgnoreCase("conference")) {
            tmpConference = new Conference();
        }

        if (elementName.equalsIgnoreCase("type") && nodeTypeArticles) {
            tmpType = new Type();
            tmpType.setId(attributes.getValue("id"));
        }

        if (elementName.equalsIgnoreCase("acceptations") && nodeStatistiques) {
            tmpAcceptations = new Acceptations();
            tmpAcceptations.setId(attributes.getValue("id"));
            tmpAcceptations.setSoumissions(Integer.parseInt(attributes.getValue("soumissions")));
        }


        if (elementName.equalsIgnoreCase("article") && nodeArticles) {
            tmpArticle = new Article();
            tmpArticle.setId(attributes.getValue("id"));
            tmpArticle.setSession(attributes.getValue("session"));
        }
        if (elementName.equalsIgnoreCase("auteurs") && nodeArticle) {
            tmpListAuteurs = new ArrayList<>();
        }
        if (elementName.equalsIgnoreCase("auteur") && nodeAuteurs) {
            tmpAuteur = new Auteur();
            tmpListAffiliationsId = new ArrayList<>();
        }
        if (elementName.equalsIgnoreCase("affiliation") && nodeAffilitions) {
            tmpAffiliation = new Affiliation();
            tmpAffiliation.setAffiliationId(Integer.parseInt(attributes.getValue("affiliationId")));
        }

        // START EDITION
        if(elementName.equalsIgnoreCase("edition")) { nodeEdition = true; }
        // START LIST PRESIDENTS
        if(elementName.equalsIgnoreCase("presidents")) { nodePresident = true; }
        // START LIST TYPE ARTICLES
        if(elementName.equalsIgnoreCase("typeArticles")) { nodeTypeArticles = true; }
        // START LIST STATS
        if(elementName.equalsIgnoreCase("statistiques")) { nodeStatistiques = true; }
        // START LIST MEILLEUR ARTICLE
        if(elementName.equalsIgnoreCase("meilleurArticle")) { nodeMeilleurArticle = true; }

        // START LIST ARTICLES
        if(elementName.equalsIgnoreCase("articles")) { nodeArticles = true; }
        // START ARTICLE
        if(elementName.equalsIgnoreCase("article")) { nodeArticle = true; }
        // START LIST AUTEURS
        if(elementName.equalsIgnoreCase("auteurs")) { nodeAuteurs = true; }
        // START AUTEUR
        if(elementName.equalsIgnoreCase("auteur")) { nodeAuteur = true; }
        // START LIST AFFILIATIONS
        if(elementName.equalsIgnoreCase("affiliations")) { nodeAffilitions = true; }
    }

    @Override
    public void endElement(String s, String s1, String elementName) throws SAXException {
        // if end of book elementName add to list
        if (elementName.equals("conference")) {
            conferences.add(tmpConference);
        }

        // END EDITION
        if(elementName.equalsIgnoreCase("edition")) { nodeEdition = false; }
        // END LIST PRESIDENT
        if(elementName.equalsIgnoreCase("presidents")) { nodePresident = false; }
        // END LIST TYPE ARTICLES
        if(elementName.equalsIgnoreCase("typeArticles")) { nodeTypeArticles = false; }
        // END LIST STATS
        if(elementName.equalsIgnoreCase("statistiques")) { nodeStatistiques = false; }
        // END LIST MEILLEUR ARTICLE
        if(elementName.equalsIgnoreCase("meilleurArticle")) { nodeMeilleurArticle = false; }

        // END LIST ARTICLES
        if(elementName.equalsIgnoreCase("articles")) { nodeArticles = false; }
        // END ARTICLE
        if(elementName.equalsIgnoreCase("article")) { nodeArticle = false; }
        // END LIST AUTEURS
        if(elementName.equalsIgnoreCase("auteurs")) { nodeAuteurs = false; }
        // END AUTEUR
        if(elementName.equalsIgnoreCase("auteur")) { nodeAuteur = false; }
        // END LIST AFFILIATIONS
        if(elementName.equalsIgnoreCase("affiliations")) { nodeAffilitions = false; }


        // START EDITION
        if(nodeEdition) {
            if (elementName.equalsIgnoreCase("acronyme")) {
                tmpConference.getEdition().setAcronyme(tmpValue);
            }

            if (elementName.equalsIgnoreCase("titre")) {
                tmpConference.getEdition().setTitre(tmpValue);
            }

            if (elementName.equalsIgnoreCase("ville")) {
                tmpConference.getEdition().setVille(tmpValue);
            }

            if (elementName.equalsIgnoreCase("pays")) {
                tmpConference.getEdition().setPays(tmpValue);
            }

            if (elementName.equalsIgnoreCase("dateDebut")) {
                try {
                    tmpConference.getEdition().setDateDebut(simpleDateFormat.parse(tmpValue));
                } catch (ParseException e) {
                    System.err.println("date parsing error");
                }
            }

            if (elementName.equalsIgnoreCase("dateFin")) {
                try {
                    tmpConference.getEdition().setDateFin(simpleDateFormat.parse(tmpValue));
                } catch (ParseException e) {
                    System.err.println("date parsing error");
                }
            }

            if (elementName.equalsIgnoreCase("nom") && nodePresident) {
                tmpConference.getEdition().getPresidents().add(tmpValue);
            }

            if (elementName.equalsIgnoreCase("type") && nodeTypeArticles) {
                tmpType.setName(tmpValue);
                tmpConference.getEdition().getTypeArticles().add(tmpType);
            }

            if (elementName.equalsIgnoreCase("acceptations") && nodeStatistiques) {
                tmpAcceptations.setName(tmpValue);
                tmpConference.getEdition().getStatistiques().add(tmpAcceptations);
            }

            if (elementName.equalsIgnoreCase("siteWeb")) {
                tmpConference.getEdition().setSiteWeb(tmpValue);
            }

            if (elementName.equalsIgnoreCase("articleId") && nodeMeilleurArticle) {
                tmpConference.getEdition().getMeilleurArticle().add(tmpValue);
            }
        }
        // END EDITION

        // START ARTICLES
        if(nodeArticles) {
            if(elementName.equalsIgnoreCase("article")) {
                tmpConference.getArticles().add(tmpArticle);
            }

            if(nodeArticle) {
                if(nodeAuteurs) {
                    if(elementName.equalsIgnoreCase("auteur")) {
                        tmpAuteur.setAffiliationsId(tmpListAffiliationsId);
                        tmpListAuteurs.add(tmpAuteur);
                    }

                    if(nodeAuteur) {
                        if (elementName.equalsIgnoreCase("nom")) {
                            tmpAuteur.setNom(tmpValue);
                        }

                        if (elementName.equalsIgnoreCase("email")) {
                            tmpAuteur.setEmail(tmpValue);
                        }
                        if (elementName.equalsIgnoreCase("affiliationId")) {
                            try {
                                tmpListAffiliationsId.add(Integer.parseInt(tmpValue));
                            } catch (NumberFormatException e) {
                                tmpListAffiliationsId.add(0);
                            }
                        }
                    }
                }

                if(elementName.equalsIgnoreCase("auteurs")) {
                    tmpArticle.setAuteurs(tmpListAuteurs);
                }

                if(elementName.equalsIgnoreCase("affiliation") && nodeAffilitions) {
                    tmpAffiliation.setName(tmpValue);
                    tmpArticle.getAffiliations().add(tmpAffiliation);
                }

                if(elementName.equalsIgnoreCase("titre")) {
                    tmpArticle.setTitre(tmpValue);
                }
                if(elementName.equalsIgnoreCase("type")) {
                    tmpArticle.setType(tmpValue);
                }
                if(elementName.equalsIgnoreCase("pages")) {
                    tmpArticle.setPages(tmpValue);
                }
                if(elementName.equalsIgnoreCase("resume")) {
                    tmpArticle.setResume(tmpValue);
                }
                if(elementName.equalsIgnoreCase("mots_cles")) {
                    tmpArticle.setMots_cles(tmpValue);
                }
                if(elementName.equalsIgnoreCase("abstract")) {
                    tmpArticle.setAbstract_libelle(tmpValue);
                }
                if(elementName.equalsIgnoreCase("keywords")) {
                    tmpArticle.setKeywords(tmpValue);
                }
            }
        }
        // END ARTICLES
    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }
}
