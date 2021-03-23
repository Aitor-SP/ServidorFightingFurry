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
        stage.addActor(new MyImageButton("button_start_up", "button_start_over", 100, 100, 60, 30, () -> {
            setScreen(new GameScreen(game));
        }));

        stage.addActor(new MyImageButton("button_quit_up", "button_quit_over", 100, 50, 60, 30, () -> {
            Gdx.app.exit();
        }));
    }

    @Override
    public void render (float delta){
        stage.act();
        stage.draw();
    }
}
