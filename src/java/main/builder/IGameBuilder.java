package main.builder;

import main.model.Game;

public interface IGameBuilder {
    IGameBuilder addPlayer(String name);
    IGameBuilder withShipForPlayer(String playerName, String shipType, String shipName);
    Game build();
}
