package main.builder;

import main.model.Game;
import main.model.Player;

public interface IGameBuilder {
    IGameBuilder setPlayer1(Player player1);
    IGameBuilder setPlayer2(Player player2);
    Game build();
}
