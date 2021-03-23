package com.company.server.juego;

import com.company.model.Mensaje;

public class Carta {
    String tipo;
    int valor;

    public Carta(String tipo, int valor){
        this.tipo = tipo;
        this.valor = valor;
    }

    public Carta(Mensaje.Carta carta){
        this.tipo = carta.tipo;
        this.valor = carta.valor;
    }


    Mensaje.Carta toMensaje(){
        return new Mensaje.Carta(tipo, valor);
    }
}
