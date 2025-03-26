package main.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private String name;
    private List<Ship> ships;

    public Player(String name) {
        this.name = name;
        this.ships = new ArrayList<>();
    }

    public void addShip(Ship ship) {
        if (ships.size() < 3) {
            ships.add(ship);
        }
    }

    public boolean hasShipsAlive() {
        return ships.stream().anyMatch(ship -> !ship.isSunk());
    }

    public void attack(Player opponent) {
        if (opponent.hasShipsAlive()) {
            Random random = new Random();
            Ship target = opponent.ships.get(random.nextInt(opponent.ships.size()));
            int position = random.nextInt(target.size);
            target.hit(position);
        }
    }

    public String getName() {
        return name;
    }
}
