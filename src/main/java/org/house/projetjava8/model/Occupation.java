package org.house.projetjava8.model;
import java.time.LocalDate;

public class Occupation {
    private int id;
    private Person person;
    private Bed bed;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean hasLeft;
}
