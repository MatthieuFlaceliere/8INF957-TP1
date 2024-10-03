package main.utils;

import main.business.Adresse;
import main.business.Service;
import main.business.TypeChambre;
import main.business.TypeHebergement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Filter {
    private TypeHebergement type;
    private Adresse adresse;
    private TypeChambre typeChambre;
    private List<Service> services;
    private Date dateDebut;
    private Date dateFin;

    private Filter(Builder builder) {
        this.type = builder.type;
        this.adresse = builder.adresse;
        this.typeChambre = builder.typeChambre;
        this.services = builder.services;
        this.dateDebut = builder.dateDebut;
        this.dateFin = builder.dateFin;
    }

    public static class Builder {
        private TypeHebergement type;
        private Adresse adresse;
        private TypeChambre typeChambre;
        private List<Service> services;
        private Date dateDebut;
        private Date dateFin;

        public Builder setType(TypeHebergement type) {
            this.type = type;
            return this;
        }

        public Builder setAdresse(Adresse adresse) {
            this.adresse = adresse;
            return this;
        }

        public Builder setTypeChambre(TypeChambre typeChambre) {
            this.typeChambre = typeChambre;
            return this;
        }

        public Builder setServices(List<Service> services) {
            this.services = services;
            return this;
        }

        public Builder setDateDebut(Date dateDebut) {
            this.dateDebut = dateDebut;
            return this;
        }

        public Builder setDateFin(Date dateFin) {
            this.dateFin = dateFin;
            return this;
        }

        public Filter build() {
            return new Filter(this);
        }
    }

    public TypeHebergement getType() {
        return type;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public TypeChambre getTypeChambre() {
        return typeChambre;
    }

    public List<Service> getServices() {
        return services;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    @Override
    public String toString() {
        StringBuilder servicesString = new StringBuilder();
        if (services != null) {
            for (Service service : services) {
                servicesString.append(service.getLibelle()).append(", ");
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        return "type=" + (type != null ? type.getLibelle() : "Non spécifié") +
                ", adresse=" + (adresse != null ? adresse : "Non spécifié") +
                ", typeChambre=" + (typeChambre != null ? typeChambre.getLibelle() : "Non spécifié") +
                ", dateDebut=" + (dateDebut != null ? sdf.format(dateDebut) : "Non spécifiée") +
                ", dateFin=" + (dateFin != null ? sdf.format(dateFin) : "Non spécifiée") +
                ", services=" + (!servicesString.isEmpty() ? servicesString : "Non spécifiés");
    }
}
