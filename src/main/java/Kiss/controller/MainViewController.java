package Kiss.controller;

import Kiss.Datenbank;
import Kiss.controller.add.*;
import Kiss.controller.edit.editAbteilungController;
import Kiss.controller.edit.editBettController;
import Kiss.controller.edit.editDiagnoseController;
import Kiss.controller.edit.editDoktorController;
import Kiss.model.XmlExporterService;
import Kiss.model.XmlExporterServiceImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    @FXML
    MenuItem exportMenuButton;

    private Stage popupStage;



    private Datenbank db;
    @FXML
    public void initialize(){
        Abteilung.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(AbteilungTable);
            UpdateTableView("Abteilung");
            SetButtonsToTable(AbteilungTable,"Abteilung","AbteilungsID");
        });

        Bett.setOnSelectionChanged(selectionChanged->
        {
            ClearTable(BettTable);
            ResultSet resul;
            try {
                resul=db.runQuerry("Select * from Bett;");
                GenerateTableHead(BettTable,resul);
                FillTableWithContent(BettTable,resul);
                SetButtonsToTable(BettTable,"Bett","BettID");
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
                SetButtonsToTable(DiagnoseTable,"Diagnose","DiagnoseID");
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
                SetButtonsToTable(DoktorTable,"Doktor","DoktorID");
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
                SetButtonsToTable(KontaktdatenTable,"Kontaktdaten","KontaktdatenID");
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
                SetButtonsToTable(LaborTable,"Labor","LaborID");
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
                SetButtonsToTable(RechnungTable,"Rechnung","RechnungsID");
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
                SetButtonsToTable(PatientTable,"Patient","PatientenID");
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
                SetButtonsToTable(RaumTable,"Raum","RaumID");
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


    @FXML
    private void onExportXML() {
        XmlExporterService xmlExporterService = new XmlExporterServiceImpl();

        xmlExporterService.createXMLFileFromDatabase(db.getConnection(), "Abteilung");
    }

    public Datenbank getDb(){
        return db;
    }

    public Stage getPopupStage(){
        return popupStage;
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
            loader.setControllerFactory(addLaborController -> new addLaborController(this));
            openPopup(loader);
        } else if(tab.equals(Patient)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addPatient.fxml"));
            loader.setControllerFactory(addPatientController -> new addPatientController(this));
            openPopup(loader);
        } else if(tab.equals(Raum)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRaum.fxml"));
            loader.setControllerFactory(addRaumController -> new addRaumController(this));
            openPopup(loader);
        } else if(tab.equals(Rechnung)){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRechnung.fxml"));
            loader.setControllerFactory(addRechnungController -> new addRechnungController(this));
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
        }
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
/**
 * generates a new column in the given table with delete buttons
 * @param tableView the tableview where the generated column should go
 * @param tableName the sql table name
 * @param primaryKey name of the pk in the table*/
    private void SetButtonsToTable(TableView tableView, String tableName, String primaryKey)
    {
        TableColumn<ObservableList,String> deleteButtonColumn = new TableColumn<>("Delete");
        TableColumn<ObservableList,String> editButtonColumn = new TableColumn<>("Edit");

        Callback<TableColumn<ObservableList, String>, TableCell<ObservableList, String>> deleteCellFactory = generateDeleteCellFactory(tableName,primaryKey);
        deleteButtonColumn.setCellFactory(deleteCellFactory);

        Callback<TableColumn<ObservableList,String>,TableCell<ObservableList,String>> editCellFactory = generateEditCellFactory();
        editButtonColumn.setCellFactory(editCellFactory);

        tableView.getColumns().add(editButtonColumn);
        tableView.getColumns().add(deleteButtonColumn);
    }

    /**
     * Generates a CellFactory with the delete buttons and their functionality
     */
    private Callback<TableColumn<ObservableList, String>, TableCell<ObservableList, String>> generateDeleteCellFactory(String table, String pk) {
        Callback<TableColumn<ObservableList,String>,TableCell<ObservableList,String>> cellFactory = new Callback<>() {

            @Override
            public TableCell<ObservableList, String> call(TableColumn<ObservableList, String> param) {
                final TableCell<ObservableList, String> cell = new TableCell<ObservableList, String>() {

                    private final Button btn = new Button("Delete");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ObservableList data = getTableView().getItems().get(getIndex());

                            try {
                                db.runQuerry("DELETE FROM "+table+" Where "+pk+"=" + data.get(0) + ";");
                            } catch (SQLException e) {
                                Alert sqlAlert = new Alert(Alert.AlertType.ERROR, e.getMessage(), ButtonType.OK);
                                sqlAlert.showAndWait();
                            }
                        });
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        return cellFactory;
    }

    /**
     * Generates a CellFactory with edit button and injects the data to be changed
     */
    private Callback<TableColumn<ObservableList, String>, TableCell<ObservableList, String>> generateEditCellFactory() {
        MainViewController main = this;

        Callback<TableColumn<ObservableList,String>,TableCell<ObservableList,String>> cellFactory = new Callback<>() {

            @Override
            public TableCell<ObservableList, String> call(TableColumn<ObservableList, String> param) {
                final TableCell<ObservableList, String> cell = new TableCell<ObservableList, String>() {

                    private final Button btn = new Button("Edit");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            ObservableList data = getTableView().getItems().get(getIndex());


                            Tab tab = tabPane.getSelectionModel().getSelectedItem();
                            if(tab.equals(Abteilung)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addAbteilung.fxml"));
                                loader.setControllerFactory(editAbteilungController -> new editAbteilungController((String) data.get(0),(String)data.get(1),(String) data.get(2),(String) data.get(3),main));
                                openPopup(loader);
                            } else if(tab.equals(Bett)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addBett.fxml"));
                                loader.setControllerFactory(editBettController -> new editBettController((String) data.get(0),(String) data.get(1), main));
                                openPopup(loader);
                            } else if(tab.equals(Diagnose)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addDiagnose.fxml"));
                                loader.setControllerFactory(editDiagnoseController -> new editDiagnoseController(
                                        (String)data.get(0),
                                        (String)data.get(1),
                                        (String)data.get(2),
                                        main));
                                openPopup(loader);
                            } else if(tab.equals(Doktor)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addDoktor.fxml"));
                                loader.setControllerFactory(editDoktorController -> new editDoktorController(
                                        (String) data.get(0),
                                        (String) data.get(1),
                                        (String) data.get(2),
                                        (String) data.get(3),
                                        (String )data.get(4),
                                        (String) data.get(5),
                                        (String)data.get(6),
                                        main));
                                openPopup(loader);
                            } else if(tab.equals(Kontaktdaten)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addKontaktdaten.fxml"));
                                //loader.setControllerFactory(addKontaktdatenController -> new addKontaktdatenController(this));
                                openPopup(loader);
                            } else if(tab.equals(Labor)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addLabor.fxml"));
                                //loader.setControllerFactory(addLaborController -> new addLaborController(this));
                                openPopup(loader);
                            } else if(tab.equals(Patient)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addPatient.fxml"));
                                //loader.setControllerFactory(addPatientController -> new addPatientController(this));
                                openPopup(loader);
                            } else if(tab.equals(Raum)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRaum.fxml"));
                                //loader.setControllerFactory(addRaumController -> new addRaumController(this));
                                openPopup(loader);
                            } else if(tab.equals(Rechnung)){
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../../view/add/addRechnung.fxml"));
                                //loader.setControllerFactory(addRechnungController -> new addRechnungController(this));
                                openPopup(loader);
                            }


                        });
                    }

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        return cellFactory;
    }

    /**
     * Removes everything from the Tableview*/
    private void ClearTable(TableView table)
    {
        if(table.getColumns().size()==0){return;}

        int lastelementindex = table.getColumns().size();
        table.getColumns().remove(0,lastelementindex);

        lastelementindex = table.getItems().size();
        table.getItems().remove(0,lastelementindex);
    }
}
