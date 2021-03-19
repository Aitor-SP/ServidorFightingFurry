package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.mygdx.game.Assets;

import java.util.ArrayList;
import java.util.List;

public class Mano extends Group {

    enum Posicion { ARRIBA, ABAJO }

    List<Carta> cartas = new ArrayList<>();

    Posicion posicion;

    TextureRegion texture = Assets.getTexture("deck");

    Mano(){
        setSize(24*3+4, 34);
    }

    Carta añadir(Carta carta){
        cartas.add(carta);
        return carta;
    }

    void setPosicion(Posicion posicion){
        this.posicion = posicion;

        if(posicion == Posicion.ARRIBA){
            setPosition(40, 100);
        } else {
            setPosition(40, 0);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());

        super.draw(batch, parentAlpha);

    }
}
