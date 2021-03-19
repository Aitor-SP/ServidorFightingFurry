package com.mygdx.game.gameplay;

public class Jugador {
    public Jugador oponent;
    Mano mano;
    int vida;
    int mana;

    Jugador(){
        mano = new Mano();
        vida = 100;
        mana = 10;
    }
}
