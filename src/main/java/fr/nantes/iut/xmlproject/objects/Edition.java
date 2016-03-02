package fr.nantes.iut.xmlproject.objects;

import fr.nantes.iut.xmlproject.parsers.JaxbDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
@XmlRootElement( name = "edition" )
@XmlType( propOrder = { "acronyme", "titre", "ville", "pays", "dateDebut", "dateFin", "presidents",
"typeArticles", "statistiques", "siteWeb", "meilleurArticle" } )
public class Edition {
    private String acronyme;
    private String titre;
    private String ville;
    private String pays;
    private Date dateDebut;
    private Date dateFin;
    private List<String> presidents;
    private List<Type> typeArticles;
    private List<Acceptations> statistiques;
    private String siteWeb;
    private List<String> meilleurArticle;

    public Edition() {
        this.presidents = new ArrayList<String>();
        this.typeArticles = new ArrayList<Type>();
        this.statistiques = new ArrayList<Acceptations>();
        this.meilleurArticle = new ArrayList<String>();
    }

    public String getAcronyme() {
        return acronyme;
    }

    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<String> getPresidents() {
        return presidents;
    }

    @XmlElementWrapper(name = "presidents")
    @XmlElement(name = "nom")
    public void setPresidents(List<String> presidents) {
        this.presidents = presidents;
    }

    public List<Type> getTypeArticles() {
        return typeArticles;
    }

    @XmlElementWrapper(name = "typeArticles")
    @XmlElement(name = "type")
    public void setTypeArticles(List<Type> typeArticles) {
        this.typeArticles = typeArticles;
    }

    public List<Acceptations> getStatistiques() {
        return statistiques;
    }

    @XmlElementWrapper(name = "statistiques")
    @XmlElement(name = "acceptations")
    public void setStatistiques(List<Acceptations> statistiques) {
        this.statistiques = statistiques;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public List<String> getMeilleurArticle() {
        return meilleurArticle;
    }

    @XmlElementWrapper(name = "meilleurArticle")
    @XmlElement(name = "articleId")
    public void setMeilleurArticle(List<String> meilleurArticle) {
        this.meilleurArticle = meilleurArticle;
    }

    @Override
    public String toString() {
        return "Edition{" +
                "acronyme='" + acronyme + '\'' +
                ", titre='" + titre + '\'' +
                ", ville='" + ville + '\'' +
                ", pays='" + pays + '\'' +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", presidents=" + presidents +
                ", typeArticles=" + typeArticles +
                ", statistiques=" + statistiques +
                ", siteWeb='" + siteWeb + '\'' +
                ", meilleurArticle=" + meilleurArticle +
                '}';
    }
}
