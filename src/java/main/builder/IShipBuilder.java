package main.builder;

import main.model.Ship;

public interface IShipBuilder {
    IShipBuilder setBattleship();
    IShipBuilder setFrigate();
    IShipBuilder setCanoe();
    Ship build();
}
