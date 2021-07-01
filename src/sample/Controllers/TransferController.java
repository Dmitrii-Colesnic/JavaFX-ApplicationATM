package sample.Controllers;

import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.User;
import sample.mySQL.DatebaseHandler;

import static sample.mySQL.Const.*;

public class TransferController {

    User user = new User();

    public void FieldTransfere(String CardNumber, String Money) {
        user.setCardnumber(CardNumber);
        user.setMoney(Money);
    }

    @FXML
    private TextField field_CardNumber;

    @FXML
    private Button button_Transfer;

    @FXML
    private Label label_Eror;

    @FXML
    private TextField textField_Amount;

    @FXML
    void initialize() {
        label_Eror.setAlignment(Pos.CENTER);

        button_Transfer.setOnAction(event ->  {
            User userTransfere = new User();
            userTransfere.setCardnumber(field_CardNumber.getText());

            userTransfere = loginUser(userTransfere);

            if ((Integer.parseInt(user.getMoney()) >= Integer.parseInt(textField_Amount.getText())) && !(userTransfere.getMoney()==null)) {
                int amountUserTransfer = Integer.parseInt(textField_Amount.getText()) + Integer.parseInt(userTransfere.getMoney());
                userTransfere.setMoney(Integer.toString(amountUserTransfer));

                int amountUser = Integer.parseInt(user.getMoney()) - Integer.parseInt(textField_Amount.getText());
                user.setMoney(Integer.toString(amountUser));

                DatebaseHandler dbHandler = new DatebaseHandler();
                dbHandler.UpDateUser(user);
                dbHandler.UpDateUser(userTransfere);

                label_Eror.setText("Successfully");
            } else if (!(Integer.parseInt(user.getMoney()) >= Integer.parseInt(textField_Amount.getText()))) {
                label_Eror.setText("The transfer amount is more than your account!");
            }
        });
    }

    public User loginUser(User user) {
        DatebaseHandler dbHandler = new DatebaseHandler();

        ResultSet result = dbHandler.getUser(user, "no");

        int count = 0;

        try {
            while (result.next()) {
                user.setMoney(result.getString(USERS_MONEY));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(user.getMoney()==null) {
            label_Eror.setText("CardNumber entered incorrectly, try again!");
        }

        return user;
    }
}