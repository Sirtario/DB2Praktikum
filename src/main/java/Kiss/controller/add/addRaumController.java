package Kiss.controller.add;

import Kiss.controller.MainViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;

public class addRaumController {
    protected MainViewController mainViewController;

    @FXML
    protected TextField RaumText;
    @FXML
    protected ComboBox<String> StatusBox, AbteilungBox;

    public addRaumController(MainViewController mainViewController) {
        this.mainViewController =  mainViewController;
    }

    @FXML
    public void initialize(){
        try {
            populateStationaerBox();
            populateAbteilungBox();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateStationaerBox() {
        StatusBox.getItems().addAll("1","0");
    }

    public void populateAbteilungBox() throws SQLException {
        ArrayList<String> Abteilung = mainViewController.getDb().returnForeigKeys("SELECT AbteilungsName FROM Abteilung;", "AbteilungsName");
        for(String s : Abteilung){
            AbteilungBox.getItems().add(s);
        }
    }

    public String getAbteilungID(String Abteilung) throws SQLException {
        return mainViewController.getDb().returnID("SELECT AbteilungsID FROM Abteilung WHERE AbteilungsName='"+Abteilung+"';","AbteilungsID");
    }

    @FXML
    private void onClickSaveEntry() throws SQLException {
        if(mainViewController.fieldIsFilled(RaumText.getText()) && mainViewController.fieldIsFilled(StatusBox.getSelectionModel().getSelectedItem()) && mainViewController.fieldIsFilled(AbteilungBox.getSelectionModel().getSelectedItem())) {
            String querry = "INSERT INTO Raum (RaumTyp, Status, AbteilungsID) VALUES ('"+
                    RaumText.getText()+"',"+StatusBox.getSelectionModel().getSelectedItem()+","+
                    getAbteilungID(AbteilungBox.getSelectionModel().getSelectedItem())+");";
            mainViewController.getDb().runQuerry(querry);
            mainViewController.getPopupStage().close();
        } else {
            mainViewController.showAlert(Alert.AlertType.ERROR, "Raum");
        }
    }

    @FXML
    private void onClickCancel(){
        mainViewController.getPopupStage().close();
    }
}
