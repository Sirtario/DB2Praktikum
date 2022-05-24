package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class addDiagnoseController {
    private MainViewController mainViewController;

    @FXML
    private ComboBox<String> PatientBox;
    @FXML
    private TextField BezeichnungText;

    public addDiagnoseController(MainViewController mainViewController) throws SQLException {
        this.mainViewController =  mainViewController;
        populatePatientBox();
    }

    public void populatePatientBox() throws SQLException {
        ArrayList<String> Patient = mainViewController.getDb().returnForeigKeys("SELECT RaumID FROM Raum;");
        for(String s : Patient){
            PatientBox.getItems().add(s);
        }
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        String querry = "INSERT INTO Diagnose (PatientenID, DiagnoseBezeichnung) VALUES ("+PatientBox.getSelectionModel().getSelectedItem()+", '"+BezeichnungText+"');";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }

}
