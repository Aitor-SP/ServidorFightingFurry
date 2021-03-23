package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.company.model.Mensaje;
import com.mygdx.game.Assets;
import com.mygdx.game.MyGdxGame;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mano extends Actor {

    List<Carta> cartaList;
    TextureRegion texture = Assets.getTexture("deck");

    Mano(List<Carta> cartaList) {
        this.cartaList = cartaList;

        setSize(24*3+4, 34);
        setPosition((MyGdxGame.WIDTH-texture.getRegionWidth())/2, 0);
    }

    Mensaje.Mano toMensaje(){
        return new Mensaje.Mano(cartaList.stream().map(Carta::toMensaje).toArray(Mensaje.Carta[]::new));
    }

    static Mano fromMensaje(Mensaje.Mano mano){
        return new Mano(Arrays.stream(mano.cartaList).map(Carta::fromMensaje).collect(Collectors.toList()));
    }

    float[] getCardsPositions(){
        return new float[]{getX()+1, getX()+25+1, getX()+25*2+1};
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        super.draw(batch, parentAlpha);
    }

    @Override
    public String toString() {
        return "Mano{ cartas=" + Arrays.toString(cartaList.toArray()) + '}';
    }
}
