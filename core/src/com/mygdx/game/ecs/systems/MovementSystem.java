package com.mygdx.game.ecs.systems;

import com.badlogic.ashley.core.ComponentMapper;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.mygdx.game.ecs.components.BodyComponent;
import com.mygdx.game.ecs.components.PositionComponent;
import com.mygdx.game.ecs.components.VelocityComponent;

//unused class
public class MovementSystem extends EntitySystem {
    private ImmutableArray<Entity> entities;

    private ComponentMapper<BodyComponent> bm = ComponentMapper.getFor(BodyComponent.class);
    private ComponentMapper<VelocityComponent> vm = ComponentMapper.getFor(VelocityComponent.class);

    public MovementSystem(){};

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(Family.all(BodyComponent.class,VelocityComponent.class).get());
    }

    @Override
    public void update(float deltaTime) {
        for(int i = 0; i < entities.size(); ++i){
            Entity entity = entities.get(i);
            BodyComponent body = bm.get(entity);
            VelocityComponent velocity = vm.get(entity);

            body.body.setLinearVelocity(velocity.x,velocity.y);
        }
    }
}
