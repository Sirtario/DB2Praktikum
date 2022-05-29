package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addDoktorController;
import javafx.fxml.FXML;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class editDoktorController extends addDoktorController {
    private final String id;
    private final String vorname;
    private final String nachname;
    private final String sex;
    private final String fachrichtung;
    private final String kontakt;
    private final String geburtstag;

    public editDoktorController(String id, String vorname, String nachname, String sex, String fachrichtung, String kontakt, String geburtstag, MainViewController mainViewController) {
        super(mainViewController);
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.sex = sex;
        this.fachrichtung = fachrichtung;
        this.kontakt=kontakt;
        this.geburtstag = geburtstag;
    }

    @Override
    @FXML
    public void initialize()
    {
        super.initialize();
        GeschlechtBox.getSelectionModel().select(sex);
        KontaktdatenBox.getSelectionModel().select(kontakt);
        NachnameText.setText(nachname);
        VornameText.setText(vorname);
        FachrichtungText.setText(fachrichtung);
        GeburtstagPicker.setValue(LocalDate.parse(geburtstag,DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry ="UPDATE Doktor Set Vorname = '"
                +VornameText.getText()
                +"', Nachname = '"
                + NachnameText.getText()
                + "', Geschlecht = '"
                + GeschlechtBox.getSelectionModel().getSelectedItem()
                +"', Fachrichtung = '"
                + FachrichtungText.getText()
                +"', KontaktdatenID = "
                + KontaktdatenBox.getSelectionModel().getSelectedItem()
                + ", Geburtstag = '"
                + GeburtstagPicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                + "' WHERE DoktorID = "
                + id
                +";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
