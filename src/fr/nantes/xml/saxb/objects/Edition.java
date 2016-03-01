package fr.nantes.xml.saxb.objects;

import java.util.Date;
import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
public class Edition {
    private String acronyme;
    private String title;
    private String ville;
    private String pays;
    private Date dateDebut;
    private Date dateFin;
    private List<String> presidents;
    private List<Type> typeArticles;
    private List<Acceptations> statistiques;
    private String siteWeb;
    private List<String> meilleurArticle;

}
