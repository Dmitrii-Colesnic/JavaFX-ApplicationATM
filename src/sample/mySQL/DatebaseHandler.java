package sample.mySQL;

import sample.User;

import java.sql.*;

import static sample.mySQL.Const.*;

public class DatebaseHandler extends  Config{
    Connection dbConnection;

    public Connection getDbConnection() throws SQLException, ClassNotFoundException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

    public void signUpUser (User user) {

        String insert = "INSERT INTO " + USER_TABLE + "(" + USERS_FIRSTNAME + "," +
                USERS_LASTNAME + "," + USERS_CARDNUMBER + "," + USERS_PASSWORD + "," +
                USERS_MONEY + ")" + "VALUES(?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getFirstname());
            prSt.setString(2, user.getLastname());
            prSt.setString(3, user.getCardnumber());
            prSt.setString(4, user.getPassword());
            prSt.setString(5, user.getMoney());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user, String with_password) {

        ResultSet resSet = null;

        String select;

        if (with_password.equals("yes")) {
            select = "SELECT * FROM " + USER_TABLE + " WHERE " +
                    USERS_CARDNUMBER + "=? AND " + USERS_PASSWORD + "=?";
        } else {
            select = "SELECT * FROM " + USER_TABLE + " WHERE " +
                    USERS_CARDNUMBER + "=?";
        }

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getCardnumber());

            if (with_password.equals("yes"))
                prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        
        return resSet;
    }

    public void UpDateUser(User user) {

        String UpDate = "UPDATE " + USER_TABLE + " SET " + USERS_MONEY + "=?" +
                                            " WHERE " + USERS_CARDNUMBER + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(UpDate);
            prSt.setString(1, user.getMoney());
            prSt.setString(2, user.getCardnumber());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}