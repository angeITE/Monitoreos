package com.example.monitoreos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField entrada;
    @FXML
    private TableView<FormatoDirecciones> table;
    @FXML
    private TableColumn<String,String> estado;
    @FXML
    private TableColumn<String,String> direccion;
    @FXML
    private TableColumn<String,String> nombre;


     int numDirecciones;
ObservableList<String> saves;

    ObservableList<FormatoDirecciones> vista = FXCollections.observableArrayList(
                new FormatoDirecciones(
                        new hacerPing("192.168.100.41").ping.getHostAddress(),
                        new hacerPing("192.168.100.41").estado,
                        new hacerPing("192.168.100.41").ping.getHostName()
                )
        );

    public HelloController() throws IOException {
    }

    public void initialize(URL url, ResourceBundle rb) {
            welcomeText.setText("Ingrese una direccion IPv4");


        estado.setCellValueFactory(new PropertyValueFactory<String,String>("estado"));
        direccion.setCellValueFactory(new PropertyValueFactory<String,String>("direccion"));
        nombre.setCellValueFactory(new PropertyValueFactory<String,String>("nombre"));
        table.setItems(vista);
    }


    public void agregarIP(ActionEvent actionEvent) throws IOException {
        String dato = entrada.getText();
        if(dato.isEmpty())
        {
            entrada.setText("Favor de ingresar alguna direccion");
        } else if(validaraIP(dato) && revisarLista(dato)){
            vista.add(new FormatoDirecciones(
                    new hacerPing(dato).ping.getHostAddress(),
                    new hacerPing(dato).estado,
                    new hacerPing(dato).ping.getHostName()
                    )
            );
        }else {
            entrada.setText("Favor de ingresar una direccion valida");
        }
        //"192.168.100.41"
    }

    private boolean revisarLista(String dato) {
        boolean val = true;
        for(int i =0; i< vista.size();i++){
            if(dato.equals(vista.get(i).getDireccion()))
            {
                return val = false;
            }else {
                 val = true;
            }
        }
        return val;
    }

    private boolean validaraIP(String ip) {
        boolean val = false;
        String regex = "(\\b25[0-5]|\\b2[0-4][0-9]|\\b[01]?[0-9][0-9]?)(\\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)){3}";
        val = Pattern.matches(regex, ip);
        return val;
    }
}