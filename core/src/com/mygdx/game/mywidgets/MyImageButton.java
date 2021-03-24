package com.mygdx.game.mywidgets;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.mygdx.game.Assets;

public class MyImageButton extends ImageButton {

    public interface OnClickListener {
        void onClick();
    }

    public MyImageButton(String image_up, String image_over, int x, int y, int w, int h, OnClickListener onClickListener) {
        this(image_up, image_over, w, h, onClickListener);
        setPosition(x, y);
    }

    public MyImageButton(String image_up, String image_over, int w, int h, OnClickListener onClickListener) {
        super(new ImageButtonStyle());

        getStyle().up = Assets.getDrawable(image_up);
        getStyle().over = Assets.getDrawable(image_over);
        setSize(w, h);
        addListener(new InputListener(){
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                onClickListener.onClick();
                return true;
            }
        });
    }
}
