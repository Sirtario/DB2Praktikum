package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class addBettController {
    @FXML
    private ComboBox<String> RaumBox;
    private MainViewController mainViewController;

    public addBettController(MainViewController mainViewController) throws SQLException {
        this.mainViewController =  mainViewController;
        populateRaumBox();
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Raum (RaumID) VALUES ("+RaumBox.getSelectionModel().getSelectedItem()+");";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }

    public void populateRaumBox() throws SQLException {
        ArrayList<String> Raum = mainViewController.getDb().returnForeigKeys("SELECT RaumID FROM Raum;", "RaumID");
        for(String s : Raum){
            RaumBox.getItems().add(s);
        }
    }
}
