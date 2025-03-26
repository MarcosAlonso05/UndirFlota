package main.repository;

import main.model.ShipType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ShipTypeHashRepository {
    private static final int TYPE_TABLE_SIZE = 10;

    @PersistenceContext
    private EntityManager entityManager;

    private final Map<Integer, ShipType> hashTable = new HashMap<>();

    public void addShipType(ShipType type) {
        int hash = type.getName().hashCode() % TYPE_TABLE_SIZE;
        hash = Math.abs(hash);

        // Sondeo lineal
        while (hashTable.containsKey(hash)) {
            hash = (hash + 1) % TYPE_TABLE_SIZE;
        }

        hashTable.put(hash, type);
        entityManager.persist(type);
    }

    public ShipType getByType(String typeName) {
        int hash = typeName.hashCode() % TYPE_TABLE_SIZE;
        hash = Math.abs(hash);
        int originalHash = hash;

        while (hashTable.containsKey(hash)) {
            if (hashTable.get(hash).getName().equals(typeName)) {
                return hashTable.get(hash);
            }
            hash = (hash + 1) % TYPE_TABLE_SIZE;
            if (hash == originalHash) break;
        }
        return null;
    }
}