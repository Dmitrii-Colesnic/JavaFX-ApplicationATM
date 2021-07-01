package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.User;
import sample.mySQL.DatebaseHandler;

public class DepositController {

    User user = new User();

    public void FieldTranfer(String CardNumber, String Money) {
        user.setCardnumber(CardNumber);
        user.setMoney(Money);
    }

    @FXML
    private TextField textFieled_Amount;

    @FXML
    private Button button_Close;

    @FXML
    private Label label_successfully;

    @FXML
    void initialize() {
        button_Close.setOnAction(event -> {
            int amount = Integer.parseInt(textFieled_Amount.getText()) + Integer.parseInt(user.getMoney());

            user.setMoney(Integer.toString(amount));

            DatebaseHandler dbHandler = new DatebaseHandler();

            dbHandler.UpDateUser(user);

            label_successfully.setText("Successfully");
        });
    }
}