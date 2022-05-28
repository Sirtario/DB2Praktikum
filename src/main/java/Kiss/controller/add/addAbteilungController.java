package Kiss.controller.add;

import Kiss.Datenbank;
import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class addAbteilungController {
    @FXML
    protected TextField AbteilungsnameText, BeschreibungText, StandortText;

    protected MainViewController mainViewController;

    public addAbteilungController(MainViewController mainViewController){
        this.mainViewController = mainViewController;
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        if (mainViewController.fieldIsFilled(BeschreibungText.getText()) && mainViewController.fieldIsFilled(StandortText.getText()) && mainViewController.fieldIsFilled(AbteilungsnameText.getText())) {
            String querry = "INSERT INTO Abteilung (AbteilungsBeschreibung, AbteilungsStandort, AbteilungsName) VALUES ('" +
                    BeschreibungText.getText() + "','" + StandortText.getText() + "','" + AbteilungsnameText.getText() + "');";
            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        } else {
            mainViewController.showAlert(Alert.AlertType.ERROR, "Abteilung");
        }
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }

}
