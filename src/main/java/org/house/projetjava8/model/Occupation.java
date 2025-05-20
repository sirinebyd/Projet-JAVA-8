package org.house.projetjava8.model;

import java.time.LocalDate;

public class Occupation {
    private int id;
    private int personId;
    private int bedId;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean hasLeft;

    public Occupation(int id, int personId, int bedId, LocalDate startDate, LocalDate endDate, boolean hasLeft) {
        this.id = id;
        this.personId = personId;
        this.bedId = bedId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hasLeft = hasLeft;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getBedId() {
        return bedId;
    }

    public void setBedId(int bedId) {
        this.bedId = bedId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isHasLeft() {
        return hasLeft;
    }

    public void setHasLeft(boolean hasLeft) {
        this.hasLeft = hasLeft;
    }
}
