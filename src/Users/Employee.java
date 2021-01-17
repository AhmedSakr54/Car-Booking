package Users;
import java.util.ArrayList;
public class Employee extends User implements Logable{


    private int employeeID;

    public Employee() {}
    public Employee(String firstName, String lastName, String password ,String username, int employeeid) {
        super(firstName, lastName, password,username);
        this.employeeID = employeeid;
    }

    public void setEmployeeId(int id) {
        this.employeeID = id;
    }
    public int getEmployeeId() {
        return this.employeeID;
    }

    @Override
    public User login(String username , String passwrod) {
        LoginAuthentication loginAuthentication = new LoginAuthentication();
        User emp = new Employee();
        User some = new Employee();
        emp = loginAuthentication.isMember(username,passwrod,some);
        return emp;
    }


    @Override
    public void save(ArrayList<User> information) {
        // TODO Auto-generated method stub

    }

}
