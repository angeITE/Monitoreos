package com.example.monitoreos;
import java.io.IOException;
import java.net.InetAddress;
import java.net.*;

public class hacerPing {
    InetAddress ping;
    String estado;

    public hacerPing(String ip) throws IOException {
        ping = InetAddress.getByName(ip);
        if(ping.isReachable(5000)) {
            this.estado = "conectado";
        }else {
            this.estado = "desconectado";
        }
    }

}
