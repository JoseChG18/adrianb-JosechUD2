package com.example.proyectosw.Controller;

import com.example.proyectosw.Conexion;
import com.example.proyectosw.model.Character;
import com.example.proyectosw.model.Planet;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.*;
import java.sql.*;
import java.util.*;

public class PlanetController {
    private List<Planet> planets = new ArrayList<>();
    /**
     * Metodo que hace la llamada a la base de datos.
     * @param name
     */

    public void showPlanets(String name){
        try {
            Conexion c = new Conexion();
            c.openConnection();
            if (name.equals("")) {
                Statement stm = c.c.createStatement();
                ResultSet rst = stm.executeQuery("SELECT * FROM PLANETS");
                while (rst.next()) {
                    planets.add(new Planet(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("TERRAIN"),
                            rst.getString("GRAVITY"),
                            rst.getString("CLIMATE"),
                            rst.getString("POPULATION")));
                }
            }else {
                PreparedStatement pstm = c.c.prepareStatement(
                        "SELECT * FROM PLANETS WHERE NAME LIKE ? ");
                pstm.setString(1, "%" + name + "%");

                ResultSet rst = pstm.executeQuery();

                while (rst.next()) {
                    planets.add(new Planet(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("TERRAIN"),
                            rst.getString("GRAVITY"),
                            rst.getString("CLIMATE"),
                            rst.getString("POPULATION")));
                }
            }
            c.closeConnection();
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }

     /**
     * Metodo para llenar la tabla tanto con las columnas y datos.
     * @param searchTable
     */
    public void fillTable(TableView searchTable) {
        if (planets.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Planet, Integer> col_Id = new TableColumn<>("ID");
            TableColumn<Planet, String> col_Name = new TableColumn<>("Name");
            TableColumn<Planet, String> col_Terrain = new TableColumn<>("Terrain");
            TableColumn<Planet, String> col_Gravity = new TableColumn<>("Gravity");
            TableColumn<Planet, String> col_Residents = new TableColumn<>("Climate");
            TableColumn<Planet, String> col_Population = new TableColumn<>("Population");

            searchTable.getColumns().addAll(col_Id, col_Name, col_Terrain, col_Gravity, col_Residents, col_Population);

            col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_Terrain.setCellValueFactory(new PropertyValueFactory<>("terrain"));
            col_Gravity.setCellValueFactory(new PropertyValueFactory<>("gravity"));
            col_Residents.setCellValueFactory(new PropertyValueFactory<>("climate"));
            col_Population.setCellValueFactory(new PropertyValueFactory<>("population"));
            searchTable.getItems().clear();
            searchTable.getItems().addAll(planets);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error Al encontrar Planeta.");
            alert.showAndWait();
        }
    }

    /***
     *
     * @param searchTable
     */
    public void creacionDelete(TableView searchTable){
        TableColumn<Character, Void> col_buttonDelete = new TableColumn<>("Eliminar");
        searchTable.getColumns().add(col_buttonDelete);
        col_buttonDelete.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        for (Planet p : planets) {
            p.getEliminar().setOnAction(actionEvent -> {
                try {
                    Conexion conex = new Conexion();
                    conex.openConnection();
                    Statement stm = conex.c.createStatement();
                    stm.executeUpdate("DELETE FROM PLANETS WHERE ID = " + p.getId());
                    conex.closeConnection();
                    planets.clear();
                    showPlanets("");
                    fillTable(searchTable);
                    creacionDelete(searchTable);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
    }

    /***
     *
     * @param anchorPane
     * @param searchTable
     */
    public void creacionInsert(AnchorPane anchorPane, TableView searchTable){

        Label lblName = new Label("Name: ");
        TextField txtName = new TextField("");
        Label lblTerrain = new Label("Terrain: ");
        TextField txtTerrain = new TextField("");
        Label lblGravity = new Label("Gravity: ");
        TextField txtGravity = new TextField("");
        Label lblClimate = new Label("Climate: ");
        TextField txtClimate = new TextField("");
        Label lblPopulation = new Label("Population: ");
        TextField txtPopulation = new TextField("");
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(actionEvent -> {
            try{
                Conexion c = new Conexion();
                c.openConnection();
                PreparedStatement pstm = c.c.prepareStatement("INSERT INTO PLANETS(NAME, TERRAIN, GRAVITY, CLIMATE, POPULATION) VALUES(?,?,?,?,?)");
                pstm.setString(1,txtName.getText());
                pstm.setString(2,txtTerrain.getText());
                pstm.setString(3,txtGravity.getText());
                pstm.setString(4,txtClimate.getText());
                pstm.setString(5,txtPopulation.getText());
                pstm.executeUpdate();
                planets.clear();
                showPlanets("");
                fillTable(searchTable);
                txtName.setText("");
                txtTerrain.setText("");
                txtGravity.setText("");
                txtClimate.setText("");
                txtPopulation.setText("");
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        });
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(lblName, txtName, lblTerrain, txtTerrain, lblGravity, txtGravity, lblClimate, txtClimate, lblPopulation, txtPopulation, btnAgregar);
        // prefHeight="120.0" prefWidth="570.0"
        lblName.setTranslateY(10);
        txtName.setTranslateY(10);
        txtName.setTranslateX(40);

        lblTerrain.setTranslateY(10);
        txtTerrain.setTranslateY(10);
        lblTerrain.setTranslateX(200);
        txtTerrain.setTranslateX(245);

        lblGravity.setTranslateY(40);
        txtGravity.setTranslateX(45);
        txtGravity.setTranslateY(40);

        lblClimate.setTranslateY(40);
        txtClimate.setTranslateY(40);
        lblClimate.setTranslateX(200);
        txtClimate.setTranslateX(245);


        lblPopulation.setTranslateY(70);
        txtPopulation.setTranslateY(70);
        txtPopulation.setTranslateX(65);


        btnAgregar.setTranslateX(500);
        btnAgregar.setTranslateY(90);
    }


    /**
     * Metodo que hace el guardado en fichero JSON.
     * @param url
     */
    public void saveJson(String url) {
        try {
            File arc = new File(url + ".json");
            ObjectMapper om = new ObjectMapper();
            om.writeValue(arc, planets);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que hace la ordenación del resultado de la API.
     * @param lista
     * @return List<Character>

    private static List<Planet> sortList(List<Planet> lista) {
        List<Planet> orderedList = new ArrayList<>();
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
    xmlMapper.writeValue(arc, planets);
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
    for (Planet pl : planets) {
    escritor.writeObject(pl);
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
    }*/
}
