public class User {

    private String rd, fname, lname, position;

    public User(String rd, String fname, String lname, String position) {
        this.rd = rd;
        this.fname = fname;
        this.lname = lname;
        this.position = position;
    }

    public User(User user){
        this.rd = user.getRd();
        this.fname = user.getFname();
        this.lname = user.getLname();
        this.position = user.getPosition();
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
