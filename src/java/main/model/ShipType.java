package main.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ShipType {
    @Id
    private String name;
    private int size;

    public ShipType() {
    }

    public ShipType(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}