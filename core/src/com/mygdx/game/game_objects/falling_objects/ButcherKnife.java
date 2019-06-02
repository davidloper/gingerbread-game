package com.mygdx.game.game_objects.falling_objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.managers.Box2dCollisionManager;

public class ButcherKnife extends FallingObject{

    Vector2 initialPosition;
    float width = 100;
    float height = 100;

    public ButcherKnife(Gingerbread game,float x,float y) {
        super(game);
        initialPosition = new Vector2(x,y);
        setSprite();
        setBody();
    }

    @Override
    protected void setSprite() {
        sprite = new Sprite(game.imgManager.getRegion("butcher_knife"));
    }

    @Override
    protected void setBody() {
        //bodydef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(initialPosition);

        body = game.world.createBody(bodyDef);

        //circle shape
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width /game.PPM,height / game.PPM);

        FixtureDef fdef = new FixtureDef();
//        fdef.filter.categoryBits = Box2dCollisionManager.FALLING_OBJECT_BIT;
//        fdef.filter.maskBits = Box2dCollisionManager.FALLING_OBJECT_BIT;
        fdef.shape = polygonShape;
        fdef.density = 1;

        body.createFixture(fdef).setSensor(true);
        body.setUserData(box2dUserData);
        body.setFixedRotation(true);
    }

    @Override
    public void draw(SpriteBatch sb) {
        sb.draw(sprite,body.getPosition().x - width/game.PPM ,body.getPosition().y - height/game.PPM / 2,width * 2 /game.PPM,height/game.PPM);
    }
}
