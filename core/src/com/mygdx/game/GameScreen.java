package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.gameplay.GameMaster;

public class GameScreen extends BaseScreen {

	GameMaster gameMaster;

	public GameScreen(MyGdxGame game) {
		super(game);
	}

	@Override
	public void show () {

		gameMaster = new GameMaster(stage);
		gameMaster.start();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0.7f, 0.54f, 0.87f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.getBatch().setProjectionMatrix(game.camera.combined);
		stage.act();
		stage.draw();
	}
}
