package main.model;

public abstract class Ship {
    protected int size;
    protected boolean[] hitPositions;

    public Ship(int size) {
        this.size = size;
        this.hitPositions = new boolean[size];
    }

    public void hit(int position) {
        if (position >= 0 && position < size) {
            hitPositions[position] = true;
        }
    }

    public boolean isSunk() {
        for (boolean hit : hitPositions) {
            if (!hit) return false;
        }
        return true;
    }
}