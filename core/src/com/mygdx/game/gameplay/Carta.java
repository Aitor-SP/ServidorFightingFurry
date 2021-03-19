package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.Align;
import com.mygdx.game.Assets;

public class Carta extends Actor {
    enum Type {
        A,B,C,D,E,F,G;
    }

    Type type;
    Jugador jugador;
    public int daño;


    Animation<TextureRegion> animation;
    float stateTime = 0;


    Carta(Type type) {
        this.type = type;
    }

    void initRender(float x, float y, GameRenderer gameRenderer){
        setSize(24,32);
        setOrigin(Align.center);
        setPosition(x, y);

        setState();

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameRenderer.jugar(Carta.this);
                return false;
            }
        });
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

    public void repartir(int x, int y){
        addAction(
            Actions.sequence(
                Actions.parallel(
                        Actions.moveTo(x, y, 4, Interpolation.elasticOut)
                )
            )
        );
    }

    public static Carta getRandomCard(){
        return new Carta(Type.values()[MathUtils.random(Type.values().length)]);
    }

    void setState(){
        stateTime = 0;

        String animationName;
        switch (type){
            case A: default: animationName = "cardA"; break;
            case B: animationName = "cardB"; break;
            case C: animationName = "cardC"; break;
            case D: animationName = "cardD"; break;
            case E: animationName = "cardE"; break;
            case F: animationName = "cardF"; break;
            case G: animationName = "cardG"; break;
        }
        animation = Assets.getAnimation(animationName, 0.3f, Animation.PlayMode.LOOP);
    }
}