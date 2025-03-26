package main.controller;

import main.builder.GameBuilder;
import main.model.Game;
import main.model.Player;

public class GameController {
    private Game game;

    public void initializeGame() {
        GameBuilder builder = new GameBuilder();

        // Configuración básica
        builder.addPlayer("Jugador 1")
                .addShipToPlayer("Jugador 1", "Battleship", 0, 0, true)
                .addPlayer("Jugador 2")
                .addShipToPlayer("Jugador 2", "Frigate", 3, 3, false);

        this.game = builder.build();
    }

    public void startGame() {
        while (!game.isGameOver()) {
            playTurn(game.getCurrentPlayer(), game.getOpponent());
            game.switchPlayer();
        }
        displayWinner();
    }

    private void playTurn(Player attacker, Player defender) {
        // Lógica simplificada de ataque aleatorio
        int x = (int) (Math.random() * 10);
        int y = (int) (Math.random() * 10);

        Player.AttackResult result = defender.receiveAttack(x, y);
        attacker.recordAttack(x, y, result.hit);

        System.out.printf("%s ataca (%d,%d): %s%n",
                attacker.getName(), x, y,
                result.hit ? "IMPACTO en " + result.ship.getType() : "AGUA");
    }

    private void displayWinner() {
        System.out.println("\n¡JUEGO TERMINADO!");
        System.out.println("Ganador: " + game.getWinner().getName());
    }
}
