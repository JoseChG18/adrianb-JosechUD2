package com.example.proyectosw.Controller;

import com.example.proyectosw.model.Character;
import com.example.proyectosw.model.Planet;
import com.example.proyectosw.model.ResponseCharacter;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CharacterController {
    private final String url = "https://swapi.dev/api/people/?search=";
    private List<Character> characters;
    private List<Character> Json;

    /**
     * Metodo que hace la llamada a la API.
     * @param name
     */
    public void setCharacters(String name) {
        try {
            URL jsonURL = new URL(url + name + "&format=json");
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseCharacter response = objectMapper.readValue(jsonURL, ResponseCharacter.class);
            Json = response.getResults();
            characters = response.getResults();
            characters = sortList(characters);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodo para hacer llamada a la API y obtener los nombres de planetas.
     */
    public void changeHomeworld() {
        List<String> list = new ArrayList<String>();
        int cont = 0;
        for (Character pj : characters) {
            list.add(cont, pj.getHomeworld());
            cont++;
        }
        list = list.stream().distinct().collect(Collectors.toList());

        if (list.size() == 1) {
            try {
                URL jsonURL = new URL(list.get(0) + "?format=json");
                ObjectMapper objectMapper = new ObjectMapper();
                Planet pl = objectMapper.readValue(jsonURL, Planet.class);
                for (Character pj : characters) {
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
                    for (Character pj : characters) {
                        if (pj.getHomeworld().equals(pl.getUrl())) {
                            pj.setHomeworld(pl.getName());
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
        if (characters.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Character, String> col_Name = new TableColumn<>("Name");
            TableColumn<Character, String> col_Gender = new TableColumn<>("Gender");
            TableColumn<Character, String> col_SkinColor = new TableColumn<>("Skin color");
            TableColumn<Character, String> col_Homeworld = new TableColumn<>("Homeworld");
            TableColumn<Character, String> col_HairColor = new TableColumn<>("Hair Color");

            searchTable.getColumns().addAll(col_Name, col_Gender, col_SkinColor, col_Homeworld, col_HairColor);

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
            for (Character pj : Json) {
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
}

