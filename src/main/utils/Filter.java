package main.utils;

import main.business.Adresse;
import main.business.Service;
import main.business.TypeChambre;
import main.business.TypeHebergement;

import java.util.List;

public class Filter {
    private TypeHebergement type;
    private Adresse adresse;
    private TypeChambre typeChambre;
    private List<Service> services;

    private Filter(Builder builder) {
        this.type = builder.type;
        this.adresse = builder.adresse;
        this.typeChambre = builder.typeChambre;
        this.services = builder.services;
    }

    public static class Builder {
        private TypeHebergement type;
        private Adresse adresse;
        private TypeChambre typeChambre;
        private List<Service> services;

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

    @Override
    public String toString() {
        StringBuilder servicesString = new StringBuilder();
        if (services != null) {
            for (Service service : services) {
                servicesString.append(service.getLibelle()).append(", ");
            }
        }

        return "type=" + (type != null ? type.getLibelle() : "Non spécifié") +
                ", adresse=" + (adresse != null ? adresse : "Non spécifié") +
                ", typeChambre=" + (typeChambre != null ? typeChambre.getLibelle() : "Non spécifié") +
                ", services=" + (!servicesString.isEmpty() ? servicesString : "Non spécifiés");
    }
}
