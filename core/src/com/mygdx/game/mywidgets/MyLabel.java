package com.mygdx.game.mywidgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

public class MyLabel extends Label {
    static LabelStyle labelStyle = new LabelStyle();
    static {
        labelStyle.font = new BitmapFont();
    }

    public MyLabel(float x, float y, Color color){
        super("", labelStyle);
        getStyle().fontColor = color;
        getStyle().font.getData().setScale(0.5f);

        setPosition(x, y);
    }
}
