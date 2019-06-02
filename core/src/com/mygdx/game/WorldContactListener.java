package com.mygdx.game;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.game_objects.falling_objects.FallingObject;
import com.mygdx.game.game_objects.falling_objects.Player;

public class WorldContactListener implements ContactListener {

    Gingerbread game;

    public WorldContactListener(Gingerbread game){
        this.game = game;
    }

    @Override
    public void beginContact(Contact contact) {

        Body BodyA = contact.getFixtureA().getBody();
        Body BodyB = contact.getFixtureB().getBody();

        if(BodyA.getUserData() == game.player && BodyB.getUserData() == FallingObject.class){
            game.player.setState(Player.State.DIE);
        }else if(BodyB.getUserData() == game.player && BodyA.getUserData() == FallingObject.class){
            game.player.setState(Player.State.DIE);
        }
    }


    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
