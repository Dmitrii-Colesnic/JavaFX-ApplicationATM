package sample.Controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.User;
import sample.mySQL.DatebaseHandler;

import static sample.mySQL.Const.*;

public class Controller {

    @FXML
    private Label label_Error;

    @FXML
    private TextField field_CardNumber;

    @FXML
    private PasswordField field_Password;

    @FXML
    private Button button_LogIn;

    @FXML
    private Button button_SignUp;

    @FXML
    void initialize() {
        button_LogIn.setOnAction(event -> {
            String CardNumber = field_CardNumber.getText().trim();
            String Password = field_Password.getText().trim();

            if (!CardNumber.equals("")  &&  !Password.equals((""))) {
                loginUser(CardNumber, Password);
            } else {
                label_Error.setText("Enter CardNumber and Password");
            }
        });

        button_SignUp.setOnAction(event -> {
            FXMLLoader loader = getSceneStep1("/sample/view/SignUp.fxml", button_SignUp, 1);
            getSceneStep2(loader);
        });
    }

    public void loginUser(String CardNumber, String Password) {
        DatebaseHandler dbHandler = new DatebaseHandler();
        User user = new User();
        user.setCardnumber(CardNumber);
        user.setPassword(Password);

        ResultSet result = dbHandler.getUser(user, "yes");

        int count = 0;

        try {
            while (result.next()) {
                user.setMoney(result.getString(USERS_MONEY));
                user.setFirstname(result.getString(USERS_FIRSTNAME));
                user.setLastname(result.getString(USERS_LASTNAME));
                count++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(count >= 1) {
            FXMLLoader loader = getSceneStep1("/sample/view/LogIn.fxml", button_LogIn, 1);

            LogInController logincontroler = loader.getController();
            logincontroler.FieldTransfer(CardNumber, user.getMoney(), user.getFirstname(), user.getLastname());

            getSceneStep2(loader);
        }else {
            label_Error.setText("CardNumber or Password entered incorrectly, try again!");
        }
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