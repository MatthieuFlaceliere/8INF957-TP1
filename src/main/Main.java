package main;

import main.business.Adresse;
import main.init.InitData;
import main.repository.GenericRepository;

public class Main {
    public static void main(String[] args) {
        new InitData(10); 
        GenericRepository<Adresse> adresseRepository = GenericRepository.getInstance(Adresse.class);
    }
}