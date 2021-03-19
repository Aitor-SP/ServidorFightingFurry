package com.company.model;

import com.google.gson.Gson;
import jakarta.websocket.EndpointConfig;

import java.awt.*;
import java.util.List;


public class Mensaje {
    public enum Tipo {
        ENTRAR_AL_JUEGO, SALIR_DEL_JUEGO, ESTOY_JUGANDO, POSICION
    }

    private static Gson gson = new Gson();

    public String idDelQueEnvia;
    public Color color;
    public Tipo tipo;
    public Jugador.Posicion posicion;
    public List<Jugador.Disparo> disparos;

    public Mensaje(){}

    public static Mensaje posicion(Jugador jugador){
        Mensaje mensaje = new Mensaje();
        mensaje.tipo = Tipo.POSICION;
        mensaje.posicion = jugador.posicion;
        mensaje.disparos = jugador.disparos;
        return mensaje;
    }

    public static Mensaje entrarAlJuego(Jugador jugador){
        Mensaje mensaje = new Mensaje();
        mensaje.tipo = Tipo.ENTRAR_AL_JUEGO;
        mensaje.color = jugador.color;
        mensaje.posicion = jugador.posicion;
        return mensaje;
    }

    public static Mensaje estoyJugando(Jugador jugador){
        Mensaje mensaje = new Mensaje();
        mensaje.tipo = Tipo.ESTOY_JUGANDO;
        mensaje.color = jugador.color;
        mensaje.posicion = jugador.posicion;
        mensaje.disparos = jugador.disparos;
        return mensaje;
    }

    public static Mensaje exitGame(){
        Mensaje mensaje = new Mensaje();
        mensaje.tipo = Tipo.SALIR_DEL_JUEGO;
        return mensaje;
    }

    public Mensaje setIdDelQueEnvia(String idDelQueEnvia){
        this.idDelQueEnvia = idDelQueEnvia;
        return this;
    }

    public static class Encoder implements jakarta.websocket.Encoder.Text<Mensaje> {
        @Override
        public String encode(Mensaje mensaje) {
            return gson.toJson(mensaje);
        }

        @Override
        public void init(EndpointConfig config) {}

        @Override
        public void destroy() {}
    }

    public static class Decoder implements jakarta.websocket.Decoder.Text<Mensaje> {
        @Override
        public Mensaje decode(String s) {
            return gson.fromJson(s, Mensaje.class);
        }

        @Override
        public boolean willDecode(String s) {
            return (s != null);
        }

        @Override
        public void init(EndpointConfig config) {}

        @Override
        public void destroy() {}
    }
}
