package com.mygdx.game.mywidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.MyGdxGame;

public class MyScreen implements Screen {
    public final MyGdxGame game;
    public final Stage stage;

    public MyScreen(MyGdxGame game){
        this.game = game;
        Gdx.input.setInputProcessor(stage = new Stage(game.viewport));
    }
    public void setScreen(MyScreen screen){
        Gdx.input.setInputProcessor(screen.stage);
        game.setScreen(screen);
    }
    @Override public void show() {}
    @Override public void render(float delta) {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
