package main.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final Board primaryBoard;
    private final Board trackingBoard;
    private final List<Ship> ships = new ArrayList<>();

    public Player(String name) {
        this.name = name;
        this.primaryBoard = new Board();
        this.trackingBoard = new Board();
    }

    public boolean placeShip(Ship ship, int x, int y, boolean isHorizontal) {
        if (ships.size() >= 3) {
            return false;
        }

        if (primaryBoard.placeShip(ship, x, y, isHorizontal)) {
            ships.add(ship);
            return true;
        }
        return false;
    }

    public AttackResult receiveAttack(int x, int y) {
        boolean hit = primaryBoard.receiveAttack(x, y);
        return new AttackResult(hit, primaryBoard.getShipAt(x, y));
    }

    public void recordAttack(int x, int y, boolean hit) {
        trackingBoard.receiveAttack(x, y);
    }

    public boolean hasLost() {
        return primaryBoard.allShipsSunk();
    }

    public static class AttackResult {
        public final boolean hit;
        public final Ship ship;

        public AttackResult(boolean hit, Ship ship) {
            this.hit = hit;
            this.ship = ship;
        }
    }

    public String getName() {
        return name;
    }

    public List<Ship> getShips() {
        return ships;
    }
}
