package org.house.projetjava8.model;
import java.time.LocalDate;


public class Occupation {
    private int id;
    private int personId;
    private int bedId;
    private LocalDate startDate;
    private LocalDate endDate;

    public Occupation(int id, int personId, int bedId, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.personId = personId;
        this.bedId = bedId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}

