package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addDiagnoseController;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class editDiagnoseController extends addDiagnoseController {
    private final String id;
    private final String patient;
    private final String bezeichnung;

    public editDiagnoseController(String id, String patient, String bezeichnung, MainViewController mainViewController) {
        super(mainViewController);
        this.id = id;
        this.patient =patient;
        this.bezeichnung = bezeichnung;
    }

    @Override
    @FXML
    public void initialize(){
       super.initialize();
       PatientBox.getSelectionModel().select(patient);
       BezeichnungText.setText(bezeichnung);
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry ="UPDATE Diagnose SET PatientenID = '"
                +PatientBox.getSelectionModel().getSelectedItem()
                +"', DiagnoseBezeichnung = '"
                +BezeichnungText.getText()
                +"' WHERE DiagnoseID ="
                +id+";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
