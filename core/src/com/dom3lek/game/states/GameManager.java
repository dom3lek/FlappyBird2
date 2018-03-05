package com.dom3lek.game.states;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Stack;

/**
 * Created by dom3  lek on 2018-01-17.
 */

public class GameManager {

    private Stack<State> GameStates;

    public GameManager(){
        GameStates = new Stack<State>();

    }
    public void push(State state){
        GameStates.push(state);
    }
    public void pop(){
        GameStates.pop().dispose();
    }
    public void set(State state){
        GameStates.pop().dispose();
        GameStates.push(state);
    }
    public void update(float delta){
        GameStates.peek().update(delta);
    }
    public void render(SpriteBatch sb){
        GameStates.peek().render(sb);
    }
}
