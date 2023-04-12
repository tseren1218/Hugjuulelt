import java.sql.SQLException;
import java.util.ArrayList;

public interface VacHistoryDAOInteface {

    public ArrayList<VaccinationHistory> getAllVacHistory() throws SQLException;
    public ArrayList<VaccinationHistory> getVacHistoryByRd(String rd) throws SQLException;
    public void insertVacHistory(String rd, String vaccineName) throws SQLException;

}
