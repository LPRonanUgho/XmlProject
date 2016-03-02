package fr.nantes.xml.saxb.objects;

import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
public class Auteur {
    private String nom;
    private String email;
    private List<Integer> affiliationId;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Integer> getAffiliationsId() {
        return affiliationId;
    }

    public void setAffiliationsId(List<Integer> affiliationId) {
        this.affiliationId = affiliationId;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", affiliations=" + affiliationId +
                '}';
    }
}

