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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpeciesController {
    private List<Species> species = new ArrayList<>();

    /**
     * Metodo que hace la llamada a la base de datos.
     *
     * @param name
     */
    public void showSpecies(String name) {
        try {
            Conexion c = new Conexion();
            c.openConnection();
            if (name.equals("")) {
                Statement stm = c.c.createStatement();
                ResultSet rst = stm.executeQuery("SELECT S.ID, S.NAME, S.CLASSIFICATION, P.NAME AS HOMEWORLD, S.LANGUAGE, S.AVERAGELIFESPAN " +
                        "FROM SPECIES AS S LEFT JOIN PLANETS AS P ON S.HOMEWORLD = P.ID");
                while (rst.next()) {
                    species.add(new Species(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("CLASSIFICATION"),
                            rst.getString("HOMEWORLD"),
                            rst.getString("LANGUAGE"),
                            rst.getString("AVERAGELIFESPAN")));
                }
            } else {
                PreparedStatement pstm = c.c.prepareStatement("SELECT S.ID, S.NAME, S.CLASSIFICATION, P.NAME AS HOMEWORLD, S.LANGUAGE, S.AVERAGELIFESPAN " +
                        "FROM SPECIES AS S LEFT JOIN PLANETS AS P ON S.HOMEWORLD = P.ID WHERE S.NAME LIKE ?");
                pstm.setString(1, "%" + name + "%");

                ResultSet rst = pstm.executeQuery();

                while (rst.next()) {
                    species.add(new Species(
                            rst.getInt("ID"),
                            rst.getString("NAME"),
                            rst.getString("CLASSIFICATION"),
                            rst.getString("HOMEWORLD"),
                            rst.getString("LANGUAGE"),
                            rst.getString("AVERAGELIFESPAN")));
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
        if (species.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Species, Integer> col_id = new TableColumn<>("id");
            TableColumn<Species, String> col_name = new TableColumn<>("Name");
            TableColumn<Species, String> col_classification = new TableColumn<>("Classification");
            TableColumn<Species, String> col_homeworld = new TableColumn<>("Homeworld");
            TableColumn<Species, String> col_language = new TableColumn<>("Language");
            TableColumn<Species, String> col_average_lifespan = new TableColumn<>("Average lifespan");

            searchTable.getColumns().addAll(col_id, col_name, col_classification, col_homeworld, col_language, col_average_lifespan);

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_classification.setCellValueFactory(new PropertyValueFactory<>("classification"));
            col_homeworld.setCellValueFactory(new PropertyValueFactory<>("homeworld"));
            col_language.setCellValueFactory(new PropertyValueFactory<>("language"));
            col_average_lifespan.setCellValueFactory(new PropertyValueFactory<>("averageLifespan"));
            searchTable.getItems().clear();
            searchTable.getItems().addAll(species);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error Al encontrar Personaje.");
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
        for (Species sp : species) {
            sp.getEliminar().setOnAction(actionEvent -> {
                try {
                    Conexion conex = new Conexion();
                    conex.openConnection();
                    Statement stm = conex.c.createStatement();
                    stm.executeUpdate("DELETE FROM SPECIES WHERE ID = " + sp.getId());
                    conex.closeConnection();
                    species.clear();
                    showSpecies("");
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
        //(NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN)
        Label lblName = new Label("Name: ");
        TextField txtName = new TextField("");
        Label lblClasi = new Label("Classification: ");
        TextField txtClasi = new TextField("");
        Label lblHomeworld = new Label("Homeworld: ");
        TextField txtHomeworld = new TextField("");
        Label lblLang = new Label("Language: ");
        TextField txtLang = new TextField("");
        Label lblAvglifespan = new Label("Average Lifespan: ");
        TextField txtAvglifespan = new TextField("");
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(actionEvent -> {
            try{
                Conexion c = new Conexion();
                c.openConnection();
                PreparedStatement pstm = c.c.prepareStatement("INSERT INTO SPECIES(NAME, CLASSIFICATION, HOMEWORLD, LANGUAGE, AVERAGELIFESPAN) VALUES(?,?,?,?,?)");
                pstm.setString(1,txtName.getText());
                pstm.setString(2,txtClasi.getText());
                pstm.setInt(3,Integer.parseInt(txtHomeworld.getText()));
                pstm.setString(4,txtLang.getText());
                pstm.setString(5,txtAvglifespan.getText());
                pstm.executeUpdate();
                species.clear();
                showSpecies("");
                fillTable(searchTable);
                txtName.setText("");
                txtClasi.setText("");
                txtAvglifespan.setText("");
                txtHomeworld.setText("");
                txtLang.setText("");
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        });
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(lblName, txtName, lblClasi, txtClasi, lblHomeworld, txtHomeworld, lblLang, txtLang, lblAvglifespan, txtAvglifespan, btnAgregar);
        // prefHeight="120.0" prefWidth="570.0"
        lblName.setTranslateY(10);
        txtName.setTranslateY(10);
        txtName.setTranslateX(40);

        lblClasi.setTranslateY(10);
        txtClasi.setTranslateY(10);
        lblClasi.setTranslateX(200);
        txtClasi.setTranslateX(290);

        lblHomeworld.setTranslateY(40);
        txtHomeworld.setTranslateX(70);
        txtHomeworld.setTranslateY(40);

        lblLang.setTranslateY(40);
        txtLang.setTranslateY(40);
        lblLang.setTranslateX(230);
        txtLang.setTranslateX(290);

        lblAvglifespan.setTranslateY(70);
        txtAvglifespan.setTranslateY(70);
        txtAvglifespan.setTranslateX(100);

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
            om.writeValue(arc, species);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que hace la ordenación del resultado de la API.
     *
     * @param lista
     * @return List<Character>

    private static List<Species> sortList(List<Species> lista) {
        List<Species> orderedList = new ArrayList<>();
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

    xmlMapper.writeValue(arc, species);

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
    for (Species pj : species) {
    escritor.writeObject(pj);
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
