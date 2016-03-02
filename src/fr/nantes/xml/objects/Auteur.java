package fr.nantes.xml.objects;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by ronan on 01/03/16.
 */
@XmlRootElement( name = "auteur" )
@XmlType( propOrder = { "nom", "email", "affiliationsId" } )
public class Auteur {
    private String nom;
    private String email;
    private List<Integer> affiliationsId;

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
        return affiliationsId;
    }

    @XmlList
    @XmlElement(name="affiliationId")
    public void setAffiliationsId(List<Integer> affiliationId) {
        this.affiliationsId = affiliationId;
    }

    @Override
    public String toString() {
        return "Auteur{" +
                "nom='" + nom + '\'' +
                ", email='" + email + '\'' +
                ", affiliations=" + affiliationsId +
                '}';
    }
}

