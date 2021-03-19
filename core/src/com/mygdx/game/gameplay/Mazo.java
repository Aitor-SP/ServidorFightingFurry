package com.mygdx.game.gameplay;

import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Mazo {
    List<Carta> cartaList = new ArrayList<>();

    Mazo(){
        cartaList.addAll(Arrays.asList(
                new Carta(Carta.Type.A),
                new Carta(Carta.Type.A),
                new Carta(Carta.Type.A),
                new Carta(Carta.Type.B),
                new Carta(Carta.Type.B),
                new Carta(Carta.Type.B),
                new Carta(Carta.Type.C),
                new Carta(Carta.Type.C),
                new Carta(Carta.Type.C),
                new Carta(Carta.Type.D),
                new Carta(Carta.Type.E),
                new Carta(Carta.Type.F),
                new Carta(Carta.Type.G)
        ));
    }

    Carta robar(){
        return cartaList.remove(MathUtils.random(cartaList.size()-1));
    }
}
