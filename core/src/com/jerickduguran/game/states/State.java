package com.jerickduguran.game.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Jerick on 4/26/2016.
 */
public abstract  class State {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected  GameStateManager gsm;

    public State(GameStateManager gsm){
        this.gsm = gsm;
        this.camera = new OrthographicCamera();
        this.mouse = new Vector3();

    }

    protected abstract  void handleInput();
    public abstract void update(float dt);
    public abstract void render(SpriteBatch b);
    public abstract void dispose();

}