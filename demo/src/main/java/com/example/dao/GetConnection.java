package com.example.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class GetConnection {
    static Connection conn = null;

    public static void getConnection() {
        try {
            String dbURL = "jdbc:sqlserver://127.0.0.1:1116;databaseName=WINDSCADA;";
            String user = "SA";
            String pass = "sS123456";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, user, pass);
            DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void infiny(Timestamp timeStamp) throws Exception {
        Random ran = new Random();
        double power = 1800;
        double wind = 10 ;
        double genSpd = 1700 ;
        double rotorSpd = 13 ;
        double tempAmbMin = 30 - 22;
        double tempAmbMax = 22;
        double reactMin = 115-(-100);
        double reactMax = -100;
        double tempNacMin = 42 - 27;
        double tempNacMax = 27;
        double nacPosMin = 330 - 80;
        double nacPosMax = 80;
        String cap = ran.nextDouble() * 40 + 10 + "";
        String avai = ran.nextDouble() * 19 + 80 + "";
        String SQL = "EXEC insert_data_site_log ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
        System.out.println(power + " " +  wind + " " + cap + " " + avai + " " + timeStamp);
        PreparedStatement ps = conn.prepareStatement(SQL);
        ps.setEscapeProcessing(true);
        ps.setQueryTimeout(60000);
        ps.setDouble(1, power);
        ps.setDouble(2, wind);
        ps.setDouble(3, genSpd);
        ps.setDouble(4, rotorSpd);
        ps.setDouble(5, tempAmbMin);
        ps.setDouble(6, tempAmbMax);
        ps.setDouble(7, reactMin);
        ps.setDouble(8, reactMax);
        ps.setDouble(9, tempNacMin);
        ps.setDouble(10, tempNacMax);
        ps.setDouble(11, nacPosMin);
        ps.setDouble(12, nacPosMax);
        ps.setString(13, cap);
        ps.setString(14, avai);
        ps.setTimestamp(15, timeStamp);
        ps.execute();
    }
}
