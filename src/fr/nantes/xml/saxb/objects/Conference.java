package fr.nantes.xml.saxb.objects;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
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

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public Article getArticleById(final String id) {
        for (final Article article : articles) {
             if(id.equalsIgnoreCase(article.getId())) {
                return article;
            }
        }

        return null;
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
