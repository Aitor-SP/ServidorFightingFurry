package com.company.server;

import com.company.model.Mensaje;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/", decoders = Mensaje.Decoder.class, encoders = Mensaje.Encoder.class)
public class ServidorEndpoint {
    private static final Set<Session> clientesConectados = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session cliente) {
        clientesConectados.add(cliente);
    }

    @OnMessage
    public void onMessage(Session cliente, Mensaje mensaje) {
        difundirMensaje(cliente, mensaje);
    }

    @OnClose
    public void onClose(Session cliente) {
        clientesConectados.remove(cliente);
        difundirMensaje(cliente, Mensaje.exitGame());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {}

    private static void difundirMensaje(Session clienteQueEnvia, Mensaje mensaje) {
        synchronized(clientesConectados){
            for(Session clienteConectado: clientesConectados){
                if (!clienteConectado.equals(clienteQueEnvia)){
                    try {
                        clienteConectado.getBasicRemote().sendObject(mensaje.setIdDelQueEnvia(clienteQueEnvia.getId()));
                    } catch (Exception e) {}
                }
            }
        }
    }
}

