import java.sql.SQLException;
import java.util.ArrayList;

public interface VacHistoryInterface {

    public ArrayList<VaccinationHistory> getAllVacHistory() throws SQLException;
    public ArrayList<VaccinationHistory> getVacHistoryByRd(String rd) throws SQLException;
    public void insertVacHistory(VaccinationHistory vh);

}
