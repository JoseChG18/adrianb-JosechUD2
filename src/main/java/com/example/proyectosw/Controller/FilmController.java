package com.example.proyectosw.Controller;

import com.example.proyectosw.model.*;
import com.example.proyectosw.model.Character;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class FilmController {
    private final String url = "https://swapi.dev/api/films/?search=";
    private List<Film> films;
    private List<Film> Json;
    /**
     * Metodo que hace la llamada a la API.
     * @param name
     */
    /**public void setFilms(String name) {
        try {
            URL jsonURL = new URL(url + name + "&format=json");
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseFilm response = objectMapper.readValue(jsonURL, ResponseFilm.class);
            Json = response.getResults();
            films = response.getResults();
            films = sortList(films);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

     * Metodo para llenar la tabla tanto con las columnas y datos.
     * @param searchTable
     */
    public void fillTable(TableView searchTable) {
        if (films.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Film, String> col_title = new TableColumn<>("Title");
            TableColumn<Film, String> col_episode_id = new TableColumn<>("Nº film");
            TableColumn<Film, String> col_director = new TableColumn<>("Director");
            TableColumn<Film, String> col_release_date = new TableColumn<>("Release date");
            TableColumn<Film, String> col_producer = new TableColumn<>("Producers");

            searchTable.getColumns().addAll(col_episode_id, col_title, col_director, col_release_date, col_producer);

            col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
            col_episode_id.setCellValueFactory(new PropertyValueFactory<>("episodeId"));
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
            om.writeValue(arc, Json);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metodo que hace el guardado en fichero XML.
     * @param url
     */
    public void saveXml(String url) {
        try {
            File arc = new File(url + ".xml");
            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.writeValue(arc, Json);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    /**
     * Metodo que hace el guardado en fichero Binario.
     * @param url
     */
    public void saveBinario(String url) {
        File arc = new File(url + ".bin");
        try (ObjectOutputStream escritor = new ObjectOutputStream(new FileOutputStream(arc))) {
            for (Film pj : Json) {
                escritor.writeObject(pj);
            }
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
    /**
     * Metodo que hace el guardado en fichero CSV/TXT.
     * @param url
     */
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
    }
    /**
     * Metodo que hace la ordenación del resultado de la API.
     * @param lista
     * @return List<Character>
     */
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
    }
}
