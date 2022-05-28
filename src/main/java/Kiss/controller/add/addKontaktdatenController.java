package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
    private void onClickSaveEntry() throws SQLException {

        if(mainViewController.fieldIsFilled(TelefonText.getText()) &&
                mainViewController.fieldIsFilled(EMailText.getText()) &&
                        mainViewController.fieldIsFilled(StrasseText.getText()) &&
                        mainViewController.fieldIsFilled(StrasseText.getText()) &&
                mainViewController.fieldIsFilled(StrasseText.getText())
        ) {
            String querry = "INSERT INTO Kontaktdaten (Telefon, EMail, Straße, PLZ, Stadt) VALUES ('"+
                    TelefonText.getText()+"','"+EMailText.getText()+"','"+StrasseText.getText()+"','"+PLZText.getText()+"', '"+StadtText.getText()+"');";
            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        } else {
            mainViewController.showAlert(Alert.AlertType.ERROR, "Kontakdaten");
        }

    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
