package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.company.model.Mensaje;
import com.mygdx.game.Assets;
import com.mygdx.game.GameScreen;
import com.mygdx.game.MyGdxGame;

public class Carta extends Actor {
    String type;
    public int value;

    Animation<TextureRegion> animation;
    float stateTime;

    Carta(String type, int value) {
        this.type = type;
        this.value = value;

        setSize(24,32);
        setOrigin(Align.center);
        setPosition(0, 0);

        String animationName;
        switch (type){
            case "defensa": default: animationName = "card_d"; break;
            case "xdefensa": animationName = "card_xd"; break;
            case "ataque": animationName = "card_a"; break;
            case "xataque": animationName = "card_xa"; break;
        }

        animation = Assets.getAnimation(animationName, 0.3f, Animation.PlayMode.LOOP);

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                GameScreen.gameRenderer.touched(Carta.this);
                return false;
            }
        });
    }

    static Carta fromMensaje(Mensaje.Carta carta){
        return new Carta(carta.tipo, carta.valor);
    }

    Mensaje.Carta toMensaje(){
        return new Mensaje.Carta(type ,value);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        stateTime += delta;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animation.getKeyFrame(stateTime), getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
    }

    public void accionRepartir(float x, float y, float delay){
        setPosition(MyGdxGame.WIDTH/2, MyGdxGame.HEIGHT);
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
        return "Carta{ type=" + type + ", value=" + value + '}';
    }
}