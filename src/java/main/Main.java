package main;

import main.model.*;
import main.repository.*;
import main.service.*;
import main.controller.GameController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner demo(HashService hashService, ShipTypeHashRepository typeRepository, GameController gameController) {
        return args -> {
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            // Inicializar tipos de barcos básicos
            initializeShipTypes(typeRepository);

            while (!exit) {
                System.out.println("\n=== MENÚ PRINCIPAL ===");
                System.out.println("1. Registrar nuevo barco");
                System.out.println("2. Buscar barco por número");
                System.out.println("3. Buscar barco por nombre");
                System.out.println("4. Buscar tipo de barco");
                System.out.println("5. Jugar a Hundir la Flota");
                System.out.println("6. Salir");
                System.out.print("Seleccione una opción: ");

                int option = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer

                switch (option) {
                    case 1:
                        registerNewShip(hashService, scanner);
                        break;
                    case 2:
                        findShipByNumber(hashService, scanner);
                        break;
                    case 3:
                        findShipByName(hashService, scanner);
                        break;
                    case 4:
                        findShipType(hashService, scanner);
                        break;
                    case 5:
                        gameController.startGame();
                        break;
                    case 6:
                        exit = true;
                        System.out.println("Saliendo del sistema...");
                        break;
                    default:
                        System.out.println("Opción no válida. Intente nuevamente.");
                }
            }
            scanner.close();
        };
    }

    private void initializeShipTypes(ShipTypeHashRepository typeRepository) {
        typeRepository.addShipType(new ShipType("Battleship", 5));
        typeRepository.addShipType(new ShipType("Frigate", 3));
        typeRepository.addShipType(new ShipType("Canoe", 1));
    }

    private void registerNewShip(HashService hashService, Scanner scanner) {
        System.out.println("\n--- REGISTRAR NUEVO BARCO ---");

        System.out.print("Número de barco: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nombre del barco: ");
        String name = scanner.nextLine();

        System.out.print("Nivel: ");
        int level = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Tipo de barco (Battleship/Frigate/Canoe): ");
        String typeName = scanner.nextLine();

        System.out.print("Tamaño del barco: ");
        int size = scanner.nextInt();
        scanner.nextLine();

        ShipType type = new ShipType(typeName, size);
        ShipRegistry ship = new ShipRegistry(number, name, level, type);

        hashService.registerShip(ship);
        System.out.println("Barco registrado exitosamente!");
    }

    private void findShipByNumber(HashService hashService, Scanner scanner) {
        System.out.print("\nIngrese el número de barco a buscar: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        ShipRegistry ship = hashService.findShipByNumber(number);
        if (ship != null) {
            System.out.println("Barco encontrado:");
            System.out.println("Nombre: " + ship.getShipName());
            System.out.println("Tipo: " + ship.getShipType().getName());
            System.out.println("Nivel: " + ship.getLevel());
        } else {
            System.out.println("No se encontró ningún barco con ese número.");
        }
    }

    private void findShipByName(HashService hashService, Scanner scanner) {
        System.out.print("\nIngrese el nombre de barco a buscar: ");
        String name = scanner.nextLine();

        ShipRegistry ship = hashService.findShipByName(name);
        if (ship != null) {
            System.out.println("Barco encontrado:");
            System.out.println("Número: " + ship.getShipNumber());
            System.out.println("Tipo: " + ship.getShipType().getName());
            System.out.println("Nivel: " + ship.getLevel());
        } else {
            System.out.println("No se encontró ningún barco con ese nombre.");
        }
    }

    private void findShipType(HashService hashService, Scanner scanner) {
        System.out.print("\nIngrese el tipo de barco a buscar: ");
        String typeName = scanner.nextLine();

        ShipType type = hashService.findShipType(typeName);
        if (type != null) {
            System.out.println("Tipo de barco encontrado:");
            System.out.println("Nombre: " + type.getName());
            System.out.println("Tamaño: " + type.getSize());
        } else {
            System.out.println("No se encontró el tipo de barco especificado.");
        }
    }
}