package com.mygdx.game.gameplay;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameMaster {
    Jugador jugador1, jugador2;
    Jugador turn = jugador1;
    Mazo mazo;

    GameRenderer gameRenderer;

    public GameMaster(Stage stage){
        this.gameRenderer = new GameRenderer(stage, this);
    }

    public void start() {
        jugador1 = new Jugador();
        jugador2 = new Jugador();

        jugador1.oponent = jugador2;
        jugador2.oponent = jugador1;

        jugador1.mano.setPosicion(Mano.Posicion.ABAJO);
        jugador2.mano.setPosicion(Mano.Posicion.ARRIBA);

        gameRenderer.ponerMano(jugador1.mano);
        gameRenderer.ponerMano(jugador2.mano);

        mazo = new Mazo();

        gameRenderer.repartirCartaAJugador(jugador1, jugador1.mano.añadir(mazo.robar()));

        jugador1.mano.añadir(mazo.robar());
        jugador1.mano.añadir(mazo.robar());

        jugador2.mano.añadir(mazo.robar());
        jugador2.mano.añadir(mazo.robar());
        jugador2.mano.añadir(mazo.robar());

        turn = jugador1;
    }

    public void jugar(Carta carta){
        if(carta.jugador == turn){
            turn.oponent.vida -= carta.daño;
            nextTurn();
        }
    }

    public void descartar(Carta carta){
        if(carta.jugador == turn){
            turn.mano.cartas.remove(carta);
            turn.mano.cartas.add(mazo.robar());
        }
    }

    public void nextTurn(){
        if (turn == jugador1) turn = jugador2; else turn = jugador1;
    }
}
