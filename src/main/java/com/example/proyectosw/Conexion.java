package com.example.proyectosw;
import java.sql.*;
public class Conexion{
    private String URL = "jdbc:mysql://localhost:3306/SW";
    private String user = "root";
    private String pass = "root";
    public Connection c = null;

    public void openConnection(){

        try {
            c = DriverManager.getConnection(URL,user,pass);
        }catch(SQLException ex){
            throw new RuntimeException(ex);
        }
    }

    public void closeConnection(){
       try{
           c.close();
       }catch(SQLException ex){
           throw new RuntimeException(ex);
       }
    }
}
