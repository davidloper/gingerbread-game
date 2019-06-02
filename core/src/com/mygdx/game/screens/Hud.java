package com.mygdx.game.screens;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Hud {

    private Stage stage;

    private Label scoreLabel;
    private Table table;

    public Hud(){
        stage = new Stage();
        table = createTable();
        addScore();
        stage.addActor(table);
    }

    public Table createTable(){
        Table table = new Table();
        table.setFillParent(true);
        table.setDebug(true);

        return table;
    }

    public void addScore(){
        BitmapFont font = new BitmapFont();
        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = font;

        scoreLabel = new Label("Score: " + scoreText(0),textStyle);
        scoreLabel.setFontScale(2f,2f);

        table.right().top();
        table.add(scoreLabel).pad(20);
    }

    public void draw(){
        stage.draw();
    }

    public void updateScore(int score){
        scoreLabel.setText(scoreText(score));
    }

    public String scoreText(int score){
        return "Score: " + String.format("%05d",score);
    }

    public void showEndGameText(){

        BitmapFont font = new BitmapFont();
        Label.LabelStyle textStyle = new Label.LabelStyle();
        textStyle.font = font;

        Label endGameLabel = new Label("Game Over",textStyle);
        scoreLabel.remove();
        table.center();
        table.add(endGameLabel);
    }

    public void dispose(){
        stage.dispose();
    }


}
