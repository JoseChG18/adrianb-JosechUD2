package com.example.proyectosw.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.StageStyle;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Button search;
    @FXML
    private ComboBox<String> cbElection;
    @FXML
    private TextField name;
    @FXML
    private TableView searchTable;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadData();
    }

    /**
     * Metodo que hace la carga del Combo box de Status.
     */
    private void loadData() {
        this.cbElection.getItems().addAll("People", "Planets", "Films", "Species", "Vehicles", "Starships");
    }

    /**
     * Obtener que item está seleccionado del Combo Box
     *
     * @return String
     */
    public String getComboBox() {
        return this.cbElection.getSelectionModel().getSelectedItem();
    }

    /**
     * Obtener el nombre que se busca en la API.
     *
     * @return
     */
    public String getName() {
        return name.getText();
    }

    /**
     * Evento para buscar dandole al Enter.
     *
     * @param keyEvent
     */
    public void pressEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            accionConsultar(new ActionEvent());
        }
    }

    /**
     * Evento que hace la Llamada a la API.
     *
     * @param actionEvent
     */
    public void accionConsultar(ActionEvent actionEvent) {
        try {
            String option = getComboBox();

            if (option != null) {
                switch (option) {
                    case "People":
                        CharacterController cc = new CharacterController();
                        cc.showCharacter(getName());
                        cc.fillTable(searchTable);
                        break;
                    case "Planets":
                        PlanetController pc = new PlanetController();
                        pc.showPlanets(getName());
                        pc.fillTable(searchTable);
                        break;
                    case "Films":
                        FilmController fc = new FilmController();
                        fc.showFilms(getName());
                        fc.fillTable(searchTable);
                        break;
                    case "Species":
                        SpeciesController sc = new SpeciesController();
                        sc.showSpecies(getName());
                        sc.fillTable(searchTable);
                        break;
                    case "Vehicles":
                        VehiclesController vc = new VehiclesController();
                        vc.showVehicles(getName());
                        vc.fillTable(searchTable);
                        break;
                    case "Starships":
                        StarshipController ssc = new StarshipController();
                        ssc.showStarships(getName());
                        ssc.fillTable(searchTable);
                        break;
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Status");
                alert.setContentText("Select one status.");
                alert.showAndWait();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Evento que se ejecuta para guardar los resultados en ficheros JSON.
     *
     * @param actionEvent
     */
    public void saveFiles(ActionEvent actionEvent) {
        boolean filesSaved = false;
        String archivo = "";
        TextInputDialog dlgArchivo = new TextInputDialog();
        dlgArchivo.setTitle("Nombre del archivo");
        dlgArchivo.setHeaderText("Indica el nombre del archivo");
        dlgArchivo.initStyle(StageStyle.UTILITY);
        Optional<String> respuesta = dlgArchivo.showAndWait();
        if (!respuesta.equals(Optional.empty())) {
            archivo = respuesta.get();
        }
        try {
            if (!archivo.equals("")) {
                String option = this.cbElection.getSelectionModel().getSelectedItem();
                switch (option) {
                    case "People":
                        CharacterController cc = new CharacterController();
                        cc.showCharacter(getName());
                        cc.saveJson(archivo);
                        filesSaved = true;
                        break;
                    case "Planets":
                        PlanetController pc = new PlanetController();
                        pc.showPlanets(name.getText());
                        pc.saveJson(archivo);
                        filesSaved = true;
                        break;
                    case "Films":
                        FilmController fc = new FilmController();
                        fc.showFilms(name.getText());
                        fc.saveJson(archivo);
                        filesSaved = true;
                        break;
                    case "Species":
                        SpeciesController sc = new SpeciesController();
                        sc.showSpecies(name.getText());
                        sc.saveJson(archivo);
                        filesSaved = true;
                        break;

                    case "Vehicles":
                        VehiclesController vc = new VehiclesController();
                        vc.showVehicles(name.getText());
                        vc.saveJson(archivo);
                        filesSaved = true;
                        break;
                    case "Starships":
                        StarshipController ssc = new StarshipController();
                        ssc.showStarships(name.getText());
                        ssc.saveJson(archivo);
                        filesSaved = true;
                        break;
                }
                if (filesSaved) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Save");
                    alert.setContentText("Los archivos se han guardado.");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Save");
                    alert.setContentText("Error guardando archivos.");
                    alert.showAndWait();
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /***
     *
     * @param actionEvent
     */
    public void buscarDelete(ActionEvent actionEvent){
        try{
            String option = getComboBox();

            if (option != null) {
                switch (option){
                    case "People":
                        CharacterController c = new CharacterController();
                        c.showCharacter("");
                        c.fillTable(searchTable);
                        c.creacionDelete(searchTable);
                        break;
                    case "Planets":
                        PlanetController pc = new PlanetController();
                        pc.showPlanets("");
                        pc.fillTable(searchTable);
                        pc.creacionDelete(searchTable);
                        break;
                    case "Films":
                        FilmController fc = new FilmController();
                        fc.showFilms("");
                        fc.fillTable(searchTable);
                        fc.creacionDelete(searchTable);
                        break;
                    case "Species":
                        SpeciesController sc = new SpeciesController();
                        sc.showSpecies("");
                        sc.fillTable(searchTable);
                        sc.creacionDelete(searchTable);
                        break;

                    case "Vehicles":
                        VehiclesController vc = new VehiclesController();
                        vc.showVehicles("");
                        vc.fillTable(searchTable);
                        vc.creacionDelete(searchTable);
                        break;
                    case "Starships":
                        StarshipController ssc = new StarshipController();
                        ssc.showStarships("");
                        ssc.fillTable(searchTable);
                        ssc.creacionDelete(searchTable);
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /***
     *
     * @param actionEvent
     */
    public void buscarInsert(ActionEvent actionEvent){
        try{
            String option = getComboBox();

            if (option != null) {
                switch (option){
                    case "People":
                        CharacterController c = new CharacterController();
                        c.showCharacter("");
                        c.creacionInsert(anchorPane,searchTable);
                        c.fillTable(searchTable);
                        break;
                    case "Planets":
                        PlanetController pc = new PlanetController();
                        pc.showPlanets("");
                        pc.creacionInsert(anchorPane,searchTable);
                        pc.fillTable(searchTable);
                        break;
                    case "Films":
                        FilmController fc = new FilmController();
                        fc.showFilms("");
                        fc.creacionInsert(anchorPane,searchTable);
                        fc.fillTable(searchTable);
                        break;
                    case "Species":
                        SpeciesController sc = new SpeciesController();
                        sc.showSpecies("");
                        sc.creacionInsert(anchorPane,searchTable);
                        sc.fillTable(searchTable);
                        break;

                    case "Vehicles":
                        VehiclesController vc = new VehiclesController();
                        vc.showVehicles("");
                        vc.creacionInsert(anchorPane,searchTable);
                        vc.fillTable(searchTable);
                        break;
                    case "Starships":
                        StarshipController ssc = new StarshipController();
                        ssc.showStarships("");
                        ssc.creacionInsert(anchorPane,searchTable);
                        ssc.fillTable(searchTable);
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /*public void buscarUpdate(ActionEvent actionEvent){
        try{
            String option = getComboBox();

            if (option != null) {
                switch (option){
                    case "People":
                        CharacterController c = new CharacterController();
                        c.showCharacter("");
                        c.fillTable(searchTable);
                        break;
                    case "Planets":
                        PlanetController pc = new PlanetController();
                        pc.showPlanets("");
                        pc.fillTable(searchTable);
                        break;
                    case "Films":
                        FilmController fc = new FilmController();
                        fc.showFilms("");
                        fc.fillTable(searchTable,"update");
                        break;
                    case "Species":
                        SpeciesController sc = new SpeciesController();
                        sc.showSpecies("");
                        sc.fillTable(searchTable,"update");
                        break;

                    case "Vehicles":
                        VehiclesController vc = new VehiclesController();
                        vc.showVehicles("");
                        vc.fillTable(searchTable,"update");
                        break;
                    case "Starships":
                        StarshipController ssc = new StarshipController();
                        ssc.showStarships("");
                        ssc.fillTable(searchTable,"update");
                        break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }*/

}
