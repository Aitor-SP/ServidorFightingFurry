package com.company.server.juego;

import com.company.model.Mensaje;
import jakarta.websocket.Session;

public class Jugador {
    public Jugador oponente;
    public int defensa;
    public int xataque;
    public int xdefensa;
    Session session;

    int vida;
    Mano mano = new Mano();

    public Jugador(Session session) {
        this.session = session;
        vida = 100;
        xataque = xdefensa = 1;
    }

    void send(Mensaje mensaje) {
        try {
            session.getBasicRemote().sendObject(mensaje);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void enviarCartas() {
        send(new Mensaje("CARTAS", mano.toMensaje()));
    }
}
