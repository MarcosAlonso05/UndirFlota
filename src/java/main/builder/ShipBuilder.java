package main.builder;

import main.model.*;

public class ShipBuilder implements IShipBuilder {
    private Ship ship;

    @Override
    public IShipBuilder setBattleship() {
        this.ship = new Battleship();
        return this;
    }

    @Override
    public IShipBuilder setFrigate() {
        this.ship = new Frigate();
        return this;
    }

    @Override
    public IShipBuilder setCanoe() {
        this.ship = new Canoe();
        return this;
    }

    @Override
    public Ship build() {
        return this.ship;
    }
}