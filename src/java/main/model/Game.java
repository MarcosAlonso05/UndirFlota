package main.model;

public class Game {
    private Player player1;
    private Player player2;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public void start() {
        while (player1.hasShipsAlive() && player2.hasShipsAlive()) {
            player1.attack(player2);
            player2.attack(player1);
        }
        declareWinner();
    }

    private void declareWinner() {
        if (player1.hasShipsAlive() && !player2.hasShipsAlive()) {
            System.out.println(player1.getName() + " wins!");
        } else if (!player1.hasShipsAlive() && player2.hasShipsAlive()) {
            System.out.println(player2.getName() + " wins!");
        } else {
            System.out.println("It's a tie!");
        }
    }
}
