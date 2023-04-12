import javax.swing.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VaccinationHistoryDAO implements VacHistoryDAOInteface {

    DatabaseConnection dbcon;
    ArrayList<VaccinationHistory> vh;

    public VaccinationHistoryDAO() throws SQLException {
        dbcon = DatabaseConnection.getInstance();
    }


    @Override
    public ArrayList<VaccinationHistory> getAllVacHistory() throws SQLException {

        vh = new ArrayList<VaccinationHistory>();
        String sql = "select " +
                "u.rd, u.fname, u.lname, v.vaccineName, vh.date " +
                "from " +
                "users u, vaccination_history vh, vaccines v " +
                "where u.rd = vh.rd and " +
                "vh.vaccineId = v.id ";
        PreparedStatement pst = dbcon.getConnection().prepareStatement(sql);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            vh.add(new VaccinationHistory(rs.getString("rd"), rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("vaccineName"),
                    rs.getDate("date")));
        }
        return vh;
    }

    @Override
    public ArrayList<VaccinationHistory> getVacHistoryByRd(String rd) throws SQLException {
        vh = new ArrayList<VaccinationHistory>();
        String sql = "select " +
                "u.rd, u.fname, u.lname, v.vaccineName, vh.date " +
                "from " +
                "users u, vaccination_history vh, vaccines v " +
                "where u.rd = vh.rd and " +
                "vh.vaccineId = v.id " +
                "and " +
                "vh.rd = ?";
        PreparedStatement pst = dbcon.getConnection().prepareStatement(sql);
        pst.setString(1, rd);
        ResultSet rs = pst.executeQuery();

        while(rs.next()){
            vh.add(new VaccinationHistory(rs.getString("rd"), rs.getString("fname"),
                    rs.getString("lname"),
                    rs.getString("vaccineName"),
                    rs.getDate("date")));
        }
        return vh;
    }


    @Override
    public void insertVacHistory(String rd, String vaccineName) throws SQLException {

        String vacIdSql = "select id from vaccines where vaccineName = '" + vaccineName + "'";
        PreparedStatement vacIdPst = dbcon.getConnection().prepareStatement(vacIdSql);
        ResultSet rs = vacIdPst.executeQuery();
        rs.next();
        String vacId = rs.getString("id");

        String sql = "insert into vaccination_history " +
                "set rd = ?, " + "vaccineId = ?, " + "date = now()";
        PreparedStatement pst = dbcon.getConnection().prepareStatement(sql);
        pst.setString(1, rd);
        pst.setString(2, vacId);
        int addedRows = pst.executeUpdate();
        if(addedRows > 0){
            JFrame successFrame = new JFrame();
            successFrame.setLocationRelativeTo(null);
            successFrame.setSize(200, 200);
            JLabel successLabel = new JLabel("Амжилттай бүртгэлээ!");
            successFrame.add(successLabel);
            successFrame.setVisible(true);
        }
    }


}
