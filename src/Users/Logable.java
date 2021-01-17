package Users;
import java.util.ArrayList;
public interface Logable {
    public User login(String username , String password);


    public void logout();


    public void save(ArrayList<User> information);
}
