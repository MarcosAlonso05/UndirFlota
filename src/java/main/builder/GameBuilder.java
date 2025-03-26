package main.builder;
import main.model.Game;
import main.model.Player;
import main.model.Ship;

public class GameBuilder implements IGameBuilder {
    private Player player1;
    private Player player2;
    private final ShipBuilder shipBuilder = new ShipBuilder();

    @Override
    public IGameBuilder addPlayer(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del jugador no puede estar vacío");
        }

        if (player1 == null) {
            player1 = new Player(name);
        } else if (player2 == null) {
            if (player1.getName().equals(name)) {
                throw new IllegalArgumentException("Los jugadores deben tener nombres diferentes");
            }
            player2 = new Player(name);
        } else {
            throw new IllegalStateException("El juego solo permite 2 jugadores");
        }
        return this;
    }

    @Override
    public IGameBuilder addShipToPlayer(String playerName, String shipType,
                                        int x, int y, boolean isHorizontal) {
        Player targetPlayer = getPlayerByName(playerName);
        Ship ship = shipBuilder.ofType(shipType).build();

        if (!targetPlayer.placeShip(ship, x, y, isHorizontal)) {
            throw new IllegalArgumentException(
                    String.format("No se puede colocar %s en posición (%d,%d)",
                            shipType, x, y));
        }
        return this;
    }

    @Override
    public Game build() {
        validateConfiguration();
        return new Game(player1, player2);
    }

    private void validateConfiguration() {
        if (player1 == null || player2 == null) {
            throw new IllegalStateException("Se requieren exactamente 2 jugadores");
        }

        if (player1.getShips().isEmpty() || player2.getShips().isEmpty()) {
            throw new IllegalStateException("Cada jugador debe tener al menos un barco");
        }
    }

    private Player getPlayerByName(String name) {
        if (player1 != null && player1.getName().equals(name)) {
            return player1;
        }
        if (player2 != null && player2.getName().equals(name)) {
            return player2;
        }
        throw new IllegalArgumentException("Jugador no encontrado: " + name);
    }
}

