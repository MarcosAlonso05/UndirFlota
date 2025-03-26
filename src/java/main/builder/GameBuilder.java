package main.builder;
import main.model.Game;
import main.model.Player;

public class GameBuilder implements IGameBuilder {
    private Player player1;
    private Player player2;

    @Override
    public IGameBuilder setPlayer1(Player player1) {
        this.player1 = player1;
        return this;
    }

    @Override
    public IGameBuilder setPlayer2(Player player2) {
        this.player2 = player2;
        return this;
    }

    @Override
    public Game build() {
        return new Game(player1, player2);
    }
}

