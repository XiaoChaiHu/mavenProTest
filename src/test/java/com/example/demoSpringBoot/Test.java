package com.example.demoSpringBoot;

import java.sql.*;

public class Test {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql?characterEncoding=utf8&useSSL=true&useUnicode=true",
                    "root", "wb683341");
            Statement stmt = connect.createStatement();
            ResultSet rs = stmt.executeQuery("select * from user");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
