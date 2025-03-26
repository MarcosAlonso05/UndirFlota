package main.builder;

import main.model.Game;

public interface IGameBuilder {
    IGameBuilder addPlayer(String name);
    IGameBuilder addShipToPlayer(String playerName, String shipType, int x, int y, boolean isHorizontal);
    Game build();
}
