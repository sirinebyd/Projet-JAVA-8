package org.house.projetjava8.model;
import java.time.LocalDate;
import java.util.List;

public class OccupancyRequest {
    private LocalDate minDate;
    private LocalDate maxDate;
    private boolean sameRoomRequired;
    private List<PersonCriteria> personCriteriaList;

    public static class PersonCriteria {
        private String gender;
        private int age;
    }

}
