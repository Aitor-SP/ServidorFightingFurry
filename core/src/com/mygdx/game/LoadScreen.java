package com.mygdx.game;


import com.mygdx.game.mywidgets.MyScreen;

public class LoadScreen extends MyScreen {

    public LoadScreen(MyGdxGame game) {
        super(game);
    }

    @Override
    public void render(float delta) {
        super.render(delta);

        if(!game.assets.update()) return;

        setScreen(new MenuScreen(game));
//        setScreen(new GameScreen(game));
    }
}