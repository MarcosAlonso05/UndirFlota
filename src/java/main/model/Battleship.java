package main.model;

public class Battleship extends Ship {
    private final boolean[] compartments;

    public Battleship() {
        super("Battleship", 5);
        this.compartments = new boolean[5];
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
}