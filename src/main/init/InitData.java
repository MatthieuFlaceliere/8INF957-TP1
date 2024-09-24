package main.init;

import main.business.Adresse;
import main.business.Hebergement;
import main.business.TypeHebergement;
import main.repository.AdresseRepository;
import main.repository.HebergementRepository;
import main.repository.TypeHebergementRepository;

import java.util.ArrayList;
import java.util.List;

public class InitData {
    private final TypeHebergementRepository typeHebergementRepository = new TypeHebergementRepository();
    private final AdresseRepository adresseRepository = new AdresseRepository();
    private final HebergementRepository hebergementRepository = new HebergementRepository();

    public InitData() {
        populateHerbergements(10);
    }

    private void populateHerbergements(int nbHerbergements) {
        populateTypeHebergement();
        populateAdresses(nbHerbergements);

        for (int i = 0; i < nbHerbergements; i++) {
            hebergementRepository.save(generateRandomHebergement(i, typeHebergementRepository.getAll(), adresseRepository.getAll()));
        }
    }

    private void populateTypeHebergement() {
        TypeHebergement typeHebergement1 = new TypeHebergement("Hôtel");
        typeHebergementRepository.save(typeHebergement1);
        TypeHebergement typeHebergement2 = new TypeHebergement("Auberge");
        typeHebergementRepository.save(typeHebergement2);
        TypeHebergement typeHebergement3 = new TypeHebergement("Maison d'hôtes");
        typeHebergementRepository.save(typeHebergement3);
        TypeHebergement typeHebergement4 = new TypeHebergement("Camping");
        typeHebergementRepository.save(typeHebergement4);
    }

    private void populateAdresses(int nbAdresses) {
        for (int i = 0; i < nbAdresses; i++) {
            adresseRepository.save(generateRandomAdresse(i));
        }
    }

    private Adresse generateRandomAdresse(int constante) {
        String[] pays = {"France", "Canada", "Espagne", "Allemagne", "Italie"};
        String[] provinces = {"Ile de France", "Québec", "Catalogne", "Bavière", "Lombardie"};
        String[] villes = {"Paris", "Montréal", "Barcelone", "Munich", "Milan"};
        String[] quartiers = {"Centre Ville", "Quartier Latin", "Gracia", "Altstadt", "Brera"};
        String[] rues = {"Rue de la Paix", "Avenue des Champs-Élysées", "Carrer de la Marina", "Maximilianstrasse", "Via Monte Napoleone"};

        int paysIndex = (int) (Math.random() * pays.length);
        int provinceIndex = (int) (Math.random() * provinces.length);
        int villeIndex = (int) (Math.random() * villes.length);
        int quartierIndex = (int) (Math.random() * quartiers.length);
        int rueIndex = (int) (Math.random() * rues.length);

        return new Adresse(
                pays[paysIndex],
                provinces[provinceIndex],
                villes[villeIndex],
                quartiers[quartierIndex],
                constante + " " + rues[rueIndex]
        );
    }

    private Hebergement generateRandomHebergement(int constante, List<TypeHebergement> typeHebergements, List<Adresse> adresses) {
        String[] noms = {"Le soleil", "La lune", "Les étoiles", "La mer", "La montagne", "La campagne"};

        String nom = noms[(int) (Math.random() * noms.length)] + " " + constante;
        Adresse adresse = adresses.get((int) (Math.random() * adresses.size()));
        TypeHebergement typeHebergement = typeHebergements.get((int) (Math.random() * typeHebergements.size()));
        return new Hebergement(nom, typeHebergement, adresse, new ArrayList<>(), new ArrayList<>());
    }
}
