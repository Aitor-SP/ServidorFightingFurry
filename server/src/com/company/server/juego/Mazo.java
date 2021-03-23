package com.company.server.juego;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Mazo {
    static Random random = new Random();

    List<Carta> cartaList = new ArrayList<>();

    Mazo(){
        cartaList.addAll(Arrays.asList(
            new Carta("ataque", 5),
                new Carta("ataque", 10),
                new Carta("ataque", 1),
                new Carta("defensa", 5),
                new Carta("defensa", 10),
                new Carta("xataque", 2),
                new Carta("xataque", 3),
                new Carta("xdefensa", 2),
                new Carta("xdefensa", 3)
            ));
    }
    Carta robar(){
        return cartaList.remove(random.nextInt(cartaList.size()));
    }

}
