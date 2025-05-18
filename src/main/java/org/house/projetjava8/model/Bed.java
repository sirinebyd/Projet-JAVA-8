package org.house.projetjava8.model;

public class Bed {
    private int id;
    private int roomId;
    private boolean isAvailable;

    public Bed(int id, int roomId, boolean isAvailable) {
        this.id = id;
        this.roomId = roomId;
        this.isAvailable = isAvailable;
    }

}

