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

    public imprimirDireccionIp(){}


    public imprimirDireccionIp(String dato) throws IOException {
        direccionesIP.add(dato);
        hacerDireccion(dato);
    }

    imprimirDireccionIp hacerMultiDirecciones(List<String> dato) throws  IOException{
        return (imprimirDireccionIp) items;
    }

    ObservableList<FormatoDirecciones> hacerDireccion(String direccion){

        try {
            items = FXCollections.observableArrayList(llenarCeldas());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
    FormatoDirecciones llenarCeldas() throws IOException {

        for(int u=0;u<=direccionesIP.size();u++) {
            String var = direccionesIP.get(u);
            objeto = new FormatoDirecciones(
                      new hacerPing(var).ping.getHostName(),
                      new hacerPing(var).estado,
                      new hacerPing(var).ping.getHostAddress()
            );
           guadados.add(objeto);
        }
              return (FormatoDirecciones) guadados;
    }
}
