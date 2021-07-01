package sample.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import sample.User;
import sample.mySQL.DatebaseHandler;

public class SignUpController {

    @FXML
    private Label label_CardNumber;

    @FXML
    private Label label_Password;

    @FXML
    private TextField field_FirsName;

    @FXML
    private TextField field_LastName;

    @FXML
    private Button button_SignUp;

    @FXML
    void initialize() {
        button_SignUp.setOnAction(event -> {
        signUpNewUser();
        });
    }

    private void signUpNewUser() {
        DatebaseHandler dbHandler = new DatebaseHandler();

        String FirstName = field_FirsName.getText().trim();
        String LastName = field_LastName.getText().trim();
        String CardNumber = generateString(10);
        String Password = generateString(4);

        User user = new User(FirstName, LastName, CardNumber, Password, "0");

        dbHandler.signUpUser(user);

        label_CardNumber.setText("Your Card Number: " + user.getCardnumber());
        label_Password.setText("Your Password: " + user.getPassword());
    }

    private String generateString(int lenght) {

        String passwordSet = "1234567890";

        char[] CardNumber = new char[lenght];

        for (int i = 0; i < lenght; i++) {

            int rand = (int) (Math.random() * passwordSet.length());
            CardNumber[i] = passwordSet.charAt(rand);

        }
        return new String(CardNumber);
    }
}