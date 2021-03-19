package com.mygdx.game;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public class MyImageButton extends ImageButton {

    interface OnClickListener {
        void onClick();
    }

    public MyImageButton(String image_up, String image_over, int x, int y, int w, int h, OnClickListener onClickListener) {
        super(new ImageButtonStyle());

        getStyle().up = Assets.getDrawable(image_up);
        getStyle().over = Assets.getDrawable(image_over);
        setPosition(x, y);
        setSize(w, h);
        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onClickListener.onClick();
                return true;
            }
        });
    }
}
