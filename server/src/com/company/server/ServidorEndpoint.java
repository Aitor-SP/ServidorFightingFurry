package com.company.server;

import com.company.model.Mensaje;
import com.company.server.juego.Juego;
import com.google.gson.Gson;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/", encoders = ServidorEndpoint.MyEncoder.class, decoders = ServidorEndpoint.MyDecoder.class)
public class ServidorEndpoint {
    static Gson gson = new Gson();
    static Juego juego = new Juego();

    public ServidorEndpoint() {}

    @OnOpen
    public void onOpen(Session cliente) {
        System.out.println("NEW CLIENT: " + cliente.getId());
        juego.onOpen(cliente);
    }

    @OnMessage
    public void onMessage(Session cliente, Mensaje mensaje) {
        System.out.println("MENSAJE DE " + cliente.getId() + " => " + mensaje.action);
        juego.onMessage(cliente, mensaje);
    }

    @OnClose
    public void onClose(Session cliente) {
        System.out.println("CLOSED CONNECTION " + cliente.getId());
        juego.onClose(cliente);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        System.out.println("ERROR FROM " + session.getId()  +" : " + throwable.getMessage());
        juego.onError(session);
    }

    public static class MyEncoder implements Encoder.Text<Mensaje> {
        @Override
        public String encode(Mensaje mensaje) {
            return gson.toJson(mensaje);
        }

        @Override
        public void init(EndpointConfig config) {}

        @Override
        public void destroy() {}
    }

    public static class MyDecoder implements Decoder.Text<Mensaje> {
        @Override
        public void init(EndpointConfig config) {}

        @Override
        public void destroy() {}

        @Override
        public Mensaje decode(String s) {
            return gson.fromJson(s, Mensaje.class);
        }

        @Override
        public boolean willDecode(String s) {
            return (s != null);
        }
    }
}

