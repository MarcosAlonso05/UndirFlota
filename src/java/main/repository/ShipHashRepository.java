package main.repository;

import main.model.Ship;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ShipHashRepository {
    private static final int TABLE_SIZE = 15;

    @PersistenceContext
    private EntityManager entityManager;

    private final Map<Integer, Ship> hashTable = new HashMap<>();

    public void addShip(Ship ship) {
        int hash = Math.abs(ship.getType().hashCode()) % TABLE_SIZE;

        // Manejo de colisiones
        while (hashTable.containsKey(hash)) {
            hash = (hash + 1) % TABLE_SIZE;
        }

        hashTable.put(hash, ship);
        entityManager.persist(ship);
    }

    public Ship getByType(String type) {
        int hash = Math.abs(type.hashCode()) % TABLE_SIZE;
        int originalHash = hash;

        while (hashTable.containsKey(hash)) {
            if (hashTable.get(hash).getType().equals(type)) {
                return hashTable.get(hash);
            }
            hash = (hash + 1) % TABLE_SIZE;
            if (hash == originalHash) break;
        }
        return null;
    }
}