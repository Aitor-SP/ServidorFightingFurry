package com.mygdx.game.gameplay;

import com.company.model.Mensaje;
import com.github.czyzby.websocket.data.WebSocketCloseCode;
import com.mygdx.game.Cliente;

public class Juego {

    public Cliente cliente;
    public GameRenderer gameRenderer;



    public void onOpen() {
        gameRenderer.mostrarMensaje("CONECTADO");
        cliente.send(new Mensaje("READY"));
    }

    public void onMessage(Mensaje mensaje) {
        if(mensaje.action.equals("START")){
            gameRenderer.mostrarMensaje("GAME START");
        } else if(mensaje.action.equals("CARTAS")){

            gameRenderer.mostrarMensaje("");
            gameRenderer.ponerMano(Mano.fromMensaje(mensaje.mano));
            gameRenderer.repartirCartas();

        } else if(mensaje.action.equals("VIDAS")){
            gameRenderer.mostrarVidas(mensaje.vidas[0], mensaje.vidas[1]);
        }
    }

    public void onClose(WebSocketCloseCode code, String reason) {}

    public void jugar(Carta carta) {
        cliente.send(new Mensaje("JUGADA", carta.toMensaje()));
    }
}
