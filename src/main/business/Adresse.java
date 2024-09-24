package main.business;

import java.util.concurrent.atomic.AtomicLong;

public class Adresse {
    private static final AtomicLong idGenerator = new AtomicLong(0);
    private Long id;
    private String pays;
    private String province;
    private String ville;
    private String quartier;
    private String rue;

    public Adresse(String pays, String province, String ville, String quartier, String rue) {
        this.id = idGenerator.incrementAndGet();
        this.pays = pays;
        this.province = province;
        this.ville = ville;
        this.quartier = quartier;
        this.rue = rue;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Adresse{" +
                "rue='" + rue + '\'' +
                ", quartier='" + quartier + '\'' +
                ", ville='" + ville + '\'' +
                ", province='" + province + '\'' +
                ", pays='" + pays + '\'' +
                ", id=" + id +
                '}';
    }
}
