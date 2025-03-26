package main.model;

public abstract class Ship {
    protected final int size;
    protected int hitsReceived;
    protected final String type;

    public Ship(String type, int size) {
        this.type = type;
        this.size = size;
        this.hitsReceived = 0;
    }

    public boolean isSunk() {
        return hitsReceived >= size;
    }

    public void hit() {
        hitsReceived++;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return type;
    }
}