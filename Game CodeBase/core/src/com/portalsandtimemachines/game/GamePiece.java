package com.portalsandtimemachines.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GamePiece {
	public Sprite sprite;
	public int owner;
	public boolean moving;
	public float moveSpeed;
	
	private Vector2 destination;
	
	public GamePiece(int playerNumber, Texture texture, Vector2 position){
		owner = playerNumber;
		sprite  = new Sprite(texture);
		sprite.setOriginCenter();
		sprite.setSize(32, 32);
		sprite.setPosition(position.x, position.y);
		moveSpeed = 500;
		moving = false;
	}
	
	public void draw(SpriteBatch batch){
		if(moving){
			move(Gdx.graphics.getDeltaTime());
		}
		sprite.setOriginCenter();
		sprite.draw(batch);
	}
	
	public void moveToPosition(Vector2 newPosition){
		destination = newPosition;
		moving = true;
	}
	
	private void move(float deltaTime){
		float currentX = sprite.getX();
		float currentY = sprite.getY();
		Vector2 transform = new Vector2(currentX, currentY);
		
		if(transform.dst(destination) <= 5){
			sprite.setPosition(destination.x, destination.y);
			moving = false;
//			System.out.println("Move ended");
		}
		else{
			Vector2 direction = transform.sub(destination).nor();
//			sprite.setPosition(currentX - direction.x * moveSpeed * deltaTime, currentY - direction.y * moveSpeed * deltaTime);
			sprite.translate(-direction.x * moveSpeed * deltaTime, -direction.y * moveSpeed * deltaTime);
		}
	}
}