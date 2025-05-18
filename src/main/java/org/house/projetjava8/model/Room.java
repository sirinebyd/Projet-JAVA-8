package org.house.projetjava8.model;
import java.util.List;
public class Room {
    private int id;
    private String name;
    private int capacity;
    private boolean isMixed; // true = mixte, false = non-mixte

    public Room(int id, String name, int capacity, boolean isMixed) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
        this.isMixed = isMixed;
    }

}
