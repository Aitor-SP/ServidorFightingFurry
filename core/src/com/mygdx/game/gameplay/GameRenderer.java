package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.mywidgets.MyLabel;


public class GameRenderer {
    Stage stage;
    public Juego juego;
    MyLabel label, vida1, vida2;

    Mano mano;

    public GameRenderer(Stage stage){
        this.stage = stage;

        label = new MyLabel(10, 35, Color.RED);
        vida1 = new MyLabel(10, 60, Color.YELLOW);
        vida2 = new MyLabel(100, 60, Color.YELLOW);

        stage.addActor(label);
        stage.addActor(vida1);
        stage.addActor(vida2);
    }

    public void repartirCartas() {

        float delay = 0;
        for(Carta carta: mano.cartaList){
            float x = mano.getCardsPositions()[mano.cartaList.indexOf(carta)];
            float y = mano.getY()+1;

            carta.accionRepartir(x, y, delay);

            delay += 0.5f;

            stage.addActor(carta);
        }
    }

    public void touched(Carta carta){
        juego.jugar(carta);
        carta.accionTirar(64, 36);
    }

    public void ponerMano(Mano mano) {
        this.mano = mano;
        stage.addActor(mano);
    }

    public void mostrarMensaje(String text){
        label.setText(text);
    }

    public void mostrarVidas(int v1, int v2){
        vida1.setText(String.valueOf(v1));
        vida2.setText(String.valueOf(v2));
    }
}
