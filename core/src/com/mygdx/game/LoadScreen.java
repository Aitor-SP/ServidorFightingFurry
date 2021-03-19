package com.mygdx.game;


public class LoadScreen extends  BaseScreen{

    public LoadScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        if(!game.assets.update()) return;

        setScreen(new GameScreen(game));
    }
}