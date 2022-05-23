package Kiss.controller.add;

import Kiss.Datenbank;
import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class addAbteilungController {
    @FXML
    private TextField AbteilungsnameText, BeschreibungText, StandortText;

    private MainViewController mainViewController;

    public addAbteilungController(MainViewController mainViewController){
        this.mainViewController = mainViewController;
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Abteilung (AbteilungsBeschreibung, AbteilsungsStandort, AbteilungsName) VALUES ('"+BeschreibungText.getText()+"','"+StandortText.getText()+"','"+AbteilungsnameText.getText()+"')";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
