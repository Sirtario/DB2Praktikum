package Kiss.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.Console;
import java.sql.*;

public class LoginController {
    @FXML
    private TextField UserNameInput;
    @FXML
    private PasswordField PasswordInput;

    @FXML
    private void onLoginButtonClick()
    {
        //check if username and password are filled in
        if(!CredentialsAreFilledIn())
        {
            //print error
            //TODO: errorhandling
            return;
        }
        //create database connection with the credentials
        ConnectToDB(UserNameInput.getText(), PasswordInput.getText());

    }

    private boolean CredentialsAreFilledIn() {
        if (UserNameInput.getText() == "")
        {
            return false;
        }
        if (PasswordInput.getText()==""){
            return false;
        }
        return true;
    }

    private void ConnectToDB(String user, String pass) {
        String connectionUrl =
                "jdbc:sqlserver://68.183.223.224;"
                        + "database=KIS;"
                        + "user="+user+";"
                        + "password="+pass+";"
                        + "encrypt=true;"
                        + "trustServerCertificate=true;"
                        + "loginTimeout=30;";


        try{
            Connection con = DriverManager.getConnection(connectionUrl);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
