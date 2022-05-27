package Kiss.controller;

import Kiss.Datenbank;
import Kiss.controller.add.*;
import Kiss.model.XmlExporterServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainViewController {
    @FXML
    private Button addButton;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab Abteilung, Bett, Diagnose, Doktor, Kontaktdaten, Labor, Patient, Rechnung, Raum;
    @FXML
    private TableView AbteilungTable, BettTable, DiagnoseTable, DoktorTable, KontaktdatenTable, LaborTable, PatientTable, RechnungTable, RaumTable;

    private Stage popupStage;

    private XmlExporterServiceImpl xmlExporterServiceImpl;
    private Datenbank db;
    @FXML
    public void initialize(){
        Abteilung.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(AbteilungTable);
            UpdateTableView("Abteilung");
        });

        Bett.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(BettTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Bett;");
                GenerateTableHead(BettTable,resul);
                FillTableWithContent(BettTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });

        Diagnose.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(DiagnoseTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Diagnose;");
                GenerateTableHead(DiagnoseTable,resul);
                FillTableWithContent(DiagnoseTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });

        Doktor.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(DoktorTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Doktor;");
                GenerateTableHead(DoktorTable,resul);
                FillTableWithContent(DoktorTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });

        Kontaktdaten.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(KontaktdatenTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Kontaktdaten;");
                GenerateTableHead(KontaktdatenTable,resul);
                FillTableWithContent(KontaktdatenTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });
        Labor.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(LaborTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Labor;");
                GenerateTableHead(LaborTable,resul);
                FillTableWithContent(LaborTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });

        Rechnung.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(RechnungTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Rechnung;");
                GenerateTableHead(RechnungTable,resul);
                FillTableWithContent(RechnungTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });

        Patient.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(PatientTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Patient;");
                GenerateTableHead(PatientTable,resul);
                FillTableWithContent(PatientTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });

        Raum.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(RaumTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Raum;");
                GenerateTableHead(RaumTable,resul);
                FillTableWithContent(RaumTable,resul);
            } catch (SQLException e)
            {
                Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
                error.showAndWait();
            }
        });
    }

    private void UpdateTableView(String table) {
        ResultSet resul;
        try {
            resul=db.runQuerry("Select * from "+table+";");
            GenerateTableHead(AbteilungTable,resul);
            FillTableWithContent(AbteilungTable,resul);
        } catch (SQLException e)
        {
            Alert error = new Alert(Alert.AlertType.ERROR,e.getMessage(),ButtonType.OK);
            error.showAndWait();
        }
    }

    public void setDatenbank(Datenbank db){
        this.db = db;
    }

    /*
    @FXML
    private void onExportXML() {
        XmlExporterService xmlExporterService = new XmlExporterServiceImpl();

        xmlExporterService.createXMLFileFromDatabase(db.getConnection(), "Abteilung");
    }

    @FXML
    private void onDeleteButtonClick(){
        String table = tabPane.getSelectionModel().getSelectedItem().toString();
        switch(table){
            case "Abteilung": db.deleteEntry("Abteilung", "AbteilungsID", //TODO: Hier muss dann jeweils der Wert der ersten Zelle der ausgewählten Zeile rein(also halt die ID));
            case "Bett": db.deleteEntry("Bett","BettID",);
            case "Diagnose": db.deleteEntry("Diagnose","DiagnoseID",);
            case "Doktor": db.deleteEntry("Doktor","DoktorID",);
            case "Kontaktdaten": db.deleteEntry("Kontaktdaten","KontaktdatenID",);
            case "Labor": db.deleteEntry("Labor","LaborID",);
            case "Patient": db.deleteEntry("Patient","PatientID",);
            case "Rechnung": db.deleteEntry("Rechnung","RechnungID",);
            case "Raum": db.deleteEntry("Raum","RaumID",);
        }
    }
*/

    public Datenbank getDb(){
        return db;
    }

    public Stage getPopupStage(){
        return popupStage;
    }


    @FXML
    private void onDeleteButtonClick() throws SQLException {
        /*
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        String querry = "";
        if(tab.equals(Abteilung)){
            querry = "DELETE FROM Abteilung WHERE AbteilungsID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Bett)){
            querry = "DELETE FROM Bett WHERE BettID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Diagnose)){
            querry = "DELETE FROM Diagnose WHERE DiagnoseID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Doktor)){
            querry = "DELETE FROM Doktor WHERE DoktorID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Kontaktdaten)){
            querry = "DELETE FROM Kontaktdaten WHERE KontkatdatenID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Labor)){
            querry = "DELETE FROM Labor WHERE LaborID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Patient)){
            querry = "DELETE FROM Patient WHERE PatientID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Raum)){
            querry = "DELETE FROM Raum WHERE RaumID = "++";"; //TODO: ID des ausgewählten Elements
        } else if(tab.equals(Rechnung)){
            querry = "DELETE FROM Rechnung WHERE RechnungsID = "++";"; //TODO: ID des ausgewählten Elements
        }
        db.runQuerry(querry);

     */
    }

    @FXML
    private void onAddButtonClick() throws SQLException{
        Tab tab = tabPane.getSelectionModel().getSelectedItem();
        if(tab.equals(Abteilung)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addAbteilung.fxml"));
            loader.setControllerFactory(addAbteilungController -> new addAbteilungController(this));
            openPopup(loader);
        } else if(tab.equals(Bett)){

            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addBett.fxml"));
            loader.setControllerFactory(addBettController -> new addBettController(this));
            openPopup(loader);
        } else if(tab.equals(Diagnose)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addDiagnose.fxml"));
            loader.setControllerFactory(addDiagnoseController -> new addDiagnoseController(this));
            openPopup(loader);
        } else if(tab.equals(Doktor)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addDoktor.fxml"));
            loader.setControllerFactory(addDoktorController -> new addDoktorController(this));
            openPopup(loader);
        } else if(tab.equals(Kontaktdaten)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addKontaktdaten.fxml"));
            loader.setControllerFactory(addKontaktdatenController -> new addKontaktdatenController(this));
            openPopup(loader);
        } else if(tab.equals(Labor)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addLabor.fxml"));
            loader.setControllerFactory(addLaborController -> {
                try {
                    return new addLaborController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        } else if(tab.equals(Patient)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addPatient.fxml"));
            loader.setControllerFactory(addPatientController -> {
                try {
                    return new addPatientController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        } else if(tab.equals(Raum)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRaum.fxml"));
            loader.setControllerFactory(addRaumController -> {
                try {
                    return new addRaumController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        } else if(tab.equals(Rechnung)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRechnung.fxml"));
            loader.setControllerFactory(addRechnungController -> {
                try {
                    return new addRechnungController(this);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
            openPopup(loader);
        }
    }

    private void openPopup(FXMLLoader loader){
        try {
            AnchorPane rootElement = loader.load();

            Stage stage = new Stage();
            Scene scene = new Scene(rootElement);
            popupStage = stage;

            stage.setScene(scene);
            stage.sizeToScene();
            stage.showAndWait();

        } catch(IOException e) {
            e.printStackTrace();
        };
    }

    @FXML
    private void onReloadButtonClick(){

    }

    @FXML
    private void onEditButtonClick(){

    }

    @FXML
    private void onExportButtonClick(){

    }

    private void FillTableWithContent(TableView view, ResultSet result) throws SQLException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        while(result.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i = 1; i<= result.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(result.getString(i));
            }
            data.add(row);
        }
        view.setItems(data);
    }

    private void GenerateTableHead(TableView view, ResultSet result) throws SQLException {
        //get columns
        //Stackoverflow: https://stackoverflow.com/questions/18941093/how-to-fill-up-a-tableview-with-database-data
        for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn<>(result.getMetaData().getColumnName(i + 1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            view.getColumns().addAll(col);
        }
    }

    private void ClearTable(TableView table)
    {
        int lastelementindex = table.getColumns().size()-1;
        table.getColumns().remove(0,lastelementindex);

        lastelementindex = table.getItems().size()-1;
        table.getItems().remove(0,lastelementindex);
    }
}
