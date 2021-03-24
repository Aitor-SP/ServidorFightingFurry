package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.Color;
import com.mygdx.game.S;
import com.mygdx.game.mywidgets.MyLabel;
import com.mygdx.game.mywidgets.MyStage;


public class Renderizador {
    MyStage stage;

    MyLabel label, vida1, vida2;

    Mano mano;

    public Renderizador(MyStage stage){
        this.stage = stage;

        label = new MyLabel(Color.RED);
        vida1 = new MyLabel(Color.YELLOW);
        vida2 = new MyLabel(Color.YELLOW);

        stage.topLeft.addActor(vida1);
        stage.topRight.addActor(vida2);
        stage.middleCenter.addActor(label);
    }

    public void repartirCartas() {
        float delay = 0;

        // TODO: obtener la posicion en la que debe ir cada carta para que coincida con el deck
        float[] positions = mano.getCardsPositions();

        for(Carta carta: mano.cartaList){
            float x = positions[mano.cartaList.indexOf(carta)];
            float y = mano.getY()+3;

            carta.accionRepartir(x, y, delay);

            delay += 0.5f;

            stage.addActor(carta);
        }
    }

    public void touched(Carta carta){
        S.juego.jugar(carta);
        carta.accionTirar(64, 36);
    }

    public void ponerMano(Mano mano) {
        this.mano = mano;
        stage.bottomCenter.addActor(mano);
    }

    public void mostrarMensaje(String text){
        label.setText(text);
    }

    public void mostrarVidas(int v1, int v2){
        vida1.setText(String.valueOf(v1));
        vida2.setText(String.valueOf(v2));
    }
}
