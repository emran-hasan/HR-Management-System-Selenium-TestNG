package setup;

public class EmpModel {
    String fName;
    String lName;
    String username;
    String password;
    String empID;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public EmpModel(String fName, String lName, String username, String password, String empID){
        this.fName = fName;
        this.lName = lName;
        this.username = username;
        this.password = password;
        this.empID = empID;

    }
    public EmpModel(){

    }
}
