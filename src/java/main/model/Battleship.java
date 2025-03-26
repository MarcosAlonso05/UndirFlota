package main.model;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class Battleship extends Ship {
    @Transient
    private boolean[] compartments;

    public Battleship() {
        super(new ShipType("Battleship", 5));
        this.compartments = new boolean[5];
    }

    public Battleship(ShipType shipType) {
        super(shipType);
        this.compartments = new boolean[shipType.getSize()];
    }

    @Override
    public void hit() {
        for (int i = 0; i < compartments.length; i++) {
            if (!compartments[i]) {
                compartments[i] = true;
                hitsReceived++;
                break;
            }
        }
    }

    @Override
    public boolean isSunk() {
        for (boolean compartment : compartments) {
            if (!compartment) {
                return false;
            }
        }
        return true;
    }

    public boolean[] getCompartments() {
        return compartments;
    }
}