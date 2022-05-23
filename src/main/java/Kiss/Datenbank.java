package Kiss;

import java.sql.*;

public class Datenbank {
    Connection connection;

    public Datenbank(Connection connection){
        this.connection = connection;
    }

    public void deleteEntry(String table, String tableID, String EntryID) throws SQLException {
        String deleteString = "DELETE FROM " +table+ " WHERE " +tableID+ "='" +EntryID+ "';";
        Statement statement = connection.createStatement();
        statement.execute(deleteString);
    }

    public void runQuerry(String querry) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute(querry);
    }

    public void createEntry(String table,String[] keys, String[] values) throws SQLException {
        String createString = "INSERT INTO " +table+ " (";
        for(int i =0; i< keys.length; i++){
            if(i< keys.length-1) {
                createString = createString + keys[i] + ", ";
            } else {
                createString = createString + keys[i] + ") ";
            }
        }
        createString = createString + "VALUES (";
        for(int i =0; i< values.length; i++){
            if(i< values.length-1) {
                createString = createString+"'" + values[i] + "', ";
            } else {
                createString = createString +"'"+ values[i] + "');";
            }
        }
        //System.out.println(createString);
        Statement statement = connection.createStatement();
        statement.execute(createString);
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