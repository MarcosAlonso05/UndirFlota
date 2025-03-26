package main.model;

import javax.persistence.Entity;

@Entity
public class Frigate extends Ship {
    public Frigate() {
        super(new ShipType("Frigate", 3));
    }

    public Frigate(ShipType shipType) {
        super(shipType);
    }
}