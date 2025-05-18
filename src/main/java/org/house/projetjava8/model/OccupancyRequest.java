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

    public OccupancyRequest(int numberOfPeople, LocalDate startDate, LocalDate endDate, boolean sameRoom, String gender, int minAge, int maxAge) {
        this.numberOfPeople = numberOfPeople;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sameRoom = sameRoom;
        this.gender = gender;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

}
