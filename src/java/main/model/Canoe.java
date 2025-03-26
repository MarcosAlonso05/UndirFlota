package main.model;

import javax.persistence.Entity;

@Entity
public class Canoe extends Ship {
    public Canoe() {
        super(new ShipType("Canoe", 1));
    }

    public Canoe(ShipType shipType) {
        super(shipType);
    }
}
