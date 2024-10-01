package main.business;

import main.utils.Entity;

public class Adresse extends Entity {
    private String pays;
    private String province;
    private String ville;
    private String quartier;
    private String rue;

    public Adresse(String pays, String province, String ville, String quartier, String rue) {
        this.pays = pays;
        this.province = province;
        this.ville = ville;
        this.quartier = quartier;
        this.rue = rue;
    }

    @Override
    public String toString() {
        return rue + ", " + quartier + ", " + ville + ", " + province + ", " + pays;
    }
}
