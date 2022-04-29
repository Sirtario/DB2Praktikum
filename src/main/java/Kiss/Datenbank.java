package Kiss;

import Kiss.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Datenbank {
    private Connection con;
    private List<dtoAbteilung> abteilungen;
    private List<dtoBeds> betten;
    private List<dtoDiagnose> diagnosen;
    private List<dtoDoctor> doctors;
    private List<dtoKontaktdaten> kontaktdaten;
    private List<dtoLabor> labore;
    private List<dtoPatients> patients;
    private List<dtoRechnung> rechnungen;
    private List<dtoRoom> rooms;


    public Datenbank(Connection con){
        this.con = con;
        updateDTOs();
    }

    public Connection getCon() {
        return con;
    }

    public List<dtoAbteilung> getAbteilungen() {
        return abteilungen;
    }

    public List<dtoBeds> getBetten() {
        return betten;
    }

    public List<dtoDiagnose> getDiagnosen() {
        return diagnosen;
    }

    public List<dtoDoctor> getDoctors() {
        return doctors;
    }

    public List<dtoKontaktdaten> getKontaktdaten() {
        return kontaktdaten;
    }

    public List<dtoLabor> getLabore() {
        return labore;
    }

    public List<dtoPatients> getPatients() {
        return patients;
    }

    public List<dtoRechnung> getRechnungen() {
        return rechnungen;
    }

    public List<dtoRoom> getRooms() {
        return rooms;
    }

    public void updateDTOs(){
        updateAbteilung(con);
        updateBeds(con);
        updateDiagnose(con);
        updateDoctor(con);
        updateKontaktdaten(con);
        updateLabor(con);
        updatePatients(con);
        updateRechnung(con);
        updateRoom(con);
    }

    public void updateRoom(Connection con) {
        rooms = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM room");
            while (result.next()){
                rooms.add(new dtoRoom(  result.getInt(0),
                        result.getString(1),
                        result.getBoolean(2),
                        result.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateRechnung(Connection con) {
        rechnungen = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM rechnung");
            while (result.next()){
                rechnungen.add(new dtoRechnung( result.getInt(0),
                        result.getInt(1),
                        result.getInt(2),
                        result.getInt(3),
                        result.getInt(4),
                        result.getInt(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updatePatients(Connection con) {
        patients = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM patients");
            while (result.next()){
                patients.add(new dtoPatients(result.getInt(0),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        result.getBoolean(5),
                        result.getInt(6),
                        result.getInt(7),
                        result.getInt(8),
                        result.getInt(9),
                        result.getInt(10)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateLabor(Connection con) {
        labore = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM labor");
            while (result.next()){
                labore.add(new dtoLabor(result.getInt(0),
                        result.getDate(1),
                        result.getString(2),
                        result.getInt(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateKontaktdaten(Connection con) {
        kontaktdaten = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM kontaktdaten");
            while (result.next()){
                kontaktdaten.add(new dtoKontaktdaten(result.getInt(0),
                        result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDoctor(Connection con) {
        doctors = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM doctor");
            while (result.next()){
                doctors.add(new dtoDoctor(result.getInt(0),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3),
                        result.getDate(4),
                        result.getString(5),
                        result.getInt(6)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateDiagnose(Connection con) {
        diagnosen = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM diagnose");
            while (result.next()){
                diagnosen.add(new dtoDiagnose(result.getInt(0),
                        result.getInt(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBeds(Connection con) {
        betten = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM beds");
            while (result.next()){
                betten.add(new dtoBeds(result.getInt(0),
                        result.getInt(1)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAbteilung(Connection con) {
        abteilungen = new ArrayList<>();
        try{
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM abteilung");
            while (result.next()){
                abteilungen.add(new dtoAbteilung(result.getInt(0),
                        result.getString(1),
                        result.getString(2),
                        result.getString(3)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
