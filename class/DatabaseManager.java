import java.time.LocalDate;


public interface DatabaseManager {

    void addPerson(Person person);
    void updatePerson(Person person);
    void deletePerson(int id);

    void addRoom(Room room);
    void updateRoom(Room room);
    void deleteRoom(int id);

    void addBed(Bed bed);
    void updateBed(Bed bed);
    void deleteBed(int id);

    void addOccupation(Occupation occupation);
    void updateOccupation(Occupation occupation);
    void deleteOccupation(int id);
}
