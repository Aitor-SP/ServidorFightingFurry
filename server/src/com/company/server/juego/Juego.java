package com.company.server.juego;

import com.company.model.Mensaje;
import jakarta.websocket.Session;

import java.util.HashMap;
import java.util.Map;


public class Juego {

    Map<String, Partida> partidaMap = new HashMap<>();
    Partida partida = new Partida();

    public void onOpen(Session cliente) {}

    public void onMessage(Session cliente, Mensaje mensaje) {
        if(mensaje.action.equals("READY")){
            if (partida.faltaJugador()){
                partida.anyadirJugador(cliente);

                if(!partida.faltaJugador()) {
                    partida.jugador1.send(new Mensaje("START"));
                    partida.jugador2.send(new Mensaje("START"));

                    partida.repartirCartasIniciales();

                    partida.jugador1.enviarCartas();
                    partida.jugador2.enviarCartas();
                }
            }
        } else if (mensaje.action.equals("JUGADA")){
            partida.hacerJugada(cliente, new Carta(mensaje.carta));
            partida.jugador1.send(new Mensaje("VIDAS", new int[]{partida.jugador1.vida, partida.jugador2.vida}));
            partida.jugador2.send(new Mensaje("VIDAS", new int[]{partida.jugador1.vida, partida.jugador2.vida}));
        }
    }

    public void onClose(Session cliente) {}

    public void onError(Session session) {}
}
