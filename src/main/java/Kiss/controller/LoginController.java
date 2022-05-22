package Kiss.controller;

import Kiss.model.DatabaseConnector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.Console;
import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private AnchorPane ViewBase;
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
        if(ConnectToDB(UserNameInput.getText(), PasswordInput.getText()))
        {
            try {
                LoadMainView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void LoadMainView() throws IOException {
        Stage stage = (Stage) ViewBase.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("../../view/main.fxml"));
        stage.setTitle("KIS");
        stage.setScene(new Scene(root, 400, 300));
        stage.show();
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

    private Boolean ConnectToDB(String user, String pass) {

        try{
            DatabaseConnector.ConnectToDB(UserNameInput.getText(), PasswordInput.getText());
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
