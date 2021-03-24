package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends Game {
	public Assets assets;

	public OrthographicCamera camera;
	public Viewport viewport;

	@Override
	public void create () {
		camera = new OrthographicCamera();
		camera.position.set(Config.WIDTH /2, Config.HEIGHT /2, 0);
		viewport = new FitViewport(Config.WIDTH, Config.HEIGHT, camera);
		viewport.apply();

		assets = new Assets();
		assets.load();

		setScreen(new LoadScreen(MyGdxGame.this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0f, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width ,height);
		super.resize(width, height);
	}
}