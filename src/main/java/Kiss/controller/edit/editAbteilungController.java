package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addAbteilungController;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class editAbteilungController extends addAbteilungController {

    private String id;
    private final String name;
    private final String beschreibung;
    private final String standort;

    public  editAbteilungController(String id, String name,String beschreibung,String standort,MainViewController mainViewController)
    {
        super(mainViewController);
        this.id = id;
        this.name = name;
        this.beschreibung = beschreibung;
        this.standort = standort;
    }

    public void initialize()
    {
        AbteilungsnameText.setText(name);
        BeschreibungText.setText(beschreibung);
        StandortText.setText(standort);
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException
    {
        String querry = "UPDATE Abteilung SET AbteilungsBeschreibung = '"+BeschreibungText.getText()+"', AbteilungsStandort = '"+StandortText.getText()+"', AbteilungsName = '"+AbteilungsnameText.getText()+"' WHERE AbteilungsID ="+id+";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
