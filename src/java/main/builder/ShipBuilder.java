package main.builder;

import main.model.*;

public class ShipBuilder implements IShipBuilder {
    private String type;

    @Override
    public IShipBuilder ofType(String type) {
        this.type = type;
        return this;
    }

    @Override
    public Ship build() {
        if (type == null) {
            throw new IllegalStateException("Debe especificar el tipo de barco");
        }

        switch (type.toLowerCase()) {
            case "battleship":
                return new Battleship();
            case "frigate":
                return new Frigate();
            case "canoe":
                return new Canoe();
            default:
                throw new IllegalArgumentException("Tipo de barco no válido: " + type);
        }
    }
}