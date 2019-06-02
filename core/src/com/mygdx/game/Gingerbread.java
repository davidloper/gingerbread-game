package com.mygdx.game;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.ecs.EcsEngine;
import com.mygdx.game.game_objects.Ground;
import com.mygdx.game.game_objects.falling_objects.Pan;
import com.mygdx.game.game_objects.falling_objects.Player;
import com.mygdx.game.managers.AnimationManager;
import com.mygdx.game.managers.FallingObjectRenderer;
import com.mygdx.game.managers.ImageManager;
import com.mygdx.game.managers.InputManager;
import com.mygdx.game.screens.Hud;

public class Gingerbread extends ApplicationAdapter {

	public static float PPM = 100;
    public SpriteBatch batch;
	public ImageManager imgManager;
    public World world;
	public Box2DDebugRenderer b2dr;
	public OrthographicCamera cam;
	public EcsEngine engine;


	//temp
	FallingObjectRenderer fallingObjectRenderer;
	public Player player;
	public InputManager inputManager;
	public AnimationManager animationManager;
	public Hud hud;
	private boolean isPaused = false;

	@Override
	public void create () {
	    engine = new EcsEngine();

		cam = new OrthographicCamera();
		cam.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());

		imgManager = new ImageManager();
		world = new World(new Vector2(0,-0.5f),true);
		world.setContactListener(new WorldContactListener(this));
		b2dr = new Box2DDebugRenderer();
		batch = new SpriteBatch();

		//objects
		fallingObjectRenderer = new FallingObjectRenderer(this);
		fallingObjectRenderer.create();
		player = new Player(this);
		new Ground(this);

		engine.startSystems();

		//input
		inputManager = new InputManager(this);
		Gdx.input.setInputProcessor(inputManager);

		animationManager = new AnimationManager(this);

		//hud
		hud = new Hud();
	}

	public void update(float dt){
		hud.updateScore(fallingObjectRenderer.getNumberOfObjectsDodged());
		fallingObjectRenderer.repositionFallenObjects();
		inputManager.handleInput();
		Matrix4 camProjectionMatrix = cam.combined.scl(PPM);
		batch.setProjectionMatrix(camProjectionMatrix);
		b2dr.render(world,camProjectionMatrix);
		engine.update(dt);
		cam.update();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		update(Gdx.graphics.getDeltaTime());

		hud.draw();
		batch.begin();
		player.draw(batch);
		fallingObjectRenderer.draw();
		batch.end();
		if(!isPaused)
		world.step(1/60f,6,2);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}

	public void end(){
		isPaused = true;
		hud.showEndGameText();
	}
}
