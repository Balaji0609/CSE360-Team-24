package com.portalsandtimemachines.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * This is the GamePiece class which is used for setting up the game.
 * It holds all the functions for setting up the pieces on the board,
 * method which is used for moving the pieces.
 *  
 *  @author Team24 for CSE 360 Spring 2016
 *  @version 1.1 April 15,2016
 */

public class GamePiece {
	public Sprite sprite; 				//used for rendering the images. 
	public int owner;
	public boolean moving;
	public float moveSpeed;
	public int locationIndex;
	
	public boolean show;
	
	private Vector2 destination;
	private Vector2 secondDestination;
	
	/**
	 * Function used for setting up the game pieces i.e the portals, players etc.
	 * 
	 * @param playerNumber   player who is playing.
	 * @param texture		the piece that is being set up.
	 * @param position		used for positioning in the board.
	 */
	@SuppressWarnings("static-access")
	public GamePiece(int playerNumber, Texture texture, Vector2 position){  // this function is used for setting up the game piece on the board.
		owner = playerNumber;
		sprite  = new Sprite(texture);
		sprite.setOriginCenter();
		sprite.setSize(32, 32);
		sprite.setPosition(position.x, position.y);
		moveSpeed = 250;
		moving = false;
		destination = destination.Zero;
		secondDestination = secondDestination.Zero; 
		locationIndex = 0;
		show = true;
	}
	
	/**
	 * Function for drawing the sprite's on the board.
	 * 
	 * @param batch  the sprite that is being chosen to draw.
	 */
	public void draw(SpriteBatch batch){
		if(moving){
			move(Gdx.graphics.getDeltaTime());
		}
		sprite.setOriginCenter();
		sprite.draw(batch);
	}
	
	/**
	 * Function setting the new location index.
	 * 
	 * @param newIndex  index that is to be set up
	 */
	public void setLocationIndex(int newIndex){
		locationIndex = newIndex;
	}
	
	/**
	 * Function for setting the destination position.
	 * 
	 * @param newPosition position to which to be moved.
	 */
	public void moveToPosition(Vector2 newPosition){
		destination = newPosition;
		moving = true;
	}
	
	/**
	 * Function which is used for moving the pawn on the board.
	 * 
	 * @param deltaTime parameter used for identifying translation function.
	 */
	@SuppressWarnings("static-access")
	private void move(float deltaTime){								// this function is used for moving the pawn.
		float currentX = sprite.getX();
		float currentY = sprite.getY();
		Vector2 transform = new Vector2(currentX, currentY);
		
		if(transform.dst(destination) <= 5){
			sprite.setPosition(destination.x, destination.y);
			moving = false;
			destination = destination.Zero;
			if(secondDestination != secondDestination.Zero){
				destination = secondDestination;
				moving = true;
				secondDestination = secondDestination.Zero;
			}
		}
		else{
			Vector2 direction = transform.sub(destination).nor();
			sprite.translate(-direction.x * moveSpeed * deltaTime, -direction.y * moveSpeed * deltaTime);
		}
	}

	/**
	 * Function to show the relative movement after reaching a position when a portal
	 * or when time machine condition failed to the user.
	 * 
	 * @param secondaryPosition function to be moved when condition not satisfied.
	 */
	public void secondaryMove(Vector2 secondaryPosition){                        // This function is used for moving the pawn when it hit a portal or in case of time machine fail.
		secondDestination = secondaryPosition;
	}
	

}
