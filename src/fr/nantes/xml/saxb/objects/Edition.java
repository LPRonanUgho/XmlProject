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

    public String getAcronyme() {
        return acronyme;
    }

    public void setAcronyme(String acronyme) {
        this.acronyme = acronyme;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public List<String> getPresidents() {
        return presidents;
    }

    public void setPresidents(List<String> presidents) {
        this.presidents = presidents;
    }

    public List<Type> getTypeArticles() {
        return typeArticles;
    }

    public void setTypeArticles(List<Type> typeArticles) {
        this.typeArticles = typeArticles;
    }

    public List<Acceptations> getStatistiques() {
        return statistiques;
    }

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

    public void setMeilleurArticle(List<String> meilleurArticle) {
        this.meilleurArticle = meilleurArticle;
    }

    @Override
    public String toString() {
        return "Edition{" +
                "acronyme='" + acronyme + '\'' +
                ", title='" + title + '\'' +
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
