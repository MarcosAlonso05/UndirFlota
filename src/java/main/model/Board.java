package main.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int SIZE = 10;
    private final Ship[][] shipGrid;
    private final boolean[][] attacks;
    private final List<Ship> ships;

    public Board() {
        this.shipGrid = new Ship[SIZE][SIZE];
        this.attacks = new boolean[SIZE][SIZE];
        this.ships = new ArrayList<>();
    }

    public boolean placeShip(Ship ship, int x, int y, boolean isHorizontal) {
        if (!isValidPlacement(ship, x, y, isHorizontal)) {
            return false;
        }

        if (isHorizontal) {
            for (int i = 0; i < ship.getSize(); i++) {
                shipGrid[x][y + i] = ship;
            }
        } else {
            for (int i = 0; i < ship.getSize(); i++) {
                shipGrid[x + i][y] = ship;
            }
        }
        ships.add(ship);
        return true;
    }

    private boolean isValidPlacement(Ship ship, int x, int y, boolean isHorizontal) {
        if (x < 0 || y < 0 || x >= SIZE || y >= SIZE) return false;

        int endX = isHorizontal ? x : x + ship.getSize() - 1;
        int endY = isHorizontal ? y + ship.getSize() - 1 : y;

        if (endX >= SIZE || endY >= SIZE) return false;

        for (int i = 0; i < ship.getSize(); i++) {
            int checkX = isHorizontal ? x : x + i;
            int checkY = isHorizontal ? y + i : y;
            if (shipGrid[checkX][checkY] != null) return false;
        }

        return true;
    }

    public boolean receiveAttack(int x, int y) {
        if (x < 0 || x >= SIZE || y < 0 || y >= SIZE || attacks[x][y]) {
            return false;
        }

        attacks[x][y] = true;
        if (shipGrid[x][y] != null) {
            shipGrid[x][y].hit();
            return true;
        }
        return false;
    }

    public boolean allShipsSunk() {
        return ships.stream().allMatch(Ship::isSunk);
    }

    public Ship getShipAt(int x, int y) {
        return shipGrid[x][y];
    }

    public boolean wasAttacked(int x, int y) {
        return attacks[x][y];
    }
}
