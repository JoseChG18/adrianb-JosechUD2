package com.example.proyectosw.Controller;

import com.example.proyectosw.Conexion;
import com.example.proyectosw.model.Character;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CharacterController {
    private List<Character> characters = new ArrayList();
    private List<Character> Json;

    /**
     * Metodo que hace la llamada a la API.
     *
     * @param name
     */
    public void showCharacter(String name) {
        try {
            Conexion c = new Conexion();
            c.openConnection();
            if (name.equals("")) {
                Statement stm = c.c.createStatement();
                ResultSet rst = stm.executeQuery(
                        "SELECT C.ID,C.NAME,C.GENDER,C.SKIN_COLOR,P.NAME AS HOMEWORLD,C.HAIR_COLOR " +
                                "FROM CHARACTERS AS C JOIN PLANETS AS P ON C.HOMEWORLD = P.ID");
                while (rst.next()) {
                    characters.add(new Character(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("GENDER"),
                            rst.getString("SKIN_COLOR"),
                            rst.getString("HOMEWORLD"),
                            rst.getString("HAIR_COLOR")));
                }
            } else {
                PreparedStatement pstm = c.c.prepareStatement(
                        "SELECT C.ID,C.NAME,C.GENDER,C.SKIN_COLOR,P.NAME AS HOMEWORLD,C.HAIR_COLOR " +
                                "FROM CHARACTERS AS C JOIN PLANETS AS P ON C.HOMEWORLD = P.ID WHERE C.NAME LIKE ? ");
                pstm.setString(1, "%" + name + "%");

                ResultSet rst = pstm.executeQuery();

                while (rst.next()) {
                    characters.add(new Character(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("GENDER"),
                            rst.getString("SKIN_COLOR"),
                            rst.getString("HOMEWORLD"),
                            rst.getString("HAIR_COLOR")));
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
        if (characters.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Character, Integer> col_Id = new TableColumn<>("ID");
            TableColumn<Character, String> col_Name = new TableColumn<>("Name");
            TableColumn<Character, String> col_Gender = new TableColumn<>("Gender");
            TableColumn<Character, String> col_SkinColor = new TableColumn<>("Skin color");
            TableColumn<Character, String> col_Homeworld = new TableColumn<>("Homeworld");
            TableColumn<Character, String> col_HairColor = new TableColumn<>("Hair Color");

            searchTable.getColumns().addAll(col_Id, col_Name, col_Gender, col_SkinColor, col_Homeworld, col_HairColor);

            col_Id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
            col_SkinColor.setCellValueFactory(new PropertyValueFactory<>("skinColor"));
            col_Homeworld.setCellValueFactory(new PropertyValueFactory<>("homeworld"));
            col_HairColor.setCellValueFactory(new PropertyValueFactory<>("hairColor"));
            searchTable.getItems().clear();
            searchTable.getItems().addAll(characters);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error Al encontrar Personaje.");
            alert.showAndWait();
        }
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
            om.writeValue(arc, characters);

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
    private static List<Character> sortList(List<Character> lista) {
        List<Character> orderedList = new ArrayList<>();
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
     *
     * @param url

    public void saveXml(String url) {
    try {
    File arc = new File(url + ".xml");
    XmlMapper xmlMapper = new XmlMapper();

    xmlMapper.writeValue(arc, characters);

    } catch (Exception e) {
    System.out.println(e.getMessage());
    }
    }*/

    /**
     * Metodo que hace el guardado en fichero Binario.
     *
     * @param url

    public void saveBinario(String url) {
    File arc = new File(url + ".bin");
    try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(arc))) {
    for (Character pj : characters) {
    escritor.writeObject(pj);
    }
    } catch (IOException ex) {
    System.err.println(ex.getMessage());
    }
    }*/

    /**
     * Metodo que hace el guardado en fichero CSV/TXT.
     *
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

