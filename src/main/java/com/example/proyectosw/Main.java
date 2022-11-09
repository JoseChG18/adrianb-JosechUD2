package com.example.proyectosw;

import com.example.proyectosw.Controller.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.nio.file.LinkOption;
import java.sql.*;

public class Main extends Application {
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    @FXML
    private Button nuevoUser;
    @FXML
    private Button cambioPass;
    @FXML
    private Label cabecera;
    @FXML
    private Label lblPass;
    static Stage loginStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage loginStage) throws Exception {
        this.loginStage = loginStage;
        try {
           loginVent(loginStage);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Evento para iniciar sesion dandole al Enter
     * @param keyEvent
     */
    public void pressEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            btnEvent(new ActionEvent());
        }
    }

    /**
     * Evento para hacer Login
     * @param actionEvent
     */
    public void btnEvent(ActionEvent actionEvent) {
        try {
            String URL = "jdbc:mysql://localhost:3306/SWLogin";
            String userBD = "root";
            String passBD = "root";
            Connection c = null;
            try {
                c = DriverManager.getConnection(URL, userBD, passBD);
            } catch (Exception e){
                throw new RuntimeException("No se pudo establecer la conexión");
            }
            String textUser = "";
            String textPass = "";
            String pass = "";
            switch (cabecera.getText()){
                case "INICIO SESIÓN":
                    try {
                        textUser = user.getText();
                        textPass = password.getText();
                        /*textUser = "admin";
                        textPass = "renaido";*/
                        Statement st = c.createStatement();
                        ResultSet rs = st.executeQuery("select usuario,PASS from USUARIOS where usuario = \'"+ textUser+"\'");
                        while (rs.next()) {
                            pass = rs.getString("Pass");
                        }
                        if (pass.equals("")){
                            throw new RuntimeException("Usuario o contraseña incorrecta.");
                        }
                        st.close();
                        rs.close();
                    }catch(SQLException ex){
                        throw new RuntimeException(ex);
                    }
                    if (BCrypt.checkpw(textPass, pass)) {
                        mainVent(loginStage);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Login");
                        alert.setContentText("Error: Usuario o contraseña incorrecta.");
                        alert.showAndWait();
                    }
                    break;
                case "REGISTRAR":
                    try {
                        textUser = user.getText();
                        if (textUser.length() > 50){
                            throw new RuntimeException("Nombre de usuario demasiado largo");
                        }
                        textPass = password.getText();
                        PreparedStatement ps = c.prepareStatement("insert into USUARIOS (usuario, pass) values (?,?)");
                        ps.setString(1,textUser);
                        String pass1 = BCrypt.hashpw(textPass,BCrypt.gensalt());
                        if (pass1.length() > 255){
                            throw new RuntimeException("Contraseña demasiado larga");
                        }
                        ps.setString(2,pass1);
                        ps.executeUpdate();
                        loginVent(loginStage);
                    }catch(SQLException ex){
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Ya existe ese usuario");
                    }
                    break;
                case "CAMBIAR CONTRASEÑA":
                    try {
                        String query;
                        textUser = user.getText();
                        textPass = BCrypt.hashpw(textPass,BCrypt.gensalt());
                        Statement st = c.createStatement();
                        ResultSet rs = st.executeQuery("select * from USUARIOS where usuario = \'"+ textUser+"\'");
                        if (rs.getFetchSize() < 1){
                            throw new RuntimeException("Error en el cambio de contraseña");
                        }
                        if(textPass.length()>255){
                            throw new RuntimeException("Nueva contraseña demasiado larga");
                        }
                        query = "Update usuarios\n" +
                                "set pass = \'"+textPass+"\'\n" +
                                "where usuario = \'"+textUser+"\'";
                        st.executeUpdate(query);
                        st.close();
                        loginVent(loginStage);
                    }catch(SQLException ex){
                        throw new RuntimeException("Error en el cambio de contraseña");
                    }
                    break;
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error: " + e.getMessage());
            alert.showAndWait();
        }

    }


    public void newUser(ActionEvent actionEvent) {
        if (cabecera.getText().equals("INICIO SESIÓN")) {
            adduserVent(loginStage);
        } else{
            loginVent(loginStage);
        }

    }
    public void changePass(ActionEvent actionEvent) {
        if (cabecera.getText().equals("INICIO SESIÓN")) {
            changePassVent(loginStage);
        } else{
            loginVent(loginStage);
        }

    }
    public void mainVent(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("mainview.fxml"));
            primaryStage.setTitle("Star Wars Characters");
            primaryStage.setScene(new Scene(root, 640, 500));
            primaryStage.setResizable(false);
            primaryStage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al abrir Ventana principal: " + e.getMessage());
            alert.showAndWait();
        }

    }
    public void adduserVent(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("adduserview.fxml"));
            primaryStage.setTitle("Star Wars Characters");
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al abrir ventana de creacion de usuarios: " + e.getMessage());
            alert.showAndWait();
        }

    }
    public void changePassVent(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("changepassview.fxml"));
            primaryStage.setTitle("Star Wars Characters");
            primaryStage.setScene(new Scene(root, 300, 250));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al abrir ventana de creacion de usuarios: " + e.getMessage());
            alert.showAndWait();
        }

    }
    public void loginVent(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
            loginStage.setTitle("Star Wars Characters");
            loginStage.setScene(new Scene(root,300 , 250));
            loginStage.setResizable(false);
            loginStage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }


}