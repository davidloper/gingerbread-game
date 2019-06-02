package com.mygdx.game.game_objects.falling_objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.managers.ImageManager;

abstract public class FallingObject {

    protected Sprite sprite;
    protected Body body;
    protected Gingerbread game;
    protected static final Class box2dUserData = FallingObject.class;

    public FallingObject(Gingerbread game){
        this.game = game;
    }

    public Body getBody(){
        return body;
    }

    abstract public void draw(SpriteBatch sb);
    abstract protected void setSprite();
    abstract protected void setBody();

}
