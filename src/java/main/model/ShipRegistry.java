package main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ShipRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int shipNumber;
    private String shipName;
    private int level;

    @ManyToOne
    private ShipType shipType;

    public ShipRegistry() {}

    public ShipRegistry(int shipNumber, String shipName, int level, ShipType shipType) {
        this.shipNumber = shipNumber;
        this.shipName = shipName;
        this.level = level;
        this.shipType = shipType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getShipNumber() {
        return shipNumber;
    }

    public void setShipNumber(int shipNumber) {
        this.shipNumber = shipNumber;
    }

    public String getShipName() {
        return shipName;
    }

    public void setShipName(String shipName) {
        this.shipName = shipName;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }
}
