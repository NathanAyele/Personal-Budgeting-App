import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;

// THis is the controller class for the Chart FXMLs
public class ShowChartsFXMLController extends MainController {
    @FXML
    private PieChart incomePieChart;
    @FXML
    private PieChart expensePieChart;

    @FXML
    public void initialize() throws Exception {
        // Adding Data to Chart, then to the FXML window
        addingDataforChart();
        incomePieChart.getData().addAll(incomepieData);
        expensePieChart.getData().addAll(expensepieData);
    }

    // Creating Observable lists for the necessary Pie chart datas
    ObservableList<PieChart.Data> incomepieData = FXCollections.observableArrayList();
    ObservableList<PieChart.Data> expensepieData = FXCollections.observableArrayList();

    // Method that adds each income and expense to the pie chart data list
    public void addingDataforChart() throws Exception {
        loadDescription();
        loadAmount();
        for (int x = 0; x < amountarraylist.size(); x++) {
            if (amountarraylist.get(x) <= 0) {
                expensepieData.add(new PieChart.Data(descriptionarraylist.get(x).toString(), amountarraylist.get(x)));
            } else {
                incomepieData.add(new PieChart.Data(descriptionarraylist.get(x).toString(), amountarraylist.get(x)));
            }
        }
    }
}
