package main;

import main.builder.GameBuilder;
import main.builder.IGameBuilder;
import main.model.Game;
import main.model.Player;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("╔═══════════════════╗");
        System.out.println("║  HUNDIR LA FLOTA  ║");
        System.out.println("╚═══════════════════╝");

        Game game = configureGame();
        playGame(game);
    }

    private static Game configureGame() {
        IGameBuilder builder = new GameBuilder();

        System.out.println("\nCONFIGURACIÓN DE JUGADORES");
        String player1Name = askForName(1);
        String player2Name = askForName(2);

        builder.addPlayer(player1Name)
                .addPlayer(player2Name);

        System.out.println("\nCOLOCACIÓN DE BARCOS");
        configureShips(builder, player1Name);
        configureShips(builder, player2Name);

        return builder.build();
    }

    private static String askForName(int playerNumber) {
        System.out.printf("Nombre del Jugador %d: ", playerNumber);
        String name;
        do {
            name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.print("El nombre no puede estar vacío. Inténtalo de nuevo: ");
            }
        } while (name.isEmpty());
        return name;
    }

    private static void configureShips(IGameBuilder builder, String playerName) {
        System.out.println("\n" + playerName + ", coloca tus barcos:");

        String[] shipTypes = {"Battleship (5 casillas)", "Frigate (3 casillas)", "Canoe (1 casilla)"};
        int[] shipSizes = {5, 3, 1};

        for (int i = 0; i < shipTypes.length; i++) {
            System.out.println("\nBarco: " + shipTypes[i]);

            boolean placed = false;
            while (!placed) {
                try {
                    System.out.print("Coordenada X (0-9): ");
                    int x = Integer.parseInt(scanner.nextLine());

                    System.out.print("Coordenada Y (0-9): ");
                    int y = Integer.parseInt(scanner.nextLine());

                    System.out.print("Orientación (H)orizontal/(V)ertical: ");
                    boolean horizontal = scanner.nextLine().equalsIgnoreCase("H");

                    if (horizontal && y + shipSizes[i] > 10) {
                        System.out.println("¡El barco no cabe en esa posición horizontal!");
                        continue;
                    }
                    if (!horizontal && x + shipSizes[i] > 10) {
                        System.out.println("¡El barco no cabe en esa posición vertical!");
                        continue;
                    }

                    builder.addShipToPlayer(playerName,
                            shipTypes[i].split(" ")[0],
                            x, y, horizontal);
                    placed = true;
                    System.out.println("Barco colocado correctamente.");

                } catch (NumberFormatException e) {
                    System.out.println("¡Coordenadas deben ser números entre 0-9!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
    }

    private static void playGame(Game game) {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║     ¡COMIENZA EL JUEGO!    ║");
        System.out.println("╚════════════════════════════╝");

        while (!game.isGameOver()) {
            Player current = game.getCurrentPlayer();
            Player opponent = game.getOpponent();

            System.out.printf("\nTURNO DE %s\n", current.getName());

            System.out.println("Tablero de ataque:");
            printTrackingBoard(current);

            boolean validAttack = false;
            while (!validAttack) {
                try {
                    System.out.print("Coordenada X para atacar (0-9): ");
                    int x = Integer.parseInt(scanner.nextLine());

                    System.out.print("Coordenada Y para atacar (0-9): ");
                    int y = Integer.parseInt(scanner.nextLine());

                    if (x < 0 || x > 9 || y < 0 || y > 9) {
                        System.out.println("¡Coordenadas fuera de rango!");
                        continue;
                    }

                    boolean hit = opponent.receiveAttack(x, y).hit;
                    current.recordAttack(x, y, hit);

                    System.out.println(hit ? "¡IMPACTO!" : "AGUA");
                    validAttack = true;

                } catch (NumberFormatException e) {
                    System.out.println("¡Debes introducir números entre 0-9!");
                }
            }

            game.switchPlayer();
        }

        endGame(game);
    }

    private static void printTrackingBoard(Player player) {
        System.out.println("  0 1 2 3 4 5 6 7 8 9");
        for (int x = 0; x < 10; x++) {
            System.out.print(x + " ");
            for (int y = 0; y < 10; y++) {
                if (player.getTrackingBoard().wasAttacked(x, y)) {
                    System.out.print(player.getTrackingBoard().getShipAt(x, y) != null ? "X " : "O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }

    private static void endGame(Game game) {
        System.out.println("\n╔════════════════════════════╗");
        System.out.println("║      ¡JUEGO TERMINADO!     ║");
        System.out.println("╚════════════════════════════╝");

        Player winner = game.getWinner();
        System.out.println("\n¡EL GANADOR ES " + winner.getName() + "!");
        System.out.println("\nGracias por jugar a Hundir la Flota");
    }
}
