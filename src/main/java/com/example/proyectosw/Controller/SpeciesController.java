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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SpeciesController {
    private final String url = "https://swapi.dev/api/species/?search=";
    private List<Species> species;
    /**
     * Metodo que hace la llamada a la API.
     * @param name
     */
    /**public void setSpecies(String name) {
        try {
            URL jsonURL = new URL(url + name + "&format=json");
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseSpecies response = objectMapper.readValue(jsonURL, ResponseSpecies.class);
            species = response.getResults();
            species = sortList(species);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

     * Metodo para hacer llamada a la API y obtener los nombres de planetas.
     */
    public void changeHomeworld() {
        List<String> list = new ArrayList<>();
        int cont = 0;
        for (Species sp : species) {
            if (sp.getHomeworld() != null) {
                list.add(cont, sp.getHomeworld());
                cont++;
            }
        }

        list = list.stream().distinct().collect(Collectors.toList());

        if (list.size() == 1) {
            try {
                URL jsonURL = new URL(list.get(0) + "?format=json");
                ObjectMapper objectMapper = new ObjectMapper();
                Planet pl = objectMapper.readValue(jsonURL, Planet.class);
                for (Species pj : species) {
                    pj.setHomeworld(pl.getName());
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            try {
                List<Planet> planets = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    URL jsonURL = new URL(list.get(i) + "?format=json");
                    ObjectMapper objectMapper = new ObjectMapper();
                    Planet pl = objectMapper.readValue(jsonURL, Planet.class);
                    planets.add(pl);
                }
                for (Planet pl : planets) {
                    for (Species sp : species) {
                        if (sp.getHomeworld() != null && sp.getHomeworld().equals(pl.getUrl())) {
                            sp.setHomeworld(pl.getName());
                        }
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    /**
     * Metodo para llenar la tabla tanto con las columnas y datos.
     * @param searchTable
     */
    public void fillTable(TableView searchTable) {
        if (species.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Species, String> col_name = new TableColumn<>("Name");
            TableColumn<Species, String> col_classification = new TableColumn<>("Classification");
            TableColumn<Species, String> col_homeworld = new TableColumn<>("Homeworld");
            TableColumn<Species, String> col_language = new TableColumn<>("Language");
            TableColumn<Species, String> col_average_lifespan = new TableColumn<>("Average lifespan");

            searchTable.getColumns().addAll(col_name, col_classification, col_homeworld, col_language, col_average_lifespan);

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
    /**
     * Metodo que hace el guardado en fichero JSON.
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
     * Metodo que hace el guardado en fichero XML.
     * @param url
     */
    public void saveXml(String url) {
        try {
            File arc = new File(url + ".xml");
            XmlMapper xmlMapper = new XmlMapper();

            xmlMapper.writeValue(arc, species);

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
            for (Species pj : species) {
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
}
