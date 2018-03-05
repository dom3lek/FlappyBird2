package com.dom3lek.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.lang.reflect.Array;

/**
 * Created by dom3lek on 2018-01-17.
 */

public class AnimationofBirdModel {
    private com.badlogic.gdx.utils.Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public AnimationofBirdModel(TextureRegion  region, int frameCount, float cycleTime){
        frames = new com.badlogic.gdx.utils.Array<TextureRegion>();
        int frameWidth = region.getRegionWidth() / frameCount;
        for(int i=0; i<frameCount; i++){
            frames.add(new TextureRegion(region, i * frameWidth, 0, frameWidth, region.getRegionHeight()));
        }
        this.frameCount = frameCount;
        maxFrameTime = cycleTime / frameCount;
        frame = 0;
    }
    public void update (float delta){
        currentFrameTime += delta;
        if(currentFrameTime > maxFrameTime){
            frame++;
            currentFrameTime = 0;
        }
        if(frame >= frameCount){
            frame = 0;
        }
    }
    public TextureRegion getFrame(){
        return frames.get(frame);
    }
}
