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

public class VehiclesController {
    private final String url = "https://swapi.dev/api/vehicles/?search=";
    private List<Vehicle> vehicles;
    private List<Vehicle> Json;
    /**
     * Metodo que hace la llamada a la API.
     * @param name

    public void setVehicles(String name) {
        try {
            URL jsonURL = new URL(url + name + "&format=json");
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseVehicles response = objectMapper.readValue(jsonURL, ResponseVehicles.class);
            Json = response.getResults();
            vehicles = response.getResults();
            vehicles = sortList(vehicles);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }*/
    /**
     * Metodo para llenar la tabla tanto con las columnas y datos.
     * @param searchTable
     */
    public void fillTable(TableView searchTable) {
        if (vehicles.size() > 0) {
            searchTable.getColumns().clear();
            TableColumn<Vehicle, String> col_Name = new TableColumn<>("Name");
            TableColumn<Vehicle, String> col_Model = new TableColumn<>("Model");
            TableColumn<Vehicle, String> col_Manufacturer = new TableColumn<>("Manufacturer");
            TableColumn<Vehicle, String> col_Cost = new TableColumn<>("Cost in credits");
            TableColumn<Vehicle, String> col_Length = new TableColumn<>("length");
            TableColumn<Vehicle, String> col_Passengers = new TableColumn<>("Passengers");
            TableColumn<Vehicle, String> col_CargoCapacity = new TableColumn<>("Cargo Capacity");
            TableColumn<Vehicle, String> col_VehicleClass = new TableColumn<>("Vehicle Class");

            searchTable.getColumns().addAll(col_Name, col_Model, col_Manufacturer, col_Cost, col_Length, col_Passengers, col_CargoCapacity, col_VehicleClass);

            col_Name.setCellValueFactory(new PropertyValueFactory<>("name"));
            col_Model.setCellValueFactory(new PropertyValueFactory<>("model"));
            col_Manufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
            col_Cost.setCellValueFactory(new PropertyValueFactory<>("costInCredits"));
            col_Length.setCellValueFactory(new PropertyValueFactory<>("length"));
            col_Passengers.setCellValueFactory(new PropertyValueFactory<>("passengers"));
            col_CargoCapacity.setCellValueFactory(new PropertyValueFactory<>("cargoCapacity"));
            col_VehicleClass.setCellValueFactory(new PropertyValueFactory<>("vehicleClass"));
            searchTable.getItems().clear();
            searchTable.getItems().addAll(vehicles);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setContentText("Error Al encontrar Vehicles.");
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
            for (Vehicle vh : Json) {
                escritor.writeObject(vh);
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
    private static List<Vehicle> sortList(List<Vehicle> lista) {
        List<Vehicle> orderedList = new ArrayList<>();
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
