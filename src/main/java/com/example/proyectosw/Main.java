package com.example.proyectosw;

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

public class Main extends Application {
    @FXML
    private TextField user;
    @FXML
    private PasswordField password;
    @FXML
    private Button btnLogin;
    static Stage loginStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage loginStage) throws Exception {
        this.loginStage = loginStage;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
            loginStage.setTitle("Star Wars Characters");
            loginStage.setScene(new Scene(root,300 , 200));
            loginStage.setResizable(false);
            loginStage.show();
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
            login(new ActionEvent());
        }
    }

    /**
     * Evento para hacer Login
     * @param actionEvent
     */
    public void login(ActionEvent actionEvent) {
        try {
            String textUser = user.getText();
            String textPass = password.getText();
            textUser = "admin";
            textPass = "renaido";
            AppProperties ap = null;
            String user = ap.getProp(AppProperties.USUARIO);
            String pass = ap.getProp(AppProperties.CONTRASEÑA);
            // Sistema de Encriptado -> BCrypt.hashpw(textPass, BCrypt.gensalt(10))
            //System.out.println(BCrypt.checkpw(textPass, pass));
            if (textUser.equals(user) && BCrypt.checkpw(textPass, pass)) {
                optionsVent(loginStage);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Login");
                alert.setContentText("Usuario o contraseña incorrecta.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al iniciar sesion:" + e.getMessage());
            alert.showAndWait();
        }

    }

    public void optionsVent(Stage optionStage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("optionsview.fxml"));
            optionStage.setTitle("Escoge una opción");
            optionStage.setScene(new Scene(root, 600, 400));
            optionStage.setResizable(false);
            optionStage.show();
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al abrir la ventana de opciones: " + e.getMessage());
            alert.showAndWait();
        }

    }

    public void insertBD(ActionEvent actionEvent) {
        insertView(loginStage);
    }

    public void modifyBD(ActionEvent actionEvent) {
        modifyView(loginStage);
    }

    public void selectBD(ActionEvent actionEvent) {
        mainVent(loginStage);
    }

    public void deleteBD(ActionEvent actionEvent) {
        deleteView(loginStage);
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

    public void insertView(Stage insertStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("insertview.fxml"));
            insertStage.setTitle("Menu de Insertar");
            insertStage.setScene(new Scene(root, 600, 400));
            insertStage.setResizable(false);
            insertStage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al abrir Ventana de Insertar: " + e.getMessage());
            alert.showAndWait();
        }
    }
    public void modifyView(Stage modifyStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("modifyview.fxml"));
            modifyStage.setTitle("Menu de modificacion");
            modifyStage.setScene(new Scene(root, 600, 400));
            modifyStage.setResizable(false);
            modifyStage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al abrir Ventana de modificacion: " + e.getMessage());
            alert.showAndWait();
        }
    }
    public void deleteView(Stage deleteStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("deleteview.fxml"));
            deleteStage.setTitle("Menu de Eliminar");
            deleteStage.setScene(new Scene(root, 600, 400));
            deleteStage.setResizable(false);
            deleteStage.show();

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login");
            alert.setContentText("Error al abrir Ventana de Eliminación: " + e.getMessage());
            alert.showAndWait();
        }
    }

}