package com.portalsandtimemachines.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * 	This class contains the method for handling the dice its display etc.
 * 
 *  @author Team24 for CSE 360 Spring 2016
 *  @version 1.1 April 15,2016
 *
 */
public class Dice {
	private static final int FRAME_COLS = 6;
	private static final int FRAME_ROWS = 1;
	Animation rollAnimation;
    Texture diceSheet;
    TextureRegion[] rollFrames;
    TextureRegion currentFrame;
    float stateTime;
    boolean animate;
    @SuppressWarnings("unused")
	private Random rand;
    private float animationSpeed;
   
    /**
     * constructor used for creation of the dice object and 
     * displaying it on the board where it animates randomly.
     */
    Dice(){
    	rand = new Random();
    	diceSheet = new Texture("dice.png");
    	TextureRegion[][] temp = TextureRegion.split(diceSheet, 
    			diceSheet.getWidth() / FRAME_COLS, 
    			diceSheet.getHeight() / FRAME_ROWS);
    	rollFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
    	int index = 0;
    	for(int i = 0; i < FRAME_ROWS; i++){
    		for(int j = 0; j < FRAME_COLS; j++){
    			rollFrames[index++] = temp[i][j];
    		}
    	}
    	animationSpeed = 0.05f;
    	rollAnimation = new Animation(animationSpeed, rollFrames);
    	stateTime = 0f;
    	animate = true;
    }
    
    /**
     * Function is used for drawing the dice on the board
     * 
     * @param batch the dice object passed as param which is to be displayed.
     */
    
    void draw(SpriteBatch batch){
    	if(animate){
    		stateTime += Gdx.graphics.getDeltaTime();
//    		stateTime = rand.nextInt(5) * animationSpeed;
    		currentFrame = rollAnimation.getKeyFrame(stateTime, true);
    	}
        batch.draw(currentFrame, 
        		Gdx.graphics.getWidth()/10 - (diceSheet.getWidth() / FRAME_COLS),
        		Gdx.graphics.getHeight()/10 - (diceSheet.getHeight() / FRAME_COLS));
    }
    
    /**
     * Function used for displaying the number on the dice.
     * 
     * @param number the number that is to be displayed on the screen.
     */
    void showNumber(float number){
    	animate = false;
    	number -= 1;
    	number *= animationSpeed;
    	currentFrame = rollAnimation.getKeyFrame(number, false);
    }
    
    /**
     * Function used for reseting the animate variable.
     */
    void changeAnimate(){
    	animate = !animate;
    }
    
    /**
     * Function which returns the time which is used for the purpose of movement
     * of dice.
     * 
     * @return int returns the time for the purpose of moving the dice.
     */
    
    int getStateTimeAsIndex(){
    	return (int)stateTime % FRAME_COLS;
    }
}
