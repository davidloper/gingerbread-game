package com.mygdx.game.game_objects.falling_objects;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.managers.Box2dCollisionManager;

public class WineGlass extends FallingObject{

    Vector2 initialPosition;
    float width = 50;
    float height = 100;

    public WineGlass(Gingerbread game,float x, float y) {

        super(game);
        initialPosition = new Vector2(x,y);
        setSprite();
        setBody();
    }

    public WineGlass(Gingerbread game){
        this(game,0,0);
    }

    @Override
    protected void setSprite() {
        sprite = new Sprite(game.imgManager.getRegion("wine_glass"));
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
        polygonShape.setAsBox(width /game.PPM / 2,height / game.PPM / 2);

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
    public void draw(SpriteBatch sb){
        sb.draw(sprite,body.getPosition().x - width/game.PPM ,body.getPosition().y - height/game.PPM / 2,width * 2 /game.PPM,height/game.PPM);
    }
}
