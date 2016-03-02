package fr.nantes.iut.xmlproject.objects;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */

@XmlRootElement( name = "conference" )
@XmlType( propOrder = { "edition", "articles" } )
public class Conference implements Comparable<Conference> {
    private Edition edition;
    private List<Article> articles;

    public Conference() {
        this.edition = new Edition();
        this.articles = new ArrayList<>();
    }

    public Edition getEdition() {
        return edition;
    }

    public void setEdition(Edition edition) {
        this.edition = edition;
    }

    public List<Article> getArticles() {
        return articles;
    }

    @XmlElementWrapper( name = "articles" )
    @XmlElement( name = "article" )
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Article getArticleById(final String id) {
        for (final Article article : articles) {
            if (id.equalsIgnoreCase(article.getId())) {
                return article;
            }
        }

        return null;
    }

    public List<Article> getArticleByType(final String typeId) {
        ArrayList<Article> articlesFilteredByType = new ArrayList<>();

        for (final Article article : this.articles) {
            if (article.getType().equalsIgnoreCase(typeId)) {
                articlesFilteredByType.add(article);
            }
        }

        return articlesFilteredByType;
    }

    @Override
    public String toString() {
        return "Conference{" +
                "edition=" + edition +
                ", articles=" + articles +
                '}';
    }

    @Override
    public int compareTo(Conference conference) {
        return getEdition().getDateFin().compareTo(conference.getEdition().getDateFin());
    }
}
