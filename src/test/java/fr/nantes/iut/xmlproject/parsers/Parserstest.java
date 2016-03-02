package fr.nantes.iut.xmlproject.parsers;

import fr.nantes.iut.xmlproject.objects.Article;
import fr.nantes.iut.xmlproject.objects.Conference;
import fr.nantes.iut.xmlproject.objects.Edition;
import fr.nantes.iut.xmlproject.utils.XmlProjectUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * Created by ughostephan on 02/03/2016.
 */
public class ParsersTest {

    private SaxbParser saxbParser;
    private JaxbParser jaxbParser;

    @Before
    public void setUp() throws Exception {
        // BEFORE JUNIT
        saxbParser = new SaxbParser(XmlProjectUtils.XML_FILE_NAME);
        jaxbParser = new JaxbParser(XmlProjectUtils.XML_FILE_NAME);
    }

    @After
    public void tearDown() throws Exception {
        // AFTER JUNIT
    }

    @Test
    public void SaxbParseXML() {
        parseXML(saxbParser.parseXML());
    }

    @Test
    public void JaxbParseXML() {
        parseXML(jaxbParser.parseXML());
    }

    private void parseXML(final List<Conference> conferences) {
        // Vérifier que la liste n'est pas vide
        Assert.assertFalse(conferences.isEmpty());

        // Récupération de la deuxième conférence
        final Conference conference = conferences.get(1);

        // vérification que la conférence n'est pas null
        Assert.assertNotNull(conference);

        if (conference != null) {
            // récupération de l'edition
            final Edition edition = conference.getEdition();

            // vérification que certains champs ne sont pas vide ou mal remplis
            Assert.assertNotEquals("", edition.getAcronyme());
            Assert.assertEquals("France", edition.getPays());
            Assert.assertEquals(2, edition.getPresidents().size());
            Assert.assertEquals(4, edition.getTypeArticles().size());

            // Vérifier que la liste d'article n'est pas vide
            Assert.assertFalse(conference.getArticles().isEmpty());

            // Récupération du premier article
            final Article article = conference.getArticles().get(0);

            if (article != null) {
                // Vérification du champ Id
                Assert.assertEquals("taln-2011-invite-001", article.getId());
                // Vérification du champ type
                Assert.assertEquals("invite", article.getType());

                // Vérifier que la liste d'auteur n'est pas vide
                Assert.assertFalse(article.getAuteurs().isEmpty());

                if (!article.getAuteurs().isEmpty()) {
                    // Vérification du champ email
                    Assert.assertEquals("vfomichov@hse.ru", article.getAuteurs().get(0).getEmail());

                    // Vérifier que la liste d'affiliationId n'est pas vide
                    Assert.assertFalse(article.getAuteurs().get(0).getAffiliationsId().isEmpty());

                    if (!article.getAuteurs().get(0).getAffiliationsId().isEmpty()) {
                        // Vérification du champ affiliation
                        Assert.assertEquals(1, (int) article.getAuteurs().get(0).getAffiliationsId().get(0));
                    }
                }

                // Vérifier que la liste d'affiliation n'est pas vide
                Assert.assertFalse(article.getAffiliations().isEmpty());

                if (!article.getAffiliations().isEmpty()) {
                    // Vérification du champ affiliation
                    Assert.assertEquals("Department of Innovations and Business in the Sphere of Informational Technologies, Faculty of Business Informatics, National Research University “Higher School of Economics”, Kirpichnaya str. 33, 105679 Moscow, Russia", article.getAffiliations().get(0).getName());
                    // Vérification du champ affiliationId
                    Assert.assertEquals(1, article.getAffiliations().get(0).getAffiliationId());
                }
            }
        }
    }
}
