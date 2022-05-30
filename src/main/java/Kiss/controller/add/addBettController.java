package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class addBettController {
    @FXML
    protected ComboBox<String> RaumBox;
    protected MainViewController mainViewController;

    public addBettController(MainViewController mainViewController) {
        this.mainViewController =  mainViewController;
    }

    @FXML
    protected void onClickSaveEntry() throws SQLException {
        if(mainViewController.fieldIsFilled(RaumBox.getSelectionModel().getSelectedItem())) {
            String querry = "INSERT INTO Bett (RaumID) VALUES ("+RaumBox.getSelectionModel().getSelectedItem()+");";
            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        }
        else {
            mainViewController.showAlert(Alert.AlertType.ERROR, "Bett");
        }
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }

    public void populateRaumBox() throws SQLException {
        ArrayList<String> Raum = mainViewController.getDb().returnForeigKeys("SELECT RaumID FROM Raum;", "RaumID");
        for(String s: Raum){
            RaumBox.getItems().add(s);
        }
    }

    @FXML
    public void initialize(){
        try {
            populateRaumBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
