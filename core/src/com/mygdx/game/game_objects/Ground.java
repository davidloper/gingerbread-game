package com.mygdx.game.game_objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.managers.Box2dCollisionManager;

public class Ground {

    Gingerbread game;
    Body body;

    public Ground(Gingerbread game) {
        this.game = game;
        createBody();
    }

    void createBody(){
        Vector2[] vertices = new Vector2[4];
        vertices[0] = new Vector2(0,0);
        vertices[1] = new Vector2(0,Gdx.graphics.getHeight() / game.PPM);
        vertices[2] = new Vector2(Gdx.graphics.getWidth() / game.PPM,Gdx.graphics.getHeight() / game.PPM);
        vertices[3] = new Vector2(Gdx.graphics.getWidth() / game.PPM,0);

        //bodydef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
//        bodyDef.position.set(Gdx.graphics.getWidth() / game.PPM,Gdx.graphics.getHeight() / game.PPM);
        bodyDef.position.set(0,0);

        body = game.world.createBody(bodyDef);

        // shape
        ChainShape chainShape = new ChainShape();
        chainShape.createLoop(vertices);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = chainShape;
//        fdef.filter.categoryBits = Box2dCollisionManager.GROUND_BIT;
//        fdef.filter.maskBits = Box2dCollisionManager.PLAYER_BIT;
        fdef.density = 1;

        body.createFixture(fdef);
        body.setFixedRotation(true);
    }
}
