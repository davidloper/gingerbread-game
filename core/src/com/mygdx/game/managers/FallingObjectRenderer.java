package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.game_objects.falling_objects.ButcherKnife;
import com.mygdx.game.game_objects.falling_objects.FallingObject;
import com.mygdx.game.game_objects.falling_objects.Knife;
import com.mygdx.game.game_objects.falling_objects.Pan;
import com.mygdx.game.game_objects.falling_objects.WineGlass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FallingObjectRenderer {

    Gingerbread game;
    List<FallingObject> objects;

    Map<String,Float> xRange;
    Map<String,Float> yRange;
    Random random;
    Vector2 randomGeneratedVector;

    private final String MIN = "min";
    private final String MAX = "max";
    private final float LOWER_X_POSITION_LIMIT = -1;

    private int numberOfObjectsDodged = 0;
    private int spawnNewObjectInterval = 1;

    public FallingObjectRenderer(Gingerbread game){
        this.game = game;
        setRange();
        random = new Random();
    }

    public void create(){

        objects = new ArrayList<FallingObject>();

        objects.add(spawnRandomObject());
        objects.add(spawnRandomObject());
    }

    public FallingObject spawnRandomObject(){

        int range = rangeGenerator(0,3);

        randomGeneratedVector = vector2PointGenerator();
        float x = randomGeneratedVector.x/game.PPM;
        float y = randomGeneratedVector.y/game.PPM;

        FallingObject object;
        switch (range){
            case 0:
                object = new Pan(game,x,y);
                break;
            case 1:
                object = new WineGlass(game,x,y);
                break;
            case 2:
                object = new Knife(game,x,y);
                break;
            case 3:
                object = new ButcherKnife(game,x,y);
                break;
            default:
                object = null;
        }
        return object;
    }


    public void draw(){

        for(FallingObject object : objects){
            object.draw(game.batch);
        }
    }


    public void setRange(){
        //set x range
        xRange = new HashMap<String,Float>();
        xRange.put(MIN,0f);
        xRange.put(MAX,(float)Gdx.graphics.getWidth());

        //set y range
        yRange = new HashMap<String,Float>();
        yRange.put(MIN,(float)Gdx.graphics.getHeight());
        yRange.put(MAX,(float)Gdx.graphics.getHeight() * 4);
    }


    public int rangeGenerator(float min, float max){
        return (int) (random.nextInt((int) (max - min)) + min);
    }

    public Vector2 vector2PointGenerator(){
        Vector2 vec = new Vector2();
        vec.x = rangeGenerator(xRange.get(MIN),xRange.get(MAX));
        vec.y = rangeGenerator(yRange.get(MIN),yRange.get(MAX));
//        vec.x = random.nextInt((int) (xRange.get(MAX) - xRange.get(MIN))) + xRange.get(MIN);
//        vec.y = random.nextInt((int) (yRange.get(MAX) - yRange.get(MIN))) + yRange.get(MIN);
        return vec;
    }


    public void repositionFallenObjects(){
        for(int i = 0; i < objects.size();i++){
            if(objects.get(i).getBody().getPosition().y < LOWER_X_POSITION_LIMIT){
                recycle(objects.get(i));
                numberOfObjectsDodged++;
                if(numberOfObjectsDodged % spawnNewObjectInterval == 0) objects.add(spawnRandomObject());
            }
        }
    }

    public void recycle(FallingObject obj){
        randomGeneratedVector = vector2PointGenerator();
        obj.getBody().setTransform(randomGeneratedVector.scl(1/game.PPM),0);
        obj.getBody().setLinearVelocity(0,0);
    }

    public int getNumberOfObjectsDodged(){
        return numberOfObjectsDodged;
    }
}

