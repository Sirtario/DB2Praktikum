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

public class addLaborController {
    protected MainViewController mainViewController;

    @FXML
    protected TextField MengeText;
    @FXML
    protected DatePicker datePicker;
    @FXML
    protected ComboBox<String> DoktorBox;

    public addLaborController(MainViewController mainViewController) {
        this.mainViewController =  mainViewController;
    }

    @FXML
    public void initialize(){
        try {
            populateDoktorBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateDoktorBox() throws SQLException {
        ArrayList<String> Doktor = mainViewController.getDb().returnForeigKeys("SELECT Nachname FROM Doktor;", "Nachname");
        for(String s : Doktor){
            DoktorBox.getItems().add(s);
        }
    }

    public String getDoktorID(String Doktor) throws SQLException {
        return mainViewController.getDb().returnID("SELECT DoktorID FROM Doktor WHERE Nachname='"+Doktor+"';","DoktorID");
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {

        if(datePicker.getValue() != null &&
                mainViewController.fieldIsFilled(MengeText.getText()) &&
                mainViewController.fieldIsFilled(datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) &&
                mainViewController.fieldIsFilled(DoktorBox.getSelectionModel().getSelectedItem())) {
            String querry = "INSERT INTO Labor (Menge, Datum, DoktorID) VALUES ('"+
                    MengeText.getText()+"','"+datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"',"+
                    getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())+");";
            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        } else {
            mainViewController.showAlert(Alert.AlertType.ERROR, "Labor");
        }
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
