package com.mygdx.game;

import com.badlogic.gdx.utils.Json;
import com.company.model.Mensaje;
import com.github.czyzby.websocket.AbstractWebSocketListener;
import com.github.czyzby.websocket.WebSocket;
import com.github.czyzby.websocket.data.WebSocketCloseCode;
import com.github.czyzby.websocket.data.WebSocketException;
import com.github.czyzby.websocket.net.ExtendedNet;
import com.mygdx.game.gameplay.Juego;

public class Cliente {
    private WebSocket socket;
    static Json json = new Json();

    Juego juego;

    public Cliente(){}

    public void connect(){
//        String host = "192.168.246.246";
//        String host = "192.168.1.100";
        String host = "localhost";
        socket = ExtendedNet.getNet().newWebSocket(host, 12345);

        socket.addListener(new AbstractWebSocketListener() {
            @Override
            public boolean onOpen(final WebSocket webSocket) {
                juego.onOpen();
                return FULLY_HANDLED;
            }

            @Override
            public boolean onClose(final WebSocket webSocket, final WebSocketCloseCode code, final String reason) {
                juego.onClose(code, reason);
                return FULLY_HANDLED;
            }


            @Override
            public boolean onMessage(final WebSocket webSocket, final String s) {
                System.out.println("onMessage: " + s);
                Mensaje mensaje = json.fromJson(Mensaje.class, s);
                juego.onMessage(mensaje);

                return FULLY_HANDLED;
            }

            @Override
            protected boolean onMessage(WebSocket webSocket, Object packet) throws WebSocketException {
                return false;
            }
        });

        try {
            socket.connect();
        } catch (Exception e){
            System.out.println("NO SE PUDO CONECTAR AL SERVIDOR");
        }
    }

    public void send(Mensaje mensaje){
        socket.send(json.toJson(mensaje));
    }
}

