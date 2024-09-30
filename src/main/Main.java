package main;

import main.init.InitData;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new InitData(10);

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("Que souhaitez-vous faire ?");
            System.out.println("1 - Effectuer une réservation");
            System.out.println("2 - Annuler une réservation");
            System.out.println("3 - Quitter");
            int choice = getInputChoice(List.of(1, 2, 3));
            switch (choice) {
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.exit(0);
            }
        }
    }

    private static void makeReservation() {
        System.out.println("Effectuer une réservation");
    }

    private static void cancelReservation() {
        System.out.println("Annuler une réservation");
    }

    private static int getInputChoice(List<Integer> choices) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choices.contains(choice)) {
                    return choice;
                } else {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Veuillez saisir un nombre valide");
            }
        }
    }

}