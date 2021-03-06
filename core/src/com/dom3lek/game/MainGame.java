package com.dom3lek.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dom3lek.game.states.GameManager;
import com.dom3lek.game.states.MenuState;

public class MainGame extends ApplicationAdapter {
	public static final int WIDTH=480;
	public static final int HEIGHT=800;
	public static final String TITLE = "Flappy BirdModel";
	private GameManager gsm;
	SpriteBatch batch;
	Texture img;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		gsm = new GameManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
