package com.example.proyectosw.Controller;

import com.example.proyectosw.Conexion;
import com.example.proyectosw.model.Planet;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.*;
import java.sql.*;
import java.util.*;

public class PlanetController {
    private List<Planet> planets = new ArrayList<>();
    /**
     * Metodo que hace la llamada a la API.
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
    public void fillTable(TableView searchTable, String option) {
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
     */
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
