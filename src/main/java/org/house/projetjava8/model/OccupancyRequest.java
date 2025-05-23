package org.house.projetjava8.model;
import java.time.LocalDate;
import java.util.List;

public class OccupancyRequest {
    private int numberOfPeople;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean sameRoom;
    private String gender;
    private int minAge;
    private int maxAge;

    public OccupancyRequest() {}

    public OccupancyRequest(int numberOfPeople, LocalDate startDate, LocalDate endDate, boolean sameRoom, String gender, int minAge, int maxAge) {
        this.numberOfPeople = numberOfPeople;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sameRoom = sameRoom;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }



    public OccupancyRequest(int nb, String g, String dDebut, String dFin) {
        this.numberOfPeople = nb;
        this.gender = g;
        this.startDate = LocalDate.parse(dDebut);
        this.endDate = LocalDate.parse(dFin);
    }


    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
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

    public boolean isSameRoom() {
        return sameRoom;
    }

    public void setSameRoom(boolean sameRoom) {
        this.sameRoom = sameRoom;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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
