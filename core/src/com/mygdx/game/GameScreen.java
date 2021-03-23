package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.gameplay.GameRenderer;
import com.mygdx.game.gameplay.Juego;
import com.mygdx.game.mywidgets.MyScreen;

public class GameScreen extends MyScreen {

	static Cliente cliente;
	public static GameRenderer gameRenderer;
	static Juego juego;

	public GameScreen(MyGdxGame game) {
		super(game);
	}

	@Override
	public void show () {
		cliente = new Cliente();
		gameRenderer = new GameRenderer(stage);
		juego = new Juego();

		cliente.juego = juego;
		juego.cliente = cliente;
		juego.gameRenderer = gameRenderer;
		gameRenderer.juego = juego;

		cliente.connect();
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
