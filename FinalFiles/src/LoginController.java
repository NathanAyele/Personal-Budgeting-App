import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

// Controller Class for The Login Window before the Main Window is displayed.
public class LoginController {
    @FXML
    private Label statusLabel;
    @FXML
    private ImageView image;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password;
    @FXML
    private javafx.scene.control.Button closeButton;

    // Checks if the user's input of username and password is correct, according to
    // the .ser file that saves those values. If correct, loads the Main Window, if
    // not, produces an error message.
    public void login(ActionEvent event) throws Exception {
        if (userName.getText().equalsIgnoreCase((String) loadObject("Username.ser"))
                && password.getText().equals(loadObject("Password.ser"))) {
            statusLabel.setText("Login Success");
            Stage secondaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            secondaryStage.setTitle("Main Page");
            secondaryStage.setScene(scene);
            scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
            secondaryStage.setMaximized(true);
            secondaryStage.show();
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        } else {
            statusLabel.setText("Login Failed");
        }
    }

    // Outside Declared Method, a method that saves user's input of username and
    // password into a .ser file. If the boolean value is True: it is saving the
    // Username, if the boolean value is False: it is saving the Password.
    public static void saveObject(String obj, Boolean bool, String filepath) throws IOException {
        if (bool == true) {
            FileOutputStream fstream = new FileOutputStream("UserName.ser");
            ObjectOutputStream objectFile = new ObjectOutputStream(fstream);
            objectFile.flush();
            objectFile.writeObject(obj);
            objectFile.close();
        }
        if (bool == false) {
            FileOutputStream fstream = new FileOutputStream("Password.ser");
            ObjectOutputStream objectFile = new ObjectOutputStream(fstream);
            objectFile.flush();
            objectFile.writeObject(obj);
            objectFile.close();
        }
    }

    // Outside Declared Method, a method that loads the Username and Password values
    // to cross-reference it with what the user has made input.
    public static Object loadObject(String filepath) throws IOException, ClassNotFoundException {
        FileInputStream fstream = new FileInputStream(filepath);
        ObjectInputStream objectFile = new ObjectInputStream(fstream);
        String obj = (String) objectFile.readObject();
        objectFile.close();
        return obj;
    }

}
