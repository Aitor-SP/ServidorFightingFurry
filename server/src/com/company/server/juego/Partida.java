package com.company.server.juego;

import jakarta.websocket.Session;

public class Partida {
    Jugador jugador1, jugador2;
    Mazo mazo = new Mazo();
    Jugador turno;

    public boolean faltaJugador() {
        return jugador1 == null || jugador2 == null;
    }

    public void anyadirJugador(Session session) {
        if(jugador1 == null) {
            jugador1 = new Jugador(session);
        }
        else if(jugador2 == null) {
            jugador2 = new Jugador(session);

            jugador2.oponente = jugador1;
            jugador1.oponente = jugador2;
        }
    }

    public void repartirCartasIniciales() {
        jugador1.mano.cartaList.add(mazo.robar());
        jugador1.mano.cartaList.add(mazo.robar());
        jugador1.mano.cartaList.add(mazo.robar());

        jugador2.mano.cartaList.add(mazo.robar());
        jugador2.mano.cartaList.add(mazo.robar());
        jugador2.mano.cartaList.add(mazo.robar());

        turno = jugador1;
    }

    public void hacerJugada(Session cliente, Carta carta) {
        System.out.println("VALE");
        if(turno.session != cliente) return; // cheat
        System.out.println("VALE2");
        if(!turno.mano.tieneCarta(carta)) return; // cheat
        System.out.println("VALE3");

        if(carta.tipo.equals("ataque")) {
            turno.oponente.vida -= carta.valor*turno.xataque - turno.oponente.defensa*turno.xdefensa;
            turno.xataque = 1;
            turno.oponente.xdefensa = 1;
            System.out.println("VENGA!!! " + turno.oponente.vida);

        } else if(carta.tipo.equals("defensa")){
            turno.defensa = carta.valor;
        } else if(carta.tipo.equals("xataque")){
            turno.xataque = carta.valor;
        } else if(carta.tipo.equals("xdefensa")){
            turno.xdefensa = carta.valor;
        }

        turno = turno.oponente;
    }
}
