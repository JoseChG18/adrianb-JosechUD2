package com.example.proyectosw.Controller;

import com.example.proyectosw.Conexion;
import com.example.proyectosw.model.*;
import com.example.proyectosw.model.Character;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.io.File;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void fillTable(TableView searchTable) {
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

    public void creacionDelete(TableView searchTable){
        TableColumn<Character, Void> col_buttonDelete = new TableColumn<>("Eliminar");
        searchTable.getColumns().add(col_buttonDelete);
        col_buttonDelete.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        for (Film f : films) {
            f.getEliminar().setOnAction(actionEvent -> {
                try {
                    Conexion conex = new Conexion();
                    conex.openConnection();
                    Statement stm = conex.c.createStatement();
                    stm.executeUpdate("DELETE FROM FILMS WHERE ID = " + f.getId());
                    conex.closeConnection();
                    films.clear();
                    showFilms("");
                    fillTable(searchTable);
                    creacionDelete(searchTable);
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            });
        }
    }

    public void creacionInsert(AnchorPane anchorPane, TableView searchTable){

        Label lblTitle = new Label("Title: ");
        TextField txtTitle = new TextField("");
        Label lblDirector = new Label("Director: ");
        TextField txtDirector = new TextField("");
        Label lblReleasedate = new Label("Release Date: ");
        TextField txtReleasedate = new TextField("");
        Label lblProducer = new Label("Producer: ");
        TextField txtProducer = new TextField("");
        Button btnAgregar = new Button("Agregar");
        btnAgregar.setOnAction(actionEvent -> {
            try{
                Conexion c = new Conexion();
                c.openConnection();
                PreparedStatement pstm = c.c.prepareStatement("INSERT INTO FILMS(TITLE, DIRECTOR, RELEASEDATE, PRODUCER) VALUES(?,?,?,?)");
                pstm.setString(1,txtTitle.getText());
                pstm.setString(2,txtDirector.getText());
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                pstm.setDate(3, Date.valueOf(formato.format(txtReleasedate.getText())));
                pstm.setString(4,txtProducer.getText());
                pstm.executeUpdate();
                films.clear();
                showFilms("");
                fillTable(searchTable);
                txtTitle.setText("");
                txtDirector.setText("");
                txtReleasedate.setText("");
                txtProducer.setText("");
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        });
        anchorPane.getChildren().clear();
        anchorPane.getChildren().addAll(lblTitle, txtTitle, lblDirector, txtDirector, lblReleasedate, txtReleasedate, lblProducer, txtProducer,  btnAgregar);
        // prefHeight="120.0" prefWidth="570.0"
        lblTitle.setTranslateY(10);
        txtTitle.setTranslateY(10);
        txtTitle.setTranslateX(30);

        lblDirector.setTranslateY(10);
        txtDirector.setTranslateY(10);
        lblDirector.setTranslateX(190);
        txtDirector.setTranslateX(245);

        lblReleasedate.setTranslateY(40);
        txtReleasedate.setTranslateY(40);
        txtReleasedate.setTranslateX(80);

        lblProducer.setTranslateY(40);
        txtProducer.setTranslateY(40);
        lblProducer.setTranslateX(240);
        txtProducer.setTranslateX(300);

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
