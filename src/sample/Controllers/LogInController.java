package sample.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import sample.User;

public class LogInController {

    User user = new User();

    public void FieldTransfer(String CardNumber, String Money, String FirstName, String LastName) {
        label_FrstName.setText(FirstName);
        label_FrstName.setAlignment(Pos.BOTTOM_RIGHT);

        label_LastName.setText(LastName);

        user.setCardnumber(CardNumber);
        user.setMoney(Money);
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button button_Transfer;

    @FXML
    private Button button_Balance;

    @FXML
    private Button button_Deposit;

    @FXML
    private Label label_FrstName;

    @FXML
    private Label label_LastName;

    @FXML
    void initialize() {
        button_Balance.setOnAction(event -> {
            FXMLLoader loader = getSceneStep1("/sample/view/Balance.fxml",button_Balance,0);

            BalanceController balancecontroller = loader.getController();
            balancecontroller.getPrice(user.getMoney());

            getSceneStep2(loader);
        });

        button_Deposit.setOnAction(event -> {
            FXMLLoader loader = getSceneStep1("/sample/view/Deposit.fxml",button_Deposit,1);

            DepositController depositController = loader.getController();
            depositController.FieldTranfer(user.getCardnumber(), user.getMoney());

            getSceneStep2(loader);
        });

        button_Transfer.setOnAction(event -> {
            FXMLLoader loader = getSceneStep1("/sample/view/Transfer.fxml",button_Transfer,1);

            TransferController transfercontroller = loader.getController();
            transfercontroller.FieldTransfere(user.getCardnumber(), user.getMoney());

            getSceneStep2(loader);
        });
    }

    private FXMLLoader getSceneStep1(String parth, Button button, int change) {
        if (change==1)
            button.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(parth));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return loader;
    }

    private void getSceneStep2(FXMLLoader loader) {
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
