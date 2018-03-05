package com.dom3lek.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by dom3lek on 2018-01-17.
 */

public class BirdModel {
    private static final int GRAVITY = -15;
    private static final int MOVEMENT = 100;

    private Vector3 position;
    private Vector3 velocity;
    private Rectangle bounds;
    private Texture bird;
    private AnimationofBirdModel birdAnimation;
    private Texture texture;

    public BirdModel(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        bird = new Texture("bird.png");
        texture = new Texture("birdanimation.png");
        birdAnimation = new AnimationofBirdModel(new TextureRegion(texture),3,0.5f);
        bounds = new Rectangle(x, y, texture.getWidth() / 3, texture.getHeight());
    }
    public void update(float delta){
        birdAnimation.update(delta);
        if(position.y > 0) {
            velocity.add(0, GRAVITY, 0);
        }
        velocity.scl(delta);
        position.add(MOVEMENT * delta, velocity.y, 0);
        if(position.y < 0 ){
            position.y = 0;
        }
        velocity.scl(1/delta);
        bounds.setPosition(position.x, position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public TextureRegion getTexture() {
        return birdAnimation.getFrame();
    }
    public void jump(){
        velocity.y = 250;

    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void dispose(){
        bird.dispose();
    }
}
