package Kiss;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.*;


public class MainApp extends Application {
    public static void main(String[] args) throws Exception {
        launch(args);
    }

    private static void ConnectToDB() {
        String connectionUrl =
                "jdbc:sqlserver://68.183.223.224;"
                        + "database=KIS;"
                        + "user=SA;"
                        + "password=DbServer2!;"
                        + "encrypt=false;"
                        + "trustServerCertificate=false;"
                        + "loginTimeout=30;";


        try{
            Connection con = DriverManager.getConnection(connectionUrl);
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM abteilung");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../view/main.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }
}
