package fr.nantes.xml.saxb;

import fr.nantes.xml.XMLParserInterface;
import fr.nantes.xml.saxb.objects.Acceptations;
import fr.nantes.xml.saxb.objects.Conference;
import fr.nantes.xml.saxb.objects.Edition;
import fr.nantes.xml.saxb.objects.Type;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ughostephan on 01/03/2016.
 */
public class SaxbParser extends DefaultHandler implements XMLParserInterface {

    private List<Conference> conferences = new ArrayList<Conference>();
    private String xmlFileName;
    private String tmpValue;
    private Conference tmpConference;
    private SimpleDateFormat SimpleDateFormat = new SimpleDateFormat("yy-MM-dd");

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

    private void printDatas() {
        System.out.println(conferences.size());

        for (Conference tmpC : conferences) {
            System.out.println(tmpC.toString());
        }
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        // if current element is book , create new book
        // clear tmpValue on start of element

        if (elementName.equalsIgnoreCase("conferences")) {
            tmpConference = new Conference();

            tmpConference.setEdition(new Edition());

            System.out.println(attributes);

            /*tmpConference.getEdition().setAcronyme();
            tmpConference.getEdition().setTitle();
            tmpConference.getEdition().setVille();
            tmpConference.getEdition().setPays();
            tmpConference.getEdition().setDateDebut();
            tmpConference.getEdition().setDateFin();
            tmpConference.getEdition().setPresidents();
            tmpConference.getEdition().setTypeArticles();
            tmpConference.getEdition().setStatistiques();
            tmpConference.getEdition().setSiteWeb();
            tmpConference.getEdition().setMeilleurArticle();*/

            // TODO: Ajouter tous les setters
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        // if end of book element add to list
        if (element.equals("conference")) {
            conferences.add(tmpConference);
        }
        /*if (element.equalsIgnoreCase("isbn")) {
            tmpConference.setIsbn(tmpValue);
        }
        if (element.equalsIgnoreCase("title")) {
            tmpConference.setTitle(tmpValue);
        }
        if (element.equalsIgnoreCase("author")) {
            tmpConference.getAuthors().add(tmpValue);
        }
        if (element.equalsIgnoreCase("price")) {
            tmpConference.setPrice(Integer.parseInt(tmpValue));
        }
        if (element.equalsIgnoreCase("regDate")) {
            try {
                bookTmp.setRegDate(sdf.parse(tmpValue));
            } catch (ParseException e) {
                System.out.println("date parsing error");
            }
        }*/
    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tmpValue = new String(ac, i, j);
    }

    public SaxbParser(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void parseXML() {
        parseDocument();
        printDatas();
    }
}
