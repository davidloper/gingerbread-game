package com.mygdx.game.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Pool;

public class BodyComponent  implements Component, Pool.Poolable  {

    public Body body;

    @Override
    public void reset() {
        body = null;
    }
}
