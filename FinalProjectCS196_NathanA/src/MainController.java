import java.io.File;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button addIncomeButton;
    @FXML
    private Button addExpenseButton;
    @FXML
    private Button showChartsButton;
    @FXML
    private Button someButton;
    @FXML
    private Button updateTable;
    @FXML
    private Label Balance;
    @FXML
    public double balance = 0;
    @FXML
    private Label MS1;
    @FXML
    private Label MS2;
    @FXML
    private Label MS3;
    @FXML
    private TableView<EachTableLine> tableofall;
    @FXML
    private TableColumn<EachTableLine, String> ColDescription;
    @FXML
    private TableColumn<EachTableLine, String> ColCategory;
    @FXML
    private TableColumn<EachTableLine, String> ColDate;
    @FXML
    private TableColumn<EachTableLine, Double> ColAmount;

    // Creating an arraylist to add loaded datas from the text file
    ArrayList<String> descriptionarraylist = new ArrayList<>();
    ArrayList<String> categoryarraylist = new ArrayList<>();
    ArrayList<String> datearraylist = new ArrayList<>();
    ArrayList<Double> amountarraylist = new ArrayList<>();
    // Calling the Decimal Format default class, so numbers could be displayed nice.
    DecimalFormat df = new DecimalFormat("#,###.00");

    @FXML
    public void initialize() throws Exception {
        // Initialize method that loads the data from the text files into the arraylist,
        // then show those datas on the table. Also used to display the Balance and the
        // most spended money labels on the Main Window.
        loadDescription();
        loadCategory();
        loadDate();
        loadAmount();
        showTable();
        checkmostspends();
        findandsetBalance();
    }

    // Method to show Table. Assigning an observable list of values to each column
    // and setting up the data we have.
    ObservableList<EachTableLine> tablelist = FXCollections.observableArrayList();

    public void showTable() {
        for (int i = 0; i < amountarraylist.size(); i++) {
            tablelist.add(new EachTableLine(descriptionarraylist.get(i), categoryarraylist.get(i),
                    datearraylist.get(i), amountarraylist.get(i)));

            ColDescription.setCellValueFactory(new PropertyValueFactory<EachTableLine, String>("desal"));
            ColCategory.setCellValueFactory(new PropertyValueFactory<EachTableLine, String>("catal"));
            ColDate.setCellValueFactory(new PropertyValueFactory<EachTableLine, String>("datal"));
            ColAmount.setCellValueFactory(new PropertyValueFactory<EachTableLine, Double>("amoal"));
        }

        tableofall.setItems(tablelist);
    }

    // ---------- THE FOLLOWING EIGHT ARE DEFAULT SAVE AND LOAD TEXT FILE METHODS --

    public void saveDescription(String text) throws Exception {
        FileWriter filetosave = new FileWriter("DescriptionList.text", true);
        filetosave.write(text + "\n");
        filetosave.close();
        System.out.println("method is atleast working");
    }

    public void saveCategory(String text) throws Exception {
        FileWriter filetosave = new FileWriter("CategoryList.text", true);
        filetosave.write(text + "\n");
        filetosave.close();
    }

    public void saveDate(LocalDate localDate) throws Exception {
        FileWriter filetosave = new FileWriter("DateList.text", true);
        filetosave.write(localDate + "\n");
        filetosave.close();
    }

    public void saveAmount(double d) throws Exception {
        FileWriter filetosave = new FileWriter("AmountList.text", true);
        filetosave.write(d + "\n");
        filetosave.close();
    }

    public void loadDescription() throws Exception {
        Scanner filetoread = new Scanner(new File("DescriptionList.text"));
        while (filetoread.hasNextLine()) {
            String eachline = filetoread.nextLine();
            descriptionarraylist.add(eachline);
        }
        filetoread.close();
    }

    public void loadCategory() throws Exception {
        Scanner filetoread = new Scanner(new File("CategoryList.text"));
        while (filetoread.hasNextLine()) {
            String eachline = filetoread.nextLine();
            categoryarraylist.add(eachline);
        }
        filetoread.close();
    }

    public void loadDate() throws Exception {
        Scanner filetoread = new Scanner(new File("DateList.text"));
        while (filetoread.hasNextLine()) {
            String eachline = filetoread.nextLine();
            datearraylist.add(eachline);
        }
        filetoread.close();
    }

    public void loadAmount() throws Exception {
        Scanner filetoread = new Scanner(new File("AmountList.text"));
        while (filetoread.hasNext()) {
            Double eachline = filetoread.nextDouble();
            amountarraylist.add(eachline);
        }
        filetoread.close();
    }

    // THE FOLLOWING FOUR ARE METHODS IMPLEMENTED WHEN BUTTONS ON THE SIDE OF THE
    // MAIN WINDOW ARE CLICKED. THEY OPEN UP ANOTHER FXML WINDOW TO OPERATE ON --

    public void addIncomeButton(ActionEvent event) throws Exception {

        Stage thirdStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddIncome.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        thirdStage.setTitle("Add an Income");
        thirdStage.setResizable(false);
        thirdStage.setScene(scene);
        thirdStage.show();
    }

    public void addExpenseButton(ActionEvent event) throws Exception {

        Stage fourthStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AddExpense.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        fourthStage.setTitle("Record an Expense");
        fourthStage.setResizable(false);
        fourthStage.setScene(scene);
        fourthStage.show();
    }

    public void showChartsButton(ActionEvent event) throws Exception {
        Stage fifthStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ShowCharts.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        fifthStage.setTitle("Pie Charts tracking Incomes and Expenses");
        fifthStage.setResizable(false);
        fifthStage.setScene(scene);
        fifthStage.show();
    }

    public void changePasswordButton(ActionEvent event) throws Exception {
        Stage sixthStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("ChangePassword.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("DarkMode.css").toExternalForm());
        sixthStage.setTitle("Change Password Instantly");
        sixthStage.setScene(scene);
        sixthStage.show();
    }
    /* ---- THESE METHODS FIND THE BALANCE AND MOST SPENDINGS ----------- */

    public void checkmostspends() {
        Collections.sort(amountarraylist);

        MS1.setText("Most Spent \nAmt of Money \nis: $" + df.format(amountarraylist.get(0)));
        MS2.setText("2nd Most Spent Amt of Money \nis: $" + df.format(amountarraylist.get(1)));
        MS3.setText("3rd Most Spent Amt of Money \nis: $" + df.format(amountarraylist.get(2)));

    }

    public void findandsetBalance() {
        for (int i = 0; i < amountarraylist.size(); i++)
            balance += amountarraylist.get(i);

        Balance.setText("Current Balance is: \n$" + df.format(balance));
    }
}
