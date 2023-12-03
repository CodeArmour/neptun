import com.example.login.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationTest {

    @BeforeAll
    public static void setUp() {
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();

        try {
            Statement statement = connectDB.createStatement();
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS test_users (id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255))");
        } catch (Exception e) {
            System.out.println("Error setting up test database: " + e.getMessage());
        }
    }

    @Test
    public void testDatabaseConnection() {
        DatabaseConnection connection = new DatabaseConnection();
        assertNotNull(connection.getConnection());
    }

    @Test
    public void testLoginController() {
        // Test a valid login
        LoginController loginController = new LoginController();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        executeUpdate("INSERT INTO test_users (username, password) VALUES ('testuser', 'testpassword')", connectDB);

        loginController.setUsernameTextfield(new TextField("testuser"));
        loginController.setPasswordTextfield(new PasswordField("testpassword"));
        loginController.loginButtonOnAction(null);

        assertEquals("Connect!!", loginController.getLoginMessege().getText());

        // Test an invalid login
        loginController.setUsernameTextfield(new TextField("invaliduser"));
        loginController.setPasswordTextfield(new PasswordField("invalidpassword"));
        loginController.loginButtonOnAction(null);

        assertEquals("Invalid Login", loginController.getLoginMessege().getText());
    }

    @Test
    public void testRegisterController() {
        // Test user registration
        RegisterController registerController = new RegisterController();
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        executeUpdate("DELETE FROM test_users WHERE username='newuser'", connectDB); // Ensure the user is not already registered

        registerController.setUserNameTxt(new TextField("newuser"));
        registerController.setPasswordTxt(new PasswordField("newpassword"));
        registerController.setConfirmTxt(new PasswordField("newpassword"));
        registerController.setFirstNameTxt(new TextField("John"));
        registerController.setLastNameTxt(new TextField("Doe"));
        registerController.setSubjectTxt(new TextField("Math"));

        registerController.registerBtnOnAction(null);

        assertEquals("User registered successfully !!!!", registerController.getRegistrationMessege().getText());

        // Test registration with an existing username
        registerController.setUserNameTxt(new TextField("testuser"));
        registerController.registerBtnOnAction(null);

        assertEquals("Username already taken. Please choose another one.", registerController.getUsernameMessege().getText());
    }

    private void executeUpdate(String query, Connection connection) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Error executing update: " + e.getMessage());
        }
    }
}
