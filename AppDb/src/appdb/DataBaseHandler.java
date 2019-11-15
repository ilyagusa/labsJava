/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataBaseHandler extends Conf {

    Connection dbConnection;

    private ObservableList<Human> data
            = FXCollections.observableArrayList();
    private ObservableList<Phone> dataPh
            = FXCollections.observableArrayList();

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?" + "autoReconnect=true&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    public void insertHuman(String name, String surname, String address, String email, String category) throws ClassNotFoundException {
        try {
            String insert = "INSERT INTO " + Const.HumanTable + "(" + Const.NameCol + "," + Const.SurnameCol + "," + Const.AddressCol + "," + Const.EmailCol
                    + "," + Const.CategoryCol + ")" + "VALUES( ?,?,?,?,? )";

            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, name);
            prSt.setString(2, surname);
            prSt.setString(3, address);
            prSt.setString(4, email);
            prSt.setString(5, category);

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<Human> selectHuman(String paramFind, String paramColumn, String paramCat) throws ClassNotFoundException, SQLException {
        String setTr = "email";
        if (paramFind == "Имя") {
            setTr = Const.NameCol;
        } else if (paramFind == "Фамилия") {
            setTr = Const.SurnameCol;
        } else if (paramFind == "Город") {
            setTr = Const.AddressCol;
        }
        String select;
        try {
            if (paramCat != "Все") {
                select = "SELECT " + Const.IdHuman + "," + Const.NameCol + "," + Const.SurnameCol + ","
                        + Const.AddressCol + "," + Const.EmailCol + "," + Const.CategoryCol + " FROM " + Const.HumanTable
                        + " WHERE " + setTr + " = '" + paramColumn + "' AND " + Const.CategoryCol + " = '" + paramCat + "'";
            } else {
                select = "SELECT " + Const.IdHuman + "," + Const.NameCol + "," + Const.SurnameCol + ","
                    + Const.AddressCol + "," + Const.EmailCol + "," + Const.CategoryCol + " FROM " + Const.HumanTable
                    + " WHERE " + setTr + " = '" + paramColumn + "'";
            }
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                data.add(new Human(resultSet.getInt(Const.IdHuman), resultSet.getString(Const.NameCol), resultSet.getString(Const.SurnameCol), resultSet.getString(Const.AddressCol),
                        resultSet.getString(Const.EmailCol), resultSet.getString(Const.CategoryCol)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public ObservableList<Human> selectHuman(String num) throws ClassNotFoundException, SQLException {
        try {
            String select = "SELECT " + Const.IdHuman + "," + Const.NameCol + "," + Const.SurnameCol + ","
                    + Const.AddressCol + "," + Const.EmailCol + "," + Const.CategoryCol + " FROM " + Const.HumanTable
                    + " NATURAL JOIN " + Const.PhoneTable + " WHERE " + Const.Number + " = " + num;
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                data.add(new Human(resultSet.getInt(Const.IdHuman), resultSet.getString(Const.NameCol), resultSet.getString(Const.SurnameCol), resultSet.getString(Const.AddressCol),
                        resultSet.getString(Const.EmailCol), resultSet.getString(Const.CategoryCol)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public ObservableList<Human> selectHuman() throws ClassNotFoundException, SQLException {
        try {
            String select = "SELECT " + Const.IdHuman + "," + Const.NameCol + "," + Const.SurnameCol + ","
                    + Const.AddressCol + "," + Const.EmailCol + "," + Const.CategoryCol + " FROM " + Const.HumanTable;
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                data.add(new Human(resultSet.getInt(Const.IdHuman), resultSet.getString(Const.NameCol), resultSet.getString(Const.SurnameCol), resultSet.getString(Const.AddressCol),
                        resultSet.getString(Const.EmailCol), resultSet.getString(Const.CategoryCol)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    public ObservableList<Phone> selectPhone(int id) throws ClassNotFoundException, SQLException {
        try {
            String select = "SELECT " + Const.IdHuman + "," + Const.NameCol + "," + Const.SurnameCol + ","
                    + Const.AddressCol + "," + Const.TypeNumber + "," + Const.Number + " FROM " + Const.HumanTable + " NATURAL JOIN " + Const.PhoneTable
                    + " WHERE " + Const.PhoneTable + "." + Const.IdPhoneNumber + " = " + id;
            System.out.println(select);
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resultSet = prSt.executeQuery();
            while (resultSet.next()) {
                dataPh.add(new Phone(resultSet.getInt(Const.IdPhoneNumber), resultSet.getString(Const.TypeNumber), resultSet.getString(Const.Number)));
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DataBaseHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataPh;
    }

}
