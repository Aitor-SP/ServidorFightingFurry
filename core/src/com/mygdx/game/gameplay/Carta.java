package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.company.model.Mensaje;
import com.mygdx.game.Assets;
import com.mygdx.game.Config;
import com.mygdx.game.S;
import com.mygdx.game.mywidgets.MyActor;

public class Carta extends MyActor {
    String tipo;
    public int valor;

    Carta(String tipo, int valor) {
        this.tipo = tipo;
        this.valor = valor;

        setSize(24,32);
        setOrigin(Align.center);
        setPosition(0, 0);

        animation = Assets.getAnimation(tipo, 0.3f, Animation.PlayMode.LOOP);

        addListener(() -> S.renderizador.touched(Carta.this));
    }

    static Carta fromMensaje(Mensaje.Carta carta){
        return new Carta(carta.tipo, carta.valor);
    }

    Mensaje.Carta toMensaje(){
        return new Mensaje.Carta(tipo, valor);
    }


    public void accionRepartir(float x, float y, float delay){
        setPosition(Config.WIDTH/2, Config.HEIGHT);
        addAction(
            Actions.sequence(
                Actions.delay(delay),
                Actions.parallel(
                    Actions.rotateBy(360*3, 0.9f, Interpolation.fastSlow),
                    Actions.moveTo(x, y, 1, Interpolation.fastSlow)
                )
            )
        );
    }

    public void accionTirar(float x, float y) {
        addAction(
            Actions.sequence(
                Actions.parallel(
                    Actions.rotateBy(40, 0.4f, Interpolation.fastSlow),
                    Actions.moveTo(x/2, y/2, 0.4f, Interpolation.fastSlow)
                ),
                Actions.parallel(
                    Actions.rotateBy(-40, 0.3f, Interpolation.fastSlow),
                    Actions.moveTo(x, y, 0.3f, Interpolation.fastSlow)
                ),
                Actions.fadeOut(0.7f)
            )
        );
    }

    @Override
    public String toString() {
        return "Carta{ type=" + tipo + ", value=" + valor + '}';
    }
}