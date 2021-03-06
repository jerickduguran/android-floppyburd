package com.jerickduguran.game.sprites;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Jerick on 4/27/2016.
 */
public class Animation {

    private Array<TextureRegion> frames;
    private float maxFrameTime;
    private float currentFrameTime;
    private int frameCount;
    private int frame;

    public Animation(TextureRegion region, int frameCount,float cycleTime)
    {
        frames = new Array<TextureRegion>();
        int frameWidth  = region.getRegionWidth() / frameCount;
        for(int i = 0;i < frameCount;i++){
            frames.add(new TextureRegion(region,i* frameWidth,0,frameWidth,region.getRegionHeight()));
        }
    }
}
