package com.mygdx.game.game_objects.falling_objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.managers.Box2dCollisionManager;
import com.mygdx.game.managers.ImageManager;

public class Pan extends FallingObject{

    Vector2 initialPosition;
    float width = 100;
    float height = 100;
    Vector2 circleCenter;

    public Pan(Gingerbread game,float x,float y){
        super(game);
        circleCenter = new Vector2(width/ 2.3f,height/2.6f).scl(1/game.PPM);
        initialPosition = new Vector2(x,y);
        setSprite();
        setBody();
    }


    public Pan(Gingerbread game){
        this(game,0,0);
    }

    @Override
    protected void setSprite() {
        sprite = new Sprite(game.imgManager.getRegion("pan"));
    }

    @Override
    protected void setBody() {
        //bodydef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(initialPosition);

        body = game.world.createBody(bodyDef);

        float density = 1;

        //circle shape
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(0.33f);
        circleShape.setPosition(circleCenter);
        FixtureDef fdef = new FixtureDef();
        fdef.density = density;
        fdef.shape = circleShape;
//        fdef.filter.categoryBits = Box2dCollisionManager.FALLING_OBJECT_BIT;
//        fdef.filter.maskBits = Box2dCollisionManager.FALLING_OBJECT_BIT;
        body.createFixture(circleShape,density).setSensor(true);

        //handle shape
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(0.5f,0.04f,new Vector2(circleCenter).scl(1.3f),(float) Math.toRadians(43));
        fdef = new FixtureDef();
        fdef.shape = polygonShape;
        fdef.density = density;
//        fdef.filter.categoryBits = Box2dCollisionManager.FALLING_OBJECT_BIT;
//        fdef.filter.maskBits = Box2dCollisionManager.FALLING_OBJECT_BIT ;
        body.setUserData(this);
        body.createFixture(fdef).setSensor(true);
        body.setUserData(box2dUserData);
        body.setFixedRotation(true);
    }

    public void draw(SpriteBatch sb){
        sb.draw(sprite,body.getPosition().x,body.getPosition().y,width/game.PPM,height/game.PPM);
    }
}
