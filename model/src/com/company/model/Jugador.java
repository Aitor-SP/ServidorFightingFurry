package com.company.model;

import java.awt.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Jugador {

    public Posicion posicion;
    public float vx, vy;
    public Color color;
    public List<Disparo> disparos = new CopyOnWriteArrayList<>();

    public Jugador(Posicion posicion, Color color) {
        this.posicion = posicion;
        this.color = color;
    }

    public void actualizar(Posicion posicion, List<Disparo> disparos){
        this.posicion.actualizar(posicion);
        this.disparos.clear();
        this.disparos.addAll(disparos);
    }

    public static class Posicion {
        public float x, y;
        public double angulo;

        public Posicion(float x, float y, double angulo) {
            this.x = x;
            this.y = y;
            this.angulo = angulo;
        }

        private void actualizar(Posicion posicion){
            this.x = posicion.x;
            this.y = posicion.y;
            this.angulo = posicion.angulo;
        }
    }

    public static class Disparo {
        public Posicion posicion;

        public Disparo(float x, float y, double angulo) {
            this.posicion = new Posicion(x, y, angulo);
        }
    }
}