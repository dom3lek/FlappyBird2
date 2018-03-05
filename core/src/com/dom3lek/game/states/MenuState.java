package com.dom3lek.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dom3lek.game.MainGame;

/**
 * Created by dom3lek on 2018-01-17.
 */

public class MenuState extends State{
    private Texture bgTexture;
    private Texture playButton;

    public MenuState(GameManager gsm) {
        super(gsm);
        bgTexture = new Texture("bg.png");
        playButton = new Texture("playbtn.png");

    }

    @Override
    public void handleInput() {
            if(Gdx.input.justTouched()){
                gsm.set(new PlayState(gsm));

            }
    }

    @Override
    public void update(float delta) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        sb.draw(bgTexture,0,0, MainGame.WIDTH, MainGame.HEIGHT);
        sb.draw(playButton,(MainGame.WIDTH/2)-(playButton.getWidth() / 2), MainGame.HEIGHT /2);
        sb.end();
    }

    @Override
    public void dispose() {
        bgTexture.dispose();
        playButton.dispose();
    }

}
