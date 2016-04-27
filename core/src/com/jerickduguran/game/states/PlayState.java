package com.jerickduguran.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.jerickduguran.game.FlappyBurd;
import com.jerickduguran.game.sprites.Bird;
import com.jerickduguran.game.sprites.Tube;


/**
 * Created by Jerick on 4/26/2016.
 */
public class PlayState extends State {

    private static final int TUBE_SPACING = 125;
    private static final int TUBE_COUNT   = 4;
    private static final int GROUND_Y_OFFSET   = -30;

    private Bird bird;
    private Texture bg;
    private Array<Tube> tubes;

    private Texture ground;
    private Vector2 groundPosition1,groundPosition2;
    private Music ouch;


    public PlayState(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50,100);
        camera.setToOrtho(false, FlappyBurd.WIDTH / 2,FlappyBurd.HEIGHT / 2);
        bg = new Texture("bg.jpg");
        ground = new Texture("ground.png");
        groundPosition1 = new Vector2(camera.position.x - (camera.viewportWidth / 2),GROUND_Y_OFFSET);
        groundPosition2 = new Vector2(camera.position.x - (camera.viewportWidth / 2) + ground.getWidth(),GROUND_Y_OFFSET);
        tubes = new Array<Tube>();
        ouch   = Gdx.audio.newMusic(Gdx.files.internal("muning.mp3"));
        for(int i=1;i <= TUBE_COUNT;i++){
            tubes.add(new Tube(i* (TUBE_SPACING + Tube.TUBE_WIDTH)));
        }
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            bird.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateGround();
        bird.update(dt);
        camera.position.x = bird.getPosition().x + 80;
        for(int i=0; i < tubes.size;i++){
            Tube tube = tubes.get(i);
            if(camera.position.x - (camera.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + (tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT);
            }

            if(tube.collides(bird.getBounds())){
                gsm.set(new PlayState(gsm)); 
            }
        }

        if(bird.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET){
            gsm.set(new PlayState(gsm));
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(bg, camera.position.x - (camera.viewportWidth / 2), 0);
        sb.draw(bird.getTexture(), bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes){
            sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
            sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);
         }
        sb.draw(ground, groundPosition1.x, groundPosition1.y);
        sb.draw(ground,groundPosition2.x,groundPosition2.y);
        sb.end();
    }

    @Override
    public void dispose() {
        bg.dispose();
        bird.dispose();
        ground.dispose();
        for(Tube tube : tubes)
            tube.dispose();

    }

    private void updateGround()
    {
        if((camera.position.x  - (camera.viewportHeight / 2)) > groundPosition1 .x + ground.getWidth()){
            groundPosition1.add(ground.getWidth() * 2,0);
        }
        if((camera.position.x  - (camera.viewportHeight / 2)) > groundPosition2 .x + ground.getWidth()){
            groundPosition2.add(ground.getWidth() * 2,0);
        }
    }
}
