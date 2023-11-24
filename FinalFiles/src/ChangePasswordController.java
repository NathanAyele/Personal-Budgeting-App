import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

// This Class is a controller for the Change Password popup executed from the main window,
// It takes the user input and stores it into a .ser file, which could be cross-referenced to
// check if the password is correct later.
public class ChangePasswordController extends LoginController {
    @FXML
    private TextField newusername;
    @FXML
    private PasswordField newpassword;
    @FXML
    private javafx.scene.control.Button closeButton;

    // Taking user's input and saving them into a username and password fields
    // storage file
    public void ChangeButton() throws IOException {
        saveObject(newusername.getText(), true, "Username.ser");
        saveObject(newpassword.getText(), false, "Password.ser");
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
