package org.house.projetjava8.model;

public class Bed {
    private static int id;
    private String label;
    private int capacity;
    private int roomId;

    public Bed(int id, String label, int capacity, int roomId) {
        this.id = id;
        this.label = label;
        this.capacity = capacity;
        this.roomId = roomId;
    }

   
    public static int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}


