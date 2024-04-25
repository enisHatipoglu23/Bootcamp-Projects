import core.Helper;
import entity.User;
import view.EmployeeView;
import view.LoginView;

public class App {
    public static void main(String[] args) {
        Helper.setTheme();
        LoginView loginView = new LoginView();
        //User user = new User();
        //EmployeeView employeeView = new EmployeeView(user);
    }
}
