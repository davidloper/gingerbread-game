package com.mygdx.game.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;
import com.mygdx.game.ecs.systems.MovementSystem;

public class EcsEngine extends PooledEngine {
    public EcsEngine() {
    }

    public void startSystems(){
        MovementSystem movementSystem = new MovementSystem();
        addSystem(movementSystem);
    }
}
