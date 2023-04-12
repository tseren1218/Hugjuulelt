public class Vaccination {

    String fname, lname, rd, vaccineName;

    public Vaccination(String fname, String lname, String rd, String vaccineName) {
        this.fname = fname;
        this.lname = lname;
        this.rd = rd;
        this.vaccineName = vaccineName;
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

    public String getRd() {
        return rd;
    }

    public void setRd(String rd) {
        this.rd = rd;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }
}
