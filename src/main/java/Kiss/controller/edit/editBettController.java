package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addBettController;
import javafx.fxml.FXML;

import java.sql.SQLException;

public class editBettController extends addBettController {
    private final String raum;
    private final String bett;

    public editBettController(String bett,String raum, MainViewController mainViewController) {
        super(mainViewController);
        this.raum = raum;
        this.bett = bett;
    }

    @Override
    public void initialize(){
        super.initialize();
        RaumBox.getSelectionModel().select(raum);
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "UPDATE Bett SET RAUMID ='"+RaumBox.getSelectionModel().getSelectedItem()+"' WHERE BettID = "+bett+";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
