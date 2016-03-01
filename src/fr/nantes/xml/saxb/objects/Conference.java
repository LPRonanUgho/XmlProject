package fr.nantes.xml.saxb.objects;

import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
public class Conference {
    private Edition edition;
    private List<Article> articles;

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

    @Override
    public String toString() {
        return "Conference{" +
                "edition=" + edition +
                ", articles=" + articles +
                '}';
    }
}
