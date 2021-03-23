package com.company.server.juego;

import com.company.model.Mensaje;

import java.util.ArrayList;
import java.util.List;

public class Mano {
    public List<Carta> cartaList = new ArrayList<>();


    Mensaje.Mano toMensaje(){
        return new Mensaje.Mano(cartaList.stream().map(Carta::toMensaje).toArray(Mensaje.Carta[]::new));
    }

    public boolean tieneCarta(Carta estaCarta) {
        for(Carta carta:cartaList){
            if(carta.tipo.equals(estaCarta.tipo) && carta.valor == estaCarta.valor){
                return true;
            }
        }
        return false;
    }
}
