package main.repository;

import main.model.ShipRegistry;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ShipHashRegistryRepositoryImpl implements ShipHashRegistryRepository {
    private static final int NUMBER_TABLE_SIZE = 15;
    private static final int NAME_TABLE_SIZE = 15;

    private final Map<Integer, ShipRegistry> numberHashTable = new HashMap<>();
    private final Map<Integer, ShipRegistry> nameHashTable = new HashMap<>();

    @Override
    public void addShip(ShipRegistry ship) {
        int numHash = ship.getShipNumber() % NUMBER_TABLE_SIZE;
        while (numberHashTable.containsKey(numHash)) {
            numHash = (numHash + 1) % NUMBER_TABLE_SIZE;
        }
        numberHashTable.put(numHash, ship);

        int nameHash = Math.abs(ship.getShipName().hashCode()) % NAME_TABLE_SIZE;
        while (nameHashTable.containsKey(nameHash)) {
            nameHash = (nameHash + 1) % NAME_TABLE_SIZE;
        }
        nameHashTable.put(nameHash, ship);
    }

    @Override
    public ShipRegistry getByNumber(int number) {
        int hash = number % NUMBER_TABLE_SIZE;
        int originalHash = hash;

        while (numberHashTable.containsKey(hash)) {
            if (numberHashTable.get(hash).getShipNumber() == number) {
                return numberHashTable.get(hash);
            }
            hash = (hash + 1) % NUMBER_TABLE_SIZE;
            if (hash == originalHash) break;
        }
        return null;
    }

    @Override
    public ShipRegistry getByName(String name) {
        int hash = Math.abs(name.hashCode()) % NAME_TABLE_SIZE;
        int originalHash = hash;

        while (nameHashTable.containsKey(hash)) {
            if (nameHashTable.get(hash).getShipName().equals(name)) {
                return nameHashTable.get(hash);
            }
            hash = (hash + 1) % NAME_TABLE_SIZE;
            if (hash == originalHash) break;
        }
        return null;
    }
}