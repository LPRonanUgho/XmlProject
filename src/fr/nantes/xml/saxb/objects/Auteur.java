package fr.nantes.xml.saxb.objects;

/**
 * Created by ronan on 01/03/16.
 */
public class Auteur {
    private String nom;
    private String email;
    private int affiliationId;

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

    public int getAffiliationId() {
        return affiliationId;
    }

    public void setAffiliationId(int affiliationId) {
        this.affiliationId = affiliationId;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", affiliationId=" + affiliationId +
                '}';
    }
}
