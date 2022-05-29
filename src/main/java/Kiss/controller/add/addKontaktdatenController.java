package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class addKontaktdatenController {
    protected MainViewController mainViewController;

    @FXML
    protected TextField TelefonText, EMailText, StrasseText, PLZText, StadtText;

    public addKontaktdatenController(MainViewController mainViewController){
        this.mainViewController =  mainViewController;
    }

    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Kontaktdaten (Telefon, EMail, Strasse, PLZ, Stadt) VALUES ('"+
                TelefonText.getText()+"','"+EMailText.getText()+"','"+StrasseText.getText()+"','"+PLZText.getText()+"', '"+StadtText.getText()+"');";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
