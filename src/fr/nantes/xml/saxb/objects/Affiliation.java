package fr.nantes.xml.saxb.objects;

/**
 * Created by ronan on 01/03/16.
 */
public class Affiliation {
    private int affiliationId;
    private String name;

    public int getAffiliationId() {
        return affiliationId;
    }

    public void setAffiliationId(int affiliationId) {
        this.affiliationId = affiliationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Affiliation{" +
                "affiliationId=" + affiliationId +
                ", name='" + name + '\'' +
                '}';
    }
}
