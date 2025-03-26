package main.builder;

import main.model.Ship;

public interface IShipBuilder {
    IShipBuilder ofType(String type);
    Ship build();
}
