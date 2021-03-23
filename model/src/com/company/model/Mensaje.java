package com.company.model;

public class Mensaje {
    public String action;
    public Mano mano;
    public Carta carta;
    public int[] vidas;

    public Mensaje(){}

    public Mensaje(String action) {
        this.action = action;
    }

    public Mensaje(String action, Carta carta) {
        this.action = action;
        this.carta = carta;
    }

    public Mensaje(String action, Mano mano) {
        this.action = action;
        this.mano = mano;
    }

    public Mensaje(String action, int[] vidas){
        this.action = action;
        this.vidas = vidas;
    }

    public static class Carta {
        public String tipo;
        public int valor;

        public Carta(){}

        public Carta(String tipo, int valor) {
            this.tipo = tipo;
            this.valor = valor;
        }
    }

    public static class Mano {
        public Carta[] cartaList;

        public Mano(){}

        public Mano(Carta[] cartaList) {
            this.cartaList = cartaList;
        }
    }
}
