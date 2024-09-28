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

    public InitData(int nbHerbergements, int nbMaxChambre) {
        populateHerbergements(nbHerbergements, nbMaxChambre);
    }

    private void populateHerbergements(int nbHerbergements, int nbMaxChambre) {
        populateTypeHebergement();
        populateAdresses(nbHerbergements);
        populateServices();
        populateChambres(nbMaxChambre);

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

    private void populateChambres(int nbMaxChambre) {
        // Assurez-vous que les types de chambres sont peuplés
        populateTypeChambre();
        
        // Récupérer tous les hébergements et types de chambre disponibles
        List<Hebergement> hebergements = hebergementRepository.getAll();
        List<TypeChambre> typesChambre = typeChambreRepository.getAll();
        
        for (Hebergement hebergement : hebergements) {
            // Définir un nombre aléatoire de chambres à générer (entre 1 et 10 par exemple)
            int nbChambres = r.nextInt(nbMaxChambre) + 1;
            
            for (int i = 0; i < nbChambres; i++) {
                // Tirer un type de chambre aléatoirement en fonction d'une probabilité logique
                TypeChambre typeChambre = getRandomTypeChambre(typesChambre);
                
                // Définir un prix aléatoire selon le type de chambre
                int prix = generatePrixSelonType(typeChambre);
    
                // Créer et sauvegarder la nouvelle chambre
                Chambre chambre = new Chambre(prix, typeChambre, hebergement);
                chambreRepository.save(chambre);
            }
        }
    }
    
    // Méthode pour générer un type de chambre aléatoirement avec des probabilités différentes
    private TypeChambre getRandomTypeChambre(List<TypeChambre> typesChambre) {
        int rand = r.nextInt(100); // Tirer un nombre entre 0 et 99
        
        if (rand < 40) {
            // 40% de chance de tomber sur une chambre simple
            return typesChambre.stream().filter(tc -> tc.getLibelle().equals("Chambre Simple")).findFirst().orElse(null);
        } else if (rand < 65) {
            // 25% de chance de tomber sur une chambre double
            return typesChambre.stream().filter(tc -> tc.getLibelle().equals("Chambre Double")).findFirst().orElse(null);
        } else if (rand < 80) {
            // 15% de chance de tomber sur une chambre familiale
            return typesChambre.stream().filter(tc -> tc.getLibelle().equals("Chambre Familiale")).findFirst().orElse(null);
        } else if (rand < 90) {
            // 10% de chance de tomber sur une suite
            return typesChambre.stream().filter(tc -> tc.getLibelle().equals("Suite")).findFirst().orElse(null);
        } else {
            // 10% de chance de tomber sur un autre type (deluxe, executive, etc.)
            return typesChambre.get(r.nextInt(typesChambre.size()));
        }
    }
    
    // Méthode pour générer un prix selon le type de chambre
    private int generatePrixSelonType(TypeChambre typeChambre) {
        switch (typeChambre.getLibelle()) {
            case "Chambre Simple":
                return 50 + r.nextInt(50);  // Entre 50 et 100€
            case "Chambre Double":
                return 80 + r.nextInt(70);  // Entre 80 et 150€
            case "Chambre Familiale":
                return 120 + r.nextInt(80); // Entre 120 et 200€
            case "Suite":
                return 200 + r.nextInt(150); // Entre 200 et 350€
            default:
                return 100 + r.nextInt(100); // Autres types de chambres
        }
    }
    

    private void populateTypeChambre(){
        TypeChambre typeChambre1 = new TypeChambre("Chambre Simple");
        TypeChambre typeChambre2 = new TypeChambre("Chambre Double");
        TypeChambre typeChambre3 = new TypeChambre("Chambre Twin");
        TypeChambre typeChambre4 = new TypeChambre("Chambre Triple");
        TypeChambre typeChambre5 = new TypeChambre("Chambre Familiale");
        TypeChambre typeChambre6 = new TypeChambre("Suite");
        TypeChambre typeChambre7 = new TypeChambre("Chambre Deluxe");
        TypeChambre typeChambre8 = new TypeChambre("Chambre Supérieure");
        TypeChambre typeChambre9 = new TypeChambre("Chambre Executive");
        TypeChambre typeChambre10 = new TypeChambre("Penthouse");
        TypeChambre typeChambre11 = new TypeChambre("Chambre Accessible");
    
        typeChambreRepository.save(typeChambre1);
        typeChambreRepository.save(typeChambre2);
        typeChambreRepository.save(typeChambre3);
        typeChambreRepository.save(typeChambre4);
        typeChambreRepository.save(typeChambre5);
        typeChambreRepository.save(typeChambre6);
        typeChambreRepository.save(typeChambre7);
        typeChambreRepository.save(typeChambre8);
        typeChambreRepository.save(typeChambre9);
        typeChambreRepository.save(typeChambre10);
        typeChambreRepository.save(typeChambre11);
    }
    

    private Hebergement generateRandomHebergement(int constante, List<TypeHebergement> typeHebergements, List<Adresse> adresses, List<Service> services) {
        String[] noms = {"Le soleil", "La lune", "Les étoiles", "La mer", "La montagne", "La campagne"};

        String nom = noms[r.nextInt(noms.length)] + " " + constante;
        Adresse adresse = adresses.get(r.nextInt(adresses.size()));
        TypeHebergement typeHebergement = typeHebergements.get(r.nextInt(typeHebergements.size()));
        List<Service> servicesHebergement = new ArrayList<>();
        for (int i = 0; i < r.nextInt(services.size()); i++) {
            servicesHebergement.add(services.get(r.nextInt(services.size())));
        }
        return new Hebergement(nom, typeHebergement, adresse, servicesHebergement, new ArrayList<>());
    }
}
