package com.jerickduguran.game.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import javax.sound.midi.Soundbank;

/**
 * Created by Jerick on 4/26/2016.
 */
public class Bird {

    private final static int MOVEMENT = 100;
    private static final int GRAVITY = -15;
    private Vector3 position;
    private Vector3 velocity;
    private Texture bird;
    private Rectangle bounds;
    private Sound flap;

    public Bird(int x, int y){

        position = new Vector3(x,y,0);
        velocity = new Vector3(0,0,0);
        bird     = new Texture("bird.png");
        bounds = new Rectangle(x,y,bird.getWidth(),bird.getHeight());
        flap   = Gdx.audio.newSound(Gdx.files.internal("baril.ogg"));
    }

    public void update(float dt){
        if(position.y > 0){
            velocity.add(0,GRAVITY,0);
        }
        velocity.scl(dt);

        position.add(MOVEMENT * dt, velocity.y, 0);
        if(position.y < 0){
            position.y = 0;
        }
        velocity.scl(1/dt);

        bounds.setPosition(position.x,position.y);
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return bird;
    }

    public void jump(){
        velocity.y = 250;
        flap.play();
    }

    public Rectangle getBounds()
    {
        return bounds;
    }

    public void dispose()
    {
        bird.dispose();
        flap.dispose();
    }
}
