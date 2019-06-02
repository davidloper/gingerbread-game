package com.mygdx.game.game_objects.falling_objects;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.ecs.components.BodyComponent;
import com.mygdx.game.ecs.components.VelocityComponent;
import com.mygdx.game.managers.AnimationManager;
import com.mygdx.game.managers.Box2dCollisionManager;

public class Player {

    Gingerbread game;
    Entity entity;
    Body body;
    Sprite sprite;
    float  width = 80;
    float height = 80;
    private BodyComponent bodyComponent;
    private VelocityComponent velocityComponent;
    private float stateTime = 0;

    private final float HORIZONTAL_VELOCITY = 3;

    public enum Move {
        LEFT,
        RIGHT
    }

    public static enum State{
        STANDING,MOVE_LEFT,MOVE_RIGHT,DIE
    }

    public State activeState = State.STANDING;

    public Player(Gingerbread game,float x,float y) {
        this.game = game;
        createBody(x,y);
        createEntity(x,y);
        sprite = new Sprite(game.imgManager.getRegion("gingerbread"));
    }

    public Player(Gingerbread game){
        this(game,0,0);
    }

    private void createEntity(float x, float y){
        entity = game.engine.createEntity();

        //body
        bodyComponent = game.engine.createComponent(BodyComponent.class);
        bodyComponent.body = body;
        entity.add(bodyComponent);

        //velocity
        velocityComponent = game.engine.createComponent(VelocityComponent.class);
        entity.add(velocityComponent);
        game.engine.addEntity(entity);
    }

    public void createBody(float x, float y){
        //bodydef
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(x + width / game.PPM / 2,y + height/ game.PPM / 2);

        body = game.world.createBody(bodyDef);

        // shape
        PolygonShape polygonShape = new PolygonShape();
        polygonShape.setAsBox(width / game.PPM /2,height/ game.PPM / 2);

        FixtureDef fdef = new FixtureDef();
//        fdef.filter.categoryBits = Box2dCollisionManager.PLAYER_BIT;
//        fdef.filter.maskBits = Box2dCollisionManager.GROUND_BIT;
        fdef.density = 1;
        fdef.shape = polygonShape;

        body.createFixture(fdef);
        body.setUserData(this);
        body.setFixedRotation(true);
    }

    public void draw(SpriteBatch sb){
        stateTime += Gdx.graphics.getDeltaTime();

        Sprite currentFrame;
        switch (activeState){
            case DIE:
            case MOVE_LEFT:
            case MOVE_RIGHT:
                Animation<TextureRegion> animation = game.animationManager.getPlayerAnimation(activeState);
                currentFrame = new Sprite(animation.getKeyFrame(stateTime,false));
                break;
            case STANDING:
                currentFrame = sprite;
                break;
            default:
                currentFrame = null;
        }
        sb.draw(currentFrame,body.getPosition().x - width/game.PPM / 2,body.getPosition().y  - height/game.PPM / 2,width/game.PPM,height/game.PPM);
    }

    public void move(Move move){
        switch (move){
            case LEFT:
                velocityComponent.x = -HORIZONTAL_VELOCITY;
                setState(State.MOVE_LEFT);
                break;
            case RIGHT:
                velocityComponent.x = HORIZONTAL_VELOCITY;
                setState(State.MOVE_RIGHT);
                break;
        }
    }

    public void setState(State state){
        stateTime = 0;
        activeState = state;
        if(state == State.STANDING) velocityComponent.x = 0;
        else if(state == State.DIE) game.end();
    }
}
