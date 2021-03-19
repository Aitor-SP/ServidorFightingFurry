package com.mygdx.game.gameplay;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class GameRenderer {
    Stage stage;
    GameMaster gameMaster;

    GameRenderer(Stage stage, GameMaster gameMaster){
        this.stage = stage;
        this.gameMaster = gameMaster;
    }

    public void repartirCartaAJugador(Jugador jugador, Carta carta) {
        carta.initRender(0,0,this);

        stage.addActor(carta);
    }


    public void jugar(Carta carta){
        gameMaster.jugar(carta);
    }

    public void ponerMano(Mano mano) {
        stage.addActor(mano);
    }
}
