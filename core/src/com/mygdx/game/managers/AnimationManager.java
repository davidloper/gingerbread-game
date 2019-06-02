package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
//import com.badlogic.gdx.graphics.g3d.model.Animation;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.game_objects.falling_objects.Player;

public class AnimationManager {
    public Animation<TextureRegion> walkLeftAnimation;
    public Animation<TextureRegion> walkRightAnimation;
    public Animation<TextureRegion> dieAnimation;

    public TextureAtlas atlas;


    public AnimationManager(Gingerbread game) {

        atlas = new TextureAtlas(Gdx.files.internal("images/spritesheets/gingerbread_animations/gingerbread_animations.atlas"));
        walkLeftAnimation = new Animation<TextureRegion>(0.033f,atlas.findRegions("walk_left/frame"), Animation.PlayMode.NORMAL);
        walkRightAnimation = new Animation<TextureRegion>(0.033f,atlas.findRegions("walk_right/frame"), Animation.PlayMode.NORMAL);
        dieAnimation = new Animation<TextureRegion>(0.033f,atlas.findRegions("top_collision/frame"), Animation.PlayMode.NORMAL);
    }

    public Animation<TextureRegion> getPlayerAnimation(Player.State state){

        switch (state){
            case MOVE_LEFT:
                return walkLeftAnimation;
            case MOVE_RIGHT:
                return walkRightAnimation;
            case DIE:
                return dieAnimation;
        }
        return null;
    }


}
