package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class addLaborController {
    private MainViewController mainViewController;

    @FXML
    private TextField MengeText;
    @FXML
    private DatePicker datePicker;
    @FXML
    private ComboBox<String> DoktorBox;

    public addLaborController(MainViewController mainViewController) throws SQLException {
        this.mainViewController =  mainViewController;
        datePicker = new DatePicker();
        populateDoktorBox();
    }

    public void populateDoktorBox() throws SQLException {
        ArrayList<String> Doktor = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Doktor;", "DoktorID");
        for(String s : Doktor){
            DoktorBox.getItems().add(s);
        }
    }

    public String getDoktorID(String Doktor) throws SQLException {
        return mainViewController.getDb().returnID("SELECT DoktorID FROM Doktor WHERE Nachname='"+Doktor+"'","DoktorID");
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Doktor (Menge, Datum, DoktorID) VALUES ('"+
                MengeText.getText()+"','"+datePicker.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+"',"+
                getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())+");";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
