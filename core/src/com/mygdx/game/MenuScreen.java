package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.mywidgets.MyImageButton;
import com.mygdx.game.mywidgets.MyScreen;

public class MenuScreen extends MyScreen {

    public MenuScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void show() {
        stage.middleCenter.addActor(new MyImageButton("button_start_up", "button_start_over", 30, 10, () -> {
            setScreen(new GameScreen(game));
        }));
        stage.middleCenter.addActor(new MyImageButton("button_quit_up", "button_quit_over", 30, 10, () -> {
            Gdx.app.exit();
        }));
    }
}
