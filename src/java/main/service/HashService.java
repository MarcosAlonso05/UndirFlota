package main.service;

import main.model.ShipRegistry;
import main.model.ShipType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import main.repository.ShipHashRegistryRepository;
import main.repository.ShipTypeHashRepository;

import javax.transaction.Transactional;

@Service
public class HashService {
    @Autowired
    private ShipTypeHashRepository typeRepository;

    @Autowired
    private ShipHashRegistryRepository shipRepository;

    @Transactional
    public void registerShip(ShipRegistry ship) {
        ShipType type = typeRepository.getByType(ship.getShipType().getName());
        if (type == null) {
            typeRepository.addShipType(ship.getShipType());
        }

        shipRepository.addShip(ship);

        if (ship.getShipType().getName().contains("/")) {
            String[] types = ship.getShipType().getName().split("/");
            for (String t : types) {
                ShipType multiType = new ShipType(t, ship.getShipType().getSize());
                typeRepository.addShipType(multiType);
            }
        }
    }

    public ShipRegistry findShipByNumber(int number) {
        return shipRepository.getByNumber(number);
    }

    public ShipRegistry findShipByName(String name) {
        return shipRepository.getByName(name);
    }

    public ShipType findShipType(String typeName) {
        return typeRepository.getByType(typeName);
    }
}