package com.mygdx.game.gameplay;

import com.company.model.Mensaje;
import com.github.czyzby.websocket.data.WebSocketCloseCode;
import com.mygdx.game.S;

public class Juego {

    Mano mano;

    public void cuandoConecte() {
        S.renderizador.mostrarMensaje("CONECTADO");
        S.clienteWS.enviar(new Mensaje("READY"));
    }

    public void cuandoLlegueUnMensaje(Mensaje mensaje) {
        switch (mensaje.action) {
            case "START":
                S.renderizador.mostrarMensaje("GAME START");
                break;
            case "CARTAS":
                mano = Mano.fromMensaje(mensaje.mano);

                S.renderizador.mostrarMensaje("");
                S.renderizador.ponerMano(mano);
                S.renderizador.repartirCartas();
                break;
            case "VIDAS":
                S.renderizador.mostrarVidas(mensaje.vidas[0], mensaje.vidas[1]);
                break;
        }
    }

    public void cuandoDesconecte(WebSocketCloseCode code, String reason) {}

    public void jugar(Carta carta) {
        S.clienteWS.enviar(new Mensaje("JUGADA", carta.toMensaje()));
        mano.cartaList.remove(carta);
    }
}
