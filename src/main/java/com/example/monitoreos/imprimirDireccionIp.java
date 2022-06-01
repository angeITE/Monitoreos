package com.example.monitoreos;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class imprimirDireccionIp {
    ObservableList<FormatoDirecciones> items;
    FormatoDirecciones objeto;
    List<FormatoDirecciones> guadados = new ArrayList<>();
    List<String> direccionesIP = new ArrayList<>();

    public imprimirDireccionIp(String dato) throws IOException {
        new hacerPing(dato);
    }

    ObservableList<FormatoDirecciones> hacerDireccion(String direccion){

        try {
            items = FXCollections.observableArrayList(new FormatoDirecciones(
                    new hacerPing(direccion).ping.getHostName(),
                    new hacerPing(direccion).estado,
                    new hacerPing(direccion).ping.getHostAddress()
                    )
            );
                return items;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    void llenarCeldas(String direccion) throws IOException {


        }
    }

