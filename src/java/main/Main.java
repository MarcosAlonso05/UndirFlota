package main;

import main.builder.GameBuilder;
import main.builder.ShipBuilder;
import main.model.Game;
import main.model.Player;

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        player1.addShip(new ShipBuilder().setBattleship().build());
        player1.addShip(new ShipBuilder().setFrigate().build());
        player1.addShip(new ShipBuilder().setCanoe().build());

        player2.addShip(new ShipBuilder().setBattleship().build());
        player2.addShip(new ShipBuilder().setFrigate().build());
        player2.addShip(new ShipBuilder().setCanoe().build());

        Game game = new GameBuilder().setPlayer1(player1).setPlayer2(player2).build();
        game.start();
    }
}
