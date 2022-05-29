package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addRaumController;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class editRaumController extends addRaumController {


    private final String id;
    private final String text;
    private final String status;
    private final String abteilung;

    public editRaumController(String id, String text, String status, String abteilung, MainViewController mainViewController) {
        super(mainViewController);

        this.id = id;
        this.text =text;
        this.status = status;
        this.abteilung = abteilung;
    }

    @Override
    @FXML
    public void initialize()
    {
        super.initialize();

        RaumText.setText(text);
        StatusBox.getSelectionModel().select(status);
        AbteilungBox.getSelectionModel().select(abteilung);

    }
    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "UPDATE Raum SET RaumTyp = '"
                + RaumText.getText()
                + "', Status = "
                + StatusBox.getSelectionModel().getSelectedItem()
                +", AbteilungsID = "
                + getAbteilungID(AbteilungBox.getSelectionModel().getSelectedItem())
                +" WHERE RaumID = "
                +id
                +";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
