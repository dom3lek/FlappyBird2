package com.dom3lek.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.dom3lek.game.MainGame;
import com.dom3lek.game.sprites.BirdModel;
import com.dom3lek.game.sprites.TubeModel;

/**
 * Created by dom3lek on 2018-01-17.
 */

public class PlayState extends State {
    private static final int TUBE_SPACE = 125;
    private static final int TUBE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -40;
    private BirdModel birdModel;
    private Texture bg;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;


    private Array<TubeModel> tubes;

    protected PlayState(GameManager gsm) {
        super(gsm);
        birdModel = new BirdModel(50, 300);
        camera.setToOrtho(false, MainGame.WIDTH/2, MainGame.HEIGHT / 2);
        bg = new Texture("bg.png");
        ground = new Texture("ground.png");
        groundPos1 = new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET);
        groundPos2 = new Vector2((camera.position.x - camera.viewportWidth / 2)+ ground.getWidth(), GROUND_Y_OFFSET );


        tubes = new Array<TubeModel>();

        for(int i =1; i <= TUBE_COUNT; i++){
            tubes.add(new TubeModel(i * (TUBE_SPACE + TubeModel.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.justTouched()) {

            birdModel.jump();
        }
    }

    @Override
    public void update(float delta) {
        handleInput();
        updateGround();
        birdModel.update(delta);
        camera.position.x = birdModel.getPosition().x + 80;

        for(int i =0; i < tubes.size; i++){
            TubeModel tube = tubes.get(i);
            if(camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((TubeModel.TUBE_WIDTH + TUBE_SPACE) * TUBE_COUNT));
            }
            if(tube.collides(birdModel.getBounds())){
                gsm.set(new PlayState(gsm));
            }
        }
        if(birdModel.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(birdModel.getTexture(), birdModel.getPosition().x, birdModel.getPosition().y);
        for(TubeModel tube : tubes) {
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
        }
        sb.draw(ground, groundPos1.x, groundPos1.y);
        sb.draw(ground, groundPos2.x, groundPos2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        birdModel.dispose();
        ground.dispose();
        for(TubeModel tube: tubes){
            tube.dispose();
        }
    }
    public void updateGround(){
        if(camera.position.x - (camera.viewportWidth / 2) > groundPos1.x + ground.getWidth()){
            groundPos1.add(ground.getWidth() * 2, 0);
        }
        if(camera.position.x - (camera.viewportWidth / 2) > groundPos2.x + ground.getWidth()){
            groundPos2.add(ground.getWidth() * 2, 0);
        }
    }
}
