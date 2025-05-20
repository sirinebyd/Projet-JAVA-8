package org.house.projetjava8.model;

public class Room {
    private int id;
    private String name;
    private String genderRestriction;
    private int minAge;
    private int maxAge;

    public Room(int id, String name, String genderRestriction, int minAge, int maxAge) {
        this.id = id;
        this.name = name;
        this.genderRestriction = genderRestriction;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenderRestriction() {
        return genderRestriction;
    }

    public void setGenderRestriction(String genderRestriction) {
        this.genderRestriction = genderRestriction;
    }

    public int getMinAge() {
        return minAge;
    }

    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }
}
