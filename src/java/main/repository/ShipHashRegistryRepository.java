package main.repository;

import main.model.ShipRegistry;
import org.springframework.stereotype.Repository;

@Repository
public interface ShipHashRegistryRepository {
    void addShip(ShipRegistry ship);
    ShipRegistry getByNumber(int number);
    ShipRegistry getByName(String name);
}