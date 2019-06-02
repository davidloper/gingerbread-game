package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ImageManager {

    TextureAtlas atlas;

    public ImageManager() {
        atlas = new TextureAtlas(Gdx.files.internal("images/spritesheets/game_objects/game_objects.atlas"));
    }


    public TextureAtlas.AtlasRegion getRegion(String region){
        return atlas.findRegion(region);
    }
}
