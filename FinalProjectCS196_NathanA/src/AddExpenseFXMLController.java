
//FXML Window that pops up to add an expense
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddExpenseFXMLController extends MainController {
    @FXML
    private TextField Description;
    @FXML
    private DatePicker Date;
    @FXML
    private TextField Amount;
    @FXML
    private ChoiceBox<String> Category;
    @FXML
    private javafx.scene.control.Button closeButton;

    // Creating an arraylist of options for the Choice Box for the type of expenses
    ObservableList<String> categorylist = FXCollections.observableArrayList("Credit Card Payment",
            "Shopping", "Donations", "Dining and Drinks", "Cash and Checks", "Education", "Family Care",
            "Fees", "Gifts", "Health and Wellness", "Home and Garden", "Loan Payment", "Medical", "Personal Care",
            "Pets", "Savings Transfer", "Taxes", "Travel and Vacation", "Business", "Lending",
            "Legal Costs", "Investments", "Technology", "Miscellaneous", "Other");

    public void initialize() {
        Category.setItems(categorylist);
    }

    // 1. Checking whether every field is filled;
    // 2. Saving the user's entry to a text file.
    public void onButtonAddClick() throws Exception {
        if (Description.getText() == null || Category.getValue() == null
                || Date.getValue() == null || Amount.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            String content = "Please fill all the requested fields";
            alert.setContentText(content);
            alert.showAndWait();
        } else {
            saveDescription(Description.getText());
            saveCategory(Category.getValue());
            saveDate(Date.getValue());
            saveAmount(Double.parseDouble(Amount.getText()));
            Stage stage = (Stage) closeButton.getScene().getWindow();
            stage.close();
        }
    }

}