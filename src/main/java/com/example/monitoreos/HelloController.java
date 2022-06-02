package com.example.monitoreos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;


public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField entrada, busqueda1,busqueda2;
    @FXML
    private TableView<FormatoDirecciones> table;
    @FXML
    private TableColumn<String,String> estado;
    @FXML
    private TableColumn<String,String> direccion;
    @FXML
    private TableColumn<String,String> nombre;



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

        estado.setCellValueFactory(new PropertyValueFactory<String,String>("estado"));
        direccion.setCellValueFactory(new PropertyValueFactory<String,String>("direccion"));
        nombre.setCellValueFactory(new PropertyValueFactory<String,String>("nombre"));
        table.setItems(vista);
    }


    public void agregarIP(ActionEvent actionEvent) throws IOException {
        Alert alert;
        String dato = entrada.getText();
        if(dato.isEmpty())
        {
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campos vacios");
            alert.setHeaderText("favor de ingresar algun dato");
            alert.show();
        } else if(validaraIP(dato) && revisarLista(dato)){
            vista.add(new FormatoDirecciones(
                    new hacerPing(dato).ping.getHostAddress(),
                    new hacerPing(dato).estado,
                    new hacerPing(dato).ping.getHostName()
                    )
            );
        }else {
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("IP invalida");
            alert.setHeaderText("Revise la IP ingresada");
            alert.setContentText("Puede que ya se haya ingresado dicha IP o este mal escrita.");
            alert.show();
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

    public void rangoIP(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        String rango1 =busqueda1.getText();
        String [] parts1 = rango1.split("\\.");
        String rango2 =busqueda2.getText();
        String [] parts2 = rango2.split("\\.");
        if(validaraIP(rango1)){
            if (validaraIP(rango2)){
                if(rango1.equals(rango2)){
                    alert.setTitle("Rango no valido");
                    alert.setHeaderText("Las direcciones ip no pueden ser iguales");
                    alert.show();
                }else {
                    int entero1 = Integer.parseInt(parts1[3]);
                    int entero2 = Integer.parseInt(parts2[3]);
                    String cuerpo = parts1[0]+"."+parts1[1]+"."+parts1[2]+".";
                    vista.clear();
                    for(int i = entero1; i<entero2;i++){
                        vista.add(new FormatoDirecciones(
                                        new hacerPing(cuerpo+i).ping.getHostAddress(),
                                        new hacerPing(cuerpo+i).estado,
                                        new hacerPing(cuerpo+i).ping.getHostName()
                                )
                        );
                    }
                    vista.add(new FormatoDirecciones(
                                    new hacerPing(rango2).ping.getHostAddress(),
                                    new hacerPing(rango2).estado,
                                    new hacerPing(rango2).ping.getHostName()
                            )
                    );
                }
            }else {
                alert.setTitle("Rango no valido");
                alert.setHeaderText("Las direcciones ip no son validas");
                alert.show();
            }
        } else {
            alert.setTitle("Rango no valido");
            alert.setHeaderText("El rango que ingreso no es valido");
            alert.setContentText("un ejemplo de rango ip seria: 192.168.0.2 a 192.168.0.26");
            alert.show();
        }
    }

    public void limpiar(ActionEvent actionEvent) throws IOException{
        vista.clear();
    }
}