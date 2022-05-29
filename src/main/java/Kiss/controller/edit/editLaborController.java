package Kiss.controller.edit;

import Kiss.controller.MainViewController;
import Kiss.controller.add.addLaborController;
import javafx.fxml.FXML;
import javafx.util.converter.LocalDateStringConverter;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class editLaborController extends addLaborController {
    private final String id;
    private final String date;
    private final String menge;
    private final String docId;

    public editLaborController(String id, String date, String menge, String docId, MainViewController mainViewController) {
        super(mainViewController);
        this.id = id;
        this.date = date;
        this.menge = menge;
        this.docId = docId;
    }
    @Override
    @FXML
    public void initialize()
    {
        super.initialize();
        MengeText.setText(menge);
        DoktorBox.getSelectionModel().select(docId);
        datePicker.setValue(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
    }

    @Override
    @FXML
    protected void onClickSaveEntry() throws SQLException {
        String querry = "UPDATE Labor Set Datum ='"
                + datePicker.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        +"', Menge = '"
        + MengeText.getText()
        + "', DoktorID = "
        + getDoktorID(DoktorBox.getSelectionModel().getSelectedItem())
        +" WHERE LaborID = "
        +id
        +";";
        mainViewController.getDb().runQuerry(querry);
        mainViewController.getPopupStage().close();
    }
}
