package main.model;

public class Game {
    private final Player player1;
    private final Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public void switchPlayer() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getOpponent() {
        return (currentPlayer == player1) ? player2 : player1;
    }

    public boolean isGameOver() {
        return player1.hasLost() || player2.hasLost();
    }

    public Player getWinner() {
        if (player1.hasLost()) return player2;
        if (player2.hasLost()) return player1;
        return null;
    }
}
