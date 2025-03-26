package main.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Transient
    protected final int size;

    protected int hitsReceived;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ship_type_id")
    protected ShipType shipType;

    public Ship() {
        this.size = 0;
    }

    public Ship(ShipType shipType) {
        this.shipType = shipType;
        this.size = shipType.getSize();
        this.hitsReceived = 0;
    }

    public boolean isSunk() {
        return hitsReceived >= size;
    }

    public void hit() {
        hitsReceived++;
    }

    public int getSize() {
        return size;
    }

    public String getType() {
        return shipType != null ? shipType.getName() : "";
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Long getId() {
        return id;
    }

    public int getHitsReceived() {
        return hitsReceived;
    }
}