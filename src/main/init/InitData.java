package main.init;

import main.business.Adresse;
import main.business.Chambre;
import main.business.Hebergement;
import main.business.Service;
import main.business.TypeChambre;
import main.business.TypeHebergement;
import main.repository.GenericRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InitData {
    Random r = new Random();
    private final GenericRepository<Adresse> adresseRepository = GenericRepository.getInstance(Adresse.class);
    private final GenericRepository<TypeHebergement> typeHebergementRepository = GenericRepository.getInstance(TypeHebergement.class);
    private final GenericRepository<Hebergement> hebergementRepository = GenericRepository.getInstance(Hebergement.class);
    private final GenericRepository<Service> serviceRepository = GenericRepository.getInstance(Service.class);

    private final GenericRepository<Chambre> chambreRepository = GenericRepository.getInstance(Chambre.class);
    private final GenericRepository<TypeChambre> typeChambreRepository = GenericRepository.getInstance(TypeChambre.class);

    public InitData(int nbHerbergements) {
        populateHerbergements(nbHerbergements);
    }

    private void populateHerbergements(int nbHerbergements) {
        populateTypeHebergement();
        populateAdresses(nbHerbergements);
        populateServices();
        populateTypeChambre();

        for (int i = 0; i < nbHerbergements; i++) {
            Hebergement herbergement = generateRandomHebergement(i, typeHebergementRepository.getAll(), adresseRepository.getAll(), serviceRepository.getAll());
            hebergementRepository.save(herbergement);
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
            Adresse adresse = generateRandomAdresse(i);
            adresseRepository.save(adresse);
        }
    }

    private Adresse generateRandomAdresse(int constante) {
        String[] pays = {"France", "Canada", "Espagne", "Allemagne", "Italie"};
        String[] provinces = {"Ile de France", "Québec", "Catalogne", "Bavière", "Lombardie"};
        String[] villes = {"Paris", "Montréal", "Barcelone", "Munich", "Milan"};
        String[] quartiers = {"Centre Ville", "Quartier Latin", "Gracia", "Altstadt", "Brera"};
        String[] rues = {"Rue de la Paix", "Avenue des Champs-Élysées", "Carrer de la Marina", "Maximilianstrasse", "Via Monte Napoleone"};

        int paysIndex = r.nextInt(pays.length);
        int provinceIndex = r.nextInt(provinces.length);
        int villeIndex = r.nextInt(villes.length);
        int quartierIndex = r.nextInt(quartiers.length);
        int rueIndex = r.nextInt(rues.length);

        return new Adresse(
                pays[paysIndex],
                provinces[provinceIndex],
                villes[villeIndex],
                quartiers[quartierIndex],
                constante + " " + rues[rueIndex]
        );
    }

    private void populateServices() {
        String[] labels = {"piscine intérieure", "cuisinette", "salle de conditionnement physique", "stationnement", "accès handicapé", "dépanneur", "restauran"};

        for (String label: labels) {
            Service service = new Service(label);
            serviceRepository.save(service);
        }
    }

    private void populateTypeChambre(){
        TypeChambre typeChambre1 = new TypeChambre("Chambre Simple");
        TypeChambre typeChambre2 = new TypeChambre("Chambre Double");
        TypeChambre typeChambre3 = new TypeChambre("Chambre Familiale");
        TypeChambre typeChambre4 = new TypeChambre("Suite");
        TypeChambre typeChambre5 = new TypeChambre("Chambre Deluxe");

        typeChambreRepository.save(typeChambre1);
        typeChambreRepository.save(typeChambre2);
        typeChambreRepository.save(typeChambre3);
        typeChambreRepository.save(typeChambre4);
        typeChambreRepository.save(typeChambre5);
    }

    private Hebergement generateRandomHebergement(int constante, List<TypeHebergement> typeHebergements, List<Adresse> adresses, List<Service> services) {
        String[] noms = {"Le soleil", "La lune", "Les étoiles", "La mer", "La montagne", "La campagne"};

        String nom = noms[r.nextInt(noms.length)] + " " + constante;
        Adresse adresse = adresses.get(r.nextInt(adresses.size()));
        TypeHebergement typeHebergement = typeHebergements.get(r.nextInt(typeHebergements.size()));
        List<Service> servicesHebergement = new ArrayList<>();
        for (int i = 0; i < r.nextInt(services.size()); i++) {
            Service newService = services.get(r.nextInt(services.size()));
            if (!servicesHebergement.contains(newService)) {
                servicesHebergement.add(newService);
            }
        }

        return new Hebergement(nom, typeHebergement, adresse, servicesHebergement, generateChambres(2,10));
    }

    private List<Chambre> generateChambres(int nbMinChambre,int nbMaxChambre) {
        List<Chambre> chambres = new ArrayList<>();

        for (int i = 0; i < r.nextInt(nbMinChambre, nbMaxChambre); i++) {
            Chambre chambre = new Chambre(50 + r.nextInt(100), getRandomTypeChambre());

            chambres.add(chambre);

            chambreRepository.save(chambre);
        }

        return chambres;
    }

    private TypeChambre getRandomTypeChambre() throws RuntimeException {
        List<TypeChambre> typesChambre = typeChambreRepository.getAll();
        if (typesChambre.isEmpty()) {
            throw new RuntimeException("Aucun type de chambre n'a été défini");
        }

        return typesChambre.get(r.nextInt(typesChambre.size()));
    }

}
