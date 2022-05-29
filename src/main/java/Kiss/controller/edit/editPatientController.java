package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addPatientController;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class editPatientController extends addPatientController {

    String id;
    String vorname;
    String nachname;
    String sex;
    String birthday;
    String stationaer;
    String insNr;
    String treadNr;
    String laborId;
    String kontakt;
    String doktor;

    public editPatientController(String id,
                                 String vorname,
                                 String nachname,
                                 String sex,
                                 String birthday,
                                 String stationaer,
                                 String insNr,
                                 String treadNr,
                                 String laborId,
                                 String kontakt,
                                 String doktor,
                                 MainViewController mainViewController) {
        super(mainViewController);
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.sex = sex;
        this.birthday=birthday;
        this.stationaer = stationaer;
        this.insNr = insNr;
        this.treadNr=treadNr;
        this.laborId=laborId;
        this.kontakt = kontakt;
        this.doktor = doktor;
    }

    @Override
    public void initialize()
    {
        super.initialize();
        VornameText.setText(vorname);
        NachnameText.setText(nachname);
        GeschlechtBox.getSelectionModel().select(sex);
        datePicker.setValue(LocalDate.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        StationaerBox.getSelectionModel().select(stationaer);
        VersicherungsnummerText.setText(insNr);
        BehandlungsnummerText.setText(treadNr);
        LaborBox.getSelectionModel().select(laborId);
        KontaktdatenBox.getSelectionModel().select(kontakt);
        DoktorBox.getSelectionModel().select(doktor);
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "UPDATE Patient Set Vorname = '"
                + VornameText.getText()
                + "', Nachname = '"
                + NachnameText.getText()
                + "', Geschlecht = '"
                + GeschlechtBox.getSelectionModel().getSelectedItem()
                + "', Geburtstag = '"
                + datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "', Stationaer = "
                + StationaerBox.getSelectionModel().getSelectedItem()
                + ", Versicherungsnummer = '"
                + VersicherungsnummerText.getText()
                + "', Behandlungsnummer = '"
                + BehandlungsnummerText.getText()
                + "', LaborID = "
                + LaborBox.getSelectionModel().getSelectedItem()
                + ", KontaktdatenID = "
                + KontaktdatenBox.getSelectionModel().getSelectedItem()
                + ", DoktorID = "
                + getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())
                + " WHERE PatientenID = "
                + id
                + ";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
