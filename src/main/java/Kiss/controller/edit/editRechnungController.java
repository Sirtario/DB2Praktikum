package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addRechnungController;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class editRechnungController extends addRechnungController {

    private final String id;
    private final String days;
    private final String labor;
    private final String raum;
    private final String doktor;
    private final String paptent;

    public editRechnungController(String id, String days, String labor, String raum,
                                  String doktor, String patient, MainViewController mainViewController) {
        super(mainViewController);

        this.id = id;
        this.days = days;
        this.labor = labor;
        this.raum = raum;
        this.doktor = doktor;
        this.paptent = patient;
    }

    @Override
    @FXML
    public void initialize()
    {
        super.initialize();

        anzTageText.setText(days);
        LaborBox.getSelectionModel().select(labor);
        RaumBox.getSelectionModel().select(raum);
        DoktorBox.getSelectionModel().select(doktor);
        PatientBox.getSelectionModel().select(paptent);
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "UPDATE Rechnung SET AnzahlTage = "
                +anzTageText.getText()
                + ", LaborID = "
                +LaborBox.getSelectionModel().getSelectedItem()
                +", RaumID = "
                + RaumBox.getSelectionModel().getSelectedItem()
                + ", DoktorID = "
                + getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())
                +", PatientenID = "
                +getPatientID(PatientBox.getSelectionModel().getSelectedItem())
                +" WHERE RechnungsID = "
                +id
                + ";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
