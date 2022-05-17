package com.example.monitoreos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController implements Initializable {
    @FXML
    private Label welcomeText;
    @FXML
    private TextField entrada;
    @FXML
    private ListView<?> listView;


     imprimirDireccionIp items;
     List<String> saves = new ArrayList<>();
    //String [][] saves = new String[10][3];
     int numDirecciones;
//192.168.100.17 Y9's
//192.168.100.53 LX9

    public void initialize(URL url, ResourceBundle rb) {
            welcomeText.setText("Ingrese una direccion IPv4");
            
            numDirecciones = 0;
    }

    public void agregarIP(ActionEvent actionEvent) throws IOException {
        String dato = entrada.getText();
        //direcciones
        //"192.168.100.41" galaxiA50
        //"192.168.100.38" Laptop
        //"192.168.100.17" Y9's
        //"192.168.100.53" LX9
        ;
            System.out.println(saves.get(numDirecciones));

        numDirecciones++;
        //saves.add(table.getItems());
    }
}