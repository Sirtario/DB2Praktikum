package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class addDoktorController {
    protected MainViewController mainViewController;

    @FXML
    protected ComboBox<String> GeschlechtBox, KontaktdatenBox;
    @FXML
    protected TextField NachnameText, VornameText, FachrichtungText;
    @FXML
    protected DatePicker GeburtstagPicker;

    public addDoktorController(MainViewController mainViewController) {
        this.mainViewController =  mainViewController;
    }

    @FXML
    public void initialize(){
        try {
            populateGeschlechtBox();
            populateKontaktdatenBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateKontaktdatenBox() throws SQLException {
        ArrayList<String> Kontaktdaten = mainViewController.getDb().returnForeigKeys("SELECT KontaktdatenID FROM Kontaktdaten;", "KontaktdatenID");
        for(String s : Kontaktdaten){
            KontaktdatenBox.getItems().add(s);
        }
    }

    public void populateGeschlechtBox() throws SQLException {
        GeschlechtBox.getItems().addAll("w","m");
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        if(mainViewController.fieldIsFilled(VornameText.getText()) &&
                mainViewController.fieldIsFilled(NachnameText.getText()) &&
                mainViewController.fieldIsFilled(GeschlechtBox.getSelectionModel().getSelectedItem()) &&
                mainViewController.fieldIsFilled(GeburtstagPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) &&
                mainViewController.fieldIsFilled(FachrichtungText.getText()) &&
                mainViewController.fieldIsFilled(KontaktdatenBox.getSelectionModel().getSelectedItem())
        ) {
            String querry = "INSERT INTO Doktor (Vorname, Nachname, Geschlecht, Geburtstag, Fachrichtung, KontaktdatenID)" +
                    " VALUES ('"+VornameText.getText()+"', '"+NachnameText.getText()+"', '"+GeschlechtBox.getSelectionModel().getSelectedItem()+
                    "', '" +GeburtstagPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+
                    "', '" +FachrichtungText.getText()+"', "+KontaktdatenBox.getSelectionModel().getSelectedItem()+");";
            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        } else {
            mainViewController.showAlert(Alert.AlertType.ERROR,"Doktor");
        }
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
