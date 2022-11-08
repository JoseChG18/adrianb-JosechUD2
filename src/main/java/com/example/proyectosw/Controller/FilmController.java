package com.example.proyectosw.Controller;

import com.example.proyectosw.Conexion;
import com.example.proyectosw.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FilmController {
    private List<Film> films = new ArrayList<>();
    /**
     * Metodo que hace la llamada a la API.
     * @param name
     */
    public void showFilms(String name){
        try{
            Conexion c = new Conexion();
            c.openConnection();
            if (name.equals("")) {
                Statement stm = c.c.createStatement();
                ResultSet rst = stm.executeQuery(
                        "SELECT ID,TITLE,DIRECTOR,RELEASEDATE,PRODUCER FROM FILMS");
                while (rst.next()) {
                    films.add(new Film(
                            rst.getInt("ID"),
                            rst.getString("TITLE"),
                            rst.getString("DIRECTOR"),
                            rst.getString("RELEASEDATE"),
                            rst.getString("PRODUCER")));
                }
            } else {
                PreparedStatement pstm = c.c.prepareStatement("SELECT ID,TITLE,DIRECTOR,RELEASEDATE,PRODUCER FROM FILMS WHERE TITLE LIKE ?");
                pstm.setString(1, "%" + name + "%");
                ResultSet rst = pstm.executeQuery();

                while (rst.next()) {
                    films.add(new Film(
                            rst.getInt("ID"),
                            rst.getString("TITLE"),
                            rst.getString("DIRECTOR"),
                            rst.getString("RELEASEDATE"),
                            rst.getString("PRODUCER")));
                }
            }
            c.closeConnection();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    /**
     * Metodo para llenar la tabla tanto con las columnas y datos.
     * @param searchTable
     */
    public void fillTable(TableView searchTable, String option) {
        if (films.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Film, Integer> col_id = new TableColumn<>("Nº film");
            TableColumn<Film, String> col_title = new TableColumn<>("Title");
            TableColumn<Film, String> col_director = new TableColumn<>("Director");
            TableColumn<Film, String> col_release_date = new TableColumn<>("Release date");
            TableColumn<Film, String> col_producer = new TableColumn<>("Producers");

            searchTable.getColumns().addAll(col_id, col_title, col_director, col_release_date, col_producer);

            col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_director.setCellValueFactory(new PropertyValueFactory<>("director"));
            col_release_date.setCellValueFactory(new PropertyValueFactory<>("releaseDate"));
            col_producer.setCellValueFactory(new PropertyValueFactory<>("producer"));
            searchTable.getItems().clear();
            searchTable.getItems().addAll(films);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error Al encontrar Personaje.");
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
            om.writeValue(arc, films);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo que hace la ordenación del resultado de la API.
     * @param lista
     * @return List<Character>

    private static List<Film> sortList(List<Film> lista) {
        List<Film> orderedList = new ArrayList<>();
        ArrayList<Integer> orden = new ArrayList<>();

        for (int i = 0; i < lista.size(); i++) {
            orden.add(lista.get(i).getEpisodeId());
        }
        Collections.sort(orden);
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (orden.get(i).equals(lista.get(j).getEpisodeId())) {
                    orderedList.add(lista.get(j));
                }
            }

        }
        return orderedList;
    }*/

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
    for (Film pj : Json) {
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
