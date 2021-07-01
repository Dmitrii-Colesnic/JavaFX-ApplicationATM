package sample.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class BalanceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lable_balance;

    @FXML
    private Button button_Close;

    @FXML
    void initialize() {
        button_Close.setOnAction(event -> {
            Stage stage = (Stage) button_Close.getScene().getWindow();
            stage.close();
        });
    }

    public void getPrice(String Money) {
        lable_balance.setText(Money);
    }
}