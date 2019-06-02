package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.mygdx.game.Gingerbread;
import com.mygdx.game.game_objects.falling_objects.Player;

public class InputManager extends InputAdapter {

    Gingerbread game;

    public InputManager(Gingerbread game){
        this.game = game;
    }

    @Override
    public boolean keyDown(int keycode) {

        if(keycode == Input.Keys.LEFT)
            this.game.player.move(Player.Move.LEFT);

        if(keycode == Input.Keys.RIGHT)
            this.game.player.move(Player.Move.RIGHT);

        return false;
//        return super.keyDown(keycode);
    }

    @Override
    public boolean keyUp(int keycode){
        this.game.player.setState(Player.State.STANDING);
        return false;
    }

    public void handleInput(){

    }
}
