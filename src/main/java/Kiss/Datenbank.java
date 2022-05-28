package Kiss;

import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

public class Datenbank {
    Connection connection;

    public Datenbank(Connection connection){
        this.connection = connection;
    }

    public ResultSet runQuerry(String querry) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            statement.execute(querry);
            return statement.getResultSet();
        } catch(SQLException exception){
            throw exception;
        }
    }

    public ArrayList<String> returnForeigKeys(String querry, String column) throws SQLException {
        ArrayList<String> foreignKeys = new ArrayList<String>();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(querry);
        while(rs.next()){
            foreignKeys.add(rs.getString(column));
        }
        if(foreignKeys.isEmpty()){
            foreignKeys.add("Hier ist nichts!");
        }
        return foreignKeys;
    }

    public String returnID(String querry, String column) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(querry);
        rs.next();
        return rs.getString(column);
    }
    public Connection getConnection() {
        return connection;
    }

    /*
        public void editEntry(String id, String table, String specificID, String column, String entryToChange){
        String editString = "UPDATE "+table+"\n" +
                "SET "+column+" = '"+entryToChange+"'\n" +
                "WHERE "+specificID+" = "+id+";";
    }

    public void createEntryAbteilung(int id, String Abteilungsname) throws SQLException {
        String createString = "INSERT INTO Abteilung(Abteilungsnummer, Abteilungsname)\n" +
                "Values ("+id+",'"+Abteilungsname+"');";
        Statement statement = connection.createStatement();
        statement.execute(createString);
    }

    public void createEntryBeds(){

    }

    public void createEntryDiagnose(){

    }

    public void createEntryDoctor(){

    }

    public void createEntryKontaktdaten(){

    }

    public void createEntryLabor(){


    }

    /*
        public void editEntry(String id, String table, String specificID, String column, String entryToChange){
        String editString = "UPDATE "+table+"\n" +
                "SET "+column+" = '"+entryToChange+"'\n" +
                "WHERE "+specificID+" = "+id+";";
    }

    public void createEntryAbteilung(int id, String Abteilungsname) throws SQLException {
        String createString = "INSERT INTO Abteilung(Abteilungsnummer, Abteilungsname)\n" +
                "Values ("+id+",'"+Abteilungsname+"');";
        Statement statement = connection.createStatement();
        statement.execute(createString);
    }

    public void createEntryBeds(){

    }

    public void createEntryDiagnose(){

    }

    public void createEntryDoctor(){

    }

    public void createEntryKontaktdaten(){

    }

    public void createEntryLabor(){

    }

    public void createEntryPatients(){

    }

    public void createEntryRechnung(){

    }

    public void createEntryRoom(){

    }
    */
}