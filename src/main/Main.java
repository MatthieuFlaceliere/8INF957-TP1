package main;

import main.business.Hebergement;
import main.business.Service;
import main.init.InitData;
import main.repository.GenericRepository;

public class Main {
    public static void main(String[] args) {
        new InitData(10);
        GenericRepository<Service> serviceRepository = GenericRepository.getInstance(Service.class);
        GenericRepository<Hebergement> hebergementRepository = GenericRepository.getInstance(Hebergement.class);

        System.out.println(serviceRepository.getAll());
        System.out.println(hebergementRepository.getAll());

    }
}