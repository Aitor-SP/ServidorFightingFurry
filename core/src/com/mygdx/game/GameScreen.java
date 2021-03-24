package com.mygdx.game;

import com.mygdx.game.gameplay.Juego;
import com.mygdx.game.gameplay.Renderizador;
import com.mygdx.game.mywidgets.MyScreen;

public class GameScreen extends MyScreen {

	public GameScreen(MyGdxGame game) {
		super(game);
	}

	@Override
	public void show () {
		S.clienteWS = new ClienteWS();
		S.juego = new Juego();
		S.renderizador = new Renderizador(stage);

		S.clienteWS.conectar();
	}
}
