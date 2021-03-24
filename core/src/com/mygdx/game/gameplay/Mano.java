package com.mygdx.game.gameplay;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.company.model.Mensaje;
import com.mygdx.game.Assets;
import com.mygdx.game.mywidgets.MyActor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Mano extends MyActor {

    List<Carta> cartaList;

    Mano(List<Carta> cartaList) {
        this.cartaList = cartaList;

        setSize(24*3+4, 34);

        animation = Assets.getAnimation("deck", 0.3f, Animation.PlayMode.LOOP);
    }

    Mensaje.Mano toMensaje(){
        return new Mensaje.Mano(cartaList.stream().map(Carta::toMensaje).toArray(Mensaje.Carta[]::new));
    }

    static Mano fromMensaje(Mensaje.Mano mano){
        return new Mano(Arrays.stream(mano.cartaList).map(Carta::fromMensaje).collect(Collectors.toList()));
    }

    float[] getCardsPositions(){

        // TODO: obtener la posicion absoluta en pantalla (x y)
//        float x = localToParentCoordinates(new Vector2(getParent().getX(), getParent().getY())).x;
//        float x = getParent().getX();

        float x = 26;

        return new float[]{x+1, x+25+1, x+25*2+1};
    }


    @Override
    public String toString() {
        return "Mano{ cartas=" + Arrays.toString(cartaList.toArray()) + '}';
    }
}
