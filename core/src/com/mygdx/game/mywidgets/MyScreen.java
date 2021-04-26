package com.mygdx.game.mywidgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.MyGdxGame;

public class
MyScreen implements Screen {
    public final MyGdxGame game;
    public final MyStage stage;
    public final Table table;

    public MyScreen(MyGdxGame game){
        this.game = game;

        Gdx.input.setInputProcessor(stage = new MyStage(game.viewport));
        table = new Table();

    }
    public void setScreen(MyScreen screen){
        Gdx.input.setInputProcessor(screen.stage);
        game.setScreen(screen);
    }
    @Override public void show() {}
    @Override public void render(float delta) {
        Gdx.gl.glClearColor(0.7f, 0.54f, 0.87f, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().setProjectionMatrix(game.camera.combined);
        stage.act();
        stage.draw();
    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
