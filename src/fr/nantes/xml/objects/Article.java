package fr.nantes.xml.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
public class Article implements Comparable<Article> {
    private String id;
    private String session;
    private List<Auteur> auteurs;
    private List<Affiliation> affiliations;
    private String titre;
    private String type;
    private String pages;
    private String resume;
    private String mots_cles;
    private String abstract_libelle;
    private String keywords;

    public Article() {
        this.auteurs = new ArrayList<>();
        this.affiliations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public List<Auteur> getAuteurs() {
        return auteurs;
    }

    public void setAuteurs(List<Auteur> auteurs) {
        this.auteurs = auteurs;
    }

    public List<Affiliation> getAffiliations() {
        return affiliations;
    }

    public void setAffiliations(List<Affiliation> affiliations) {
        this.affiliations = affiliations;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getMots_cles() {
        return mots_cles;
    }

    public void setMots_cles(String mots_cles) {
        this.mots_cles = mots_cles;
    }

    public String getAbstract_libelle() {
        return abstract_libelle;
    }

    public void setAbstract_libelle(String abstract_libelle) {
        this.abstract_libelle = abstract_libelle;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Affiliation getAffiliationById(final int affiliationId) {
        for (final Affiliation affiliation : affiliations) {
            if (affiliationId == affiliation.getAffiliationId()) {
                return affiliation;
            }
        }

        return null;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id='" + id + '\'' +
                ", session='" + session + '\'' +
                ", auteurs=" + auteurs +
                ", affiliations=" + affiliations +
                ", titre='" + titre + '\'' +
                ", type='" + type + '\'' +
                ", pages='" + pages + '\'' +
                ", resume='" + resume + '\'' +
                ", mots_cles='" + mots_cles + '\'' +
                ", abstract_libelle='" + abstract_libelle + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }

    @Override
    public int compareTo(Article article) {
        if (auteurs.size() != 0 && article.getAuteurs().size() != 0) {
            return auteurs.get(0).getNom().compareTo(article.getAuteurs().get(0).getNom());
        } else {
            return 0;
        }
    }
}
