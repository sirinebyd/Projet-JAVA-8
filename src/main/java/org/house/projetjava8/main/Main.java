import org.house.projetjava8.dao.DatabaseManager;
import org.house.projetjava8.model.*;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        try {
            DatabaseManager.connect();

            

            DatabaseManager.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
