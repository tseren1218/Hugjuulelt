import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VaccinationHistory {

    String rd, fname, lname, vaccineName;
    Date date;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");


    public VaccinationHistory(String rd, String fname, String lname, String vaccineName, Date _date) {
        this.rd = rd;
        this.fname = fname;
        this.lname = lname;
        this.vaccineName = vaccineName;
        date = new Date();
        dateFormat.format(this.date);
        dateFormat.format(_date);
        this.date = _date;

    }

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SimpleDateFormat getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(SimpleDateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }
}
