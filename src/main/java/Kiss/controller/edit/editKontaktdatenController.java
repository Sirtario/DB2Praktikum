package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addKontaktdatenController;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class editKontaktdatenController extends addKontaktdatenController {
    private final String id;
    private final String telefon;
    private final String email;
    private final String street;
    private final String plz;
    private final String city;

    public editKontaktdatenController(String id, String telefon, String email, String street, String plz, String city, MainViewController mainViewController) {
        super(mainViewController);
        this.id = id;
        this.telefon = telefon;
        this.email = email;
        this.street = street;
        this.plz = plz;
        this.city = city;
    }

    public void initialize()
    {
        TelefonText.setText(telefon);
        EMailText.setText(email);
        StadtText.setText(city);
        StrasseText.setText(street);
        PLZText.setText(plz);
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "UPDATE Kontaktdaten SET Telefon = "
                +TelefonText.getText()
                + ", Email = '"
                + EMailText.getText()
                + "', Strasse = '"
                + StrasseText.getText()
                + "', PLZ = '"
                + PLZText.getText()
                + "', Stadt = '"
                + StadtText.getText()
                + "' WHERE KontaktdatenID = "
                + id
                + ";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
