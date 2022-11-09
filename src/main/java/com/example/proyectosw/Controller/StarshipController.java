package com.example.proyectosw.Controller;

import com.example.proyectosw.Conexion;
import com.example.proyectosw.model.*;
import com.example.proyectosw.model.Character;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StarshipController {
    private List<Starship> starships = new ArrayList<>();

    /**
     * Metodo que hace la llamada a la API.
     *
     * @param name
     */
    public void showStarships(String name) {
        try {
            Conexion c = new Conexion();
            c.openConnection();
            if (name.equals("")) {
                Statement stm = c.c.createStatement();
                ResultSet rst = stm.executeQuery("SELECT * FROM STARSHIPS");
                while (rst.next()) {
                    starships.add(new Starship(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("MODEL"),
                            rst.getString("MANUFACTURER"),
                            rst.getString("COSTINCREDITS"),
                            rst.getString("LENGTH"),
                            rst.getString("CARGOCAPACITY"),
                            rst.getString("STARSHIPCLASS"),
                            rst.getString("HIPERDRIVERATING")
                    ));
                }
            } else {
                PreparedStatement pstm = c.c.prepareStatement("SELECT * FROM STARSHIPS WHERE NAME LIKE ?");
                pstm.setString(1, "%" + name + "%");

                ResultSet rst = pstm.executeQuery();

                while (rst.next()) {
                    starships.add(new Starship(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("MODEL"),
                            rst.getString("MANUFACTURER"),
                            rst.getString("COSTINCREDITS"),
                            rst.getString("LENGTH"),
                            rst.getString("CARGOCAPACITY"),
                            rst.getString("STARSHIPCLASS"),
                            rst.getString("HIPERDRIVERATING")
                    ));
                }
            }
            c.closeConnection();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Metodo para llenar la tabla tanto con las columnas y datos.
     *
     * @param searchTable
     */
    public void fillTable(TableView searchTable) {
        if (starships.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Starship, Integer> col_id = new TableColumn<>("id");
            TableColumn<Starship, String> col_Name = new TableColumn<>("Name");
            TableColumn<Starship, String> col_Model = new TableColumn<>("Model");
            TableColumn<Starship, String> col_Manufacturer = new TableColumn<>("Manufacturer");
            TableColumn<Starship, String> col_Cost = new TableColumn<>("Cost in credits");
            TableColumn<Starship, String> col_Length = new TableColumn<>("Length");
            TableColumn<Starship, String> col_CargoCapacity = new TableColumn<>("Cargo Capacity");
            TableColumn<Starship, String> col_StarshipClass = new TableColumn<>("Starship class");
            TableColumn<Starship, String> col_Hyperdrive = new TableColumn<>("Hyperdrive rating");

            searchTable.getColumns().addAll(col_id, col_Name, col_Model, col_Manufacturer, col_Cost, col_Length, col_CargoCapacity, col_StarshipClass, col_Hyperdrive);

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_Model.setCellValueFactory(new PropertyValueFactory<>("model"));
            col_Manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            col_Cost.setCellValueFactory(new PropertyValueFactory<>("costInCredits"));
            col_Length.setCellValueFactory(new PropertyValueFactory<>("length"));
            col_CargoCapacity.setCellValueFactory(new PropertyValueFactory<>("cargoCapacity"));
            col_StarshipClass.setCellValueFactory(new PropertyValueFactory<>("starshipClass"));
            col_Hyperdrive.setCellValueFactory(new PropertyValueFactory<>("hyperdriveRating"));

            searchTable.getItems().clear();
            searchTable.getItems().addAll(starships);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error Al encontrar Starship.");
            alert.showAndWait();
        }
    }

    public void creacionDelete(TableView searchTable) {
        TableColumn<Character, Void> col_buttonDelete = new TableColumn<>("Eliminar");
        searchTable.getColumns().add(col_buttonDelete);
        col_buttonDelete.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        for (Starship ss : starships) {
            ss.getEliminar().setOnAction(actionEvent -> {
                try {
                    Conexion conex = new Conexion();
                    conex.openConnection();
                    Statement stm = conex.c.createStatement();
                    stm.executeUpdate("DELETE FROM STARSHIPS WHERE ID = " + ss.getId());
                    conex.closeConnection();
                    starships.clear();
                    showStarships("");
                    fillTable(searchTable);
                    creacionDelete(searchTable);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
    }

    public void creacionInsert(AnchorPane anchorPane, TableView searchTable) {
        //(NAME, MODEL, MANUFACTURER, COSTINCREDITS, LENGTH, CARGOCAPACITY, STARSHIPCLASS,HIPERDRIVERATING)
        Label lblName = new Label("Name: ");
        TextField txtName = new TextField("");
        txtName.setMaxWidth(130);
        Label lblModel = new Label("Model: ");
        TextField txtModel = new TextField("");
        txtModel.setMaxWidth(130);
        Label lblManu = new Label("Manufacturer: ");
        TextField txtManu = new TextField("");
        txtManu.setMaxWidth(130);
        Label lblCost = new Label("CostInCredits: ");
        TextField txtCost = new TextField("");
        txtCost.setMaxWidth(130);
        Label lblLength = new Label("Length: ");
        TextField txtLength = new TextField("");
        txtLength.setMaxWidth(130);
        Label lblCargo = new Label("CargoCapacity: ");
        TextField txtCargo = new TextField("");
        txtCargo.setMaxWidth(130);
        Label lblClass = new Label("Class: ");
        TextField txtClass = new TextField("");
        txtClass.setMaxWidth(130);
        Label lblHiper = new Label("HiperDriveRating: ");
        TextField txtHiper = new TextField("");
        txtHiper.setMaxWidth(130);
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(actionEvent -> {
            try {
                Conexion c = new Conexion();
                c.openConnection();
                PreparedStatement pstm = c.c.prepareStatement("INSERT INTO STARSHIPS(name, model, manufacturer, costincredits, length, cargocapacity, starshipclass, hiperdriverating) VALUES(?,?,?,?,?,?,?,?)");
                pstm.setString(1, txtName.getText());
                pstm.setString(2, txtModel.getText());
                pstm.setString(3, txtManu.getText());
                pstm.setString(4, txtCost.getText());
                pstm.setString(5, txtLength.getText());
                pstm.setString(6, txtCargo.getText());
                pstm.setString(7, txtClass.getText());
                pstm.setString(8, txtHiper.getText());
                pstm.executeUpdate();
                starships.clear();
                showStarships("");
                fillTable(searchTable);
                txtName.setText("");
                txtModel.setText("");
                txtManu.setText("");
                txtCost.setText("");
                txtLength.setText("");
                txtCargo.setText("");
                txtClass.setText("");
                txtHiper.setText("");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(lblName, txtName, lblModel, txtModel, lblManu, txtManu, lblCost, txtCost, lblLength, txtLength, lblCargo, txtCargo, lblClass, txtClass, lblHiper, txtHiper, btnAgregar);
        // prefHeight="120.0" prefWidth="570.0"
        lblName.setTranslateY(10);
        txtName.setTranslateY(10);
        txtName.setTranslateX(40);

        lblModel.setTranslateY(10);
        txtModel.setTranslateY(10);
        lblModel.setTranslateX(180);
        txtModel.setTranslateX(220);

        lblManu.setTranslateY(10);
        lblManu.setTranslateX(360);
        txtManu.setTranslateX(440);
        txtManu.setTranslateY(10);

        lblCost.setTranslateY(40);
        txtCost.setTranslateY(40);
        txtCost.setTranslateX(80);

        lblLength.setTranslateY(40);
        txtLength.setTranslateY(40);
        lblLength.setTranslateX(220);
        txtLength.setTranslateX(265);

        lblClass.setTranslateY(40);
        txtClass.setTranslateY(40);
        lblClass.setTranslateX(405);
        txtClass.setTranslateX(440);

        lblCargo.setTranslateY(70);
        txtCargo.setTranslateY(70);
        txtCargo.setTranslateX(90);

        lblHiper.setTranslateY(70);
        txtHiper.setTranslateY(70);
        lblHiper.setTranslateX(230);
        txtHiper.setTranslateX(330);

        btnAgregar.setTranslateX(500);
        btnAgregar.setTranslateY(90);
    }

    /**
     * Metodo que hace el guardado en fichero JSON.
     *
     * @param url
     */
    public void saveJson(String url) {
        try {
            File arc = new File(url + ".json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(arc, starships);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que hace la ordenación del resultado de la API.
     *
     * @param lista
     * @return List<Character>
     */
    private static List<Starship> sortList(List<Starship> lista) {
        List<Starship> orderedList = new ArrayList<>();
        ArrayList<String> nombres = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            nombres.add(lista.get(i).getName());
        }
        Collections.sort(nombres);
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (nombres.get(i).equals(lista.get(j).getName())) {
                    orderedList.add(lista.get(j));
                }
            }

        }
        return orderedList;
    }

    /**
     * Metodo que hace el guardado en fichero XML.
     * @param url

    public void saveXml(String url) {
    try {
    File arc = new File(url + ".xml");
    XmlMapper xmlMapper = new XmlMapper();

    xmlMapper.writeValue(arc, Json);

    } catch (Exception e) {
    System.out.println(e.getMessage());
    }
    }*/
    /**
     * Metodo que hace el guardado en fichero Binario.
     * @param url

    public void saveBinario(String url) {
    File arc = new File(url + ".bin");
    try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(arc))) {
    for (Starship ss : Json) {
    escritor.writeObject(ss);
    }
    } catch (IOException ex) {
    System.err.println(ex.getMessage());
    }
    }*/
    /**
     * Metodo que hace el guardado en fichero CSV/TXT.
     * @param url

    public void saveTxt(String url) {
    try {
    JsonNode jsonTree = new ObjectMapper().readTree(new File(url + ".json"));
    CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
    JsonNode firstObject = jsonTree.elements().next();
    firstObject.fieldNames().forEachRemaining(fieldName -> {
    csvSchemaBuilder.addColumn(fieldName);
    });
    CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
    CsvMapper csvMapper = new CsvMapper();
    csvMapper.writerFor(JsonNode.class)
    .with(csvSchema)
    .writeValue(new File(url + ".txt"), jsonTree);
    } catch (IOException e) {
    System.out.println(e.getMessage());
    }
    } */
}
