package com.portalsandtimemachines.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
//import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.Random;

import javax.swing.*;

/**
 * This class holds all the functions that is used for the running the game.
 * 
 *  @author Team24 for CSE 360 Spring 2016
 *  @version 1.1 April 15,2016
 *  
 */
@SuppressWarnings("unused")
public class Game extends ApplicationAdapter{
	SpriteBatch batch;
	Texture boardBackground;
	Texture gamePieceTexture;
	Texture gamePieceTexture1;
	Texture portalTexture;
	Texture timemachineTexture;
	Texture bountyTexture;
	
	private Viewport viewport;
	OrthographicCamera camera;
	
	private Sprite boardSprite;
	
	private Sprite bountySprite1;
	private Sprite bountySprite2;
	private Sprite bountySprite3;
	
	private Sprite timemachineSprite1;
	private Sprite timemachineSprite2;
	private Sprite timemachineSprite3;
	
	private Sprite portalSprite1;
	private Sprite portalSprite2;
	private Sprite portalSprite3;
	private Sprite portalSprite4;
	private Sprite portalSprite5;
	private Sprite portalSprite6;
	private Sprite portalSprite7;
	private Sprite portalSprite8;
	
	private Dice dice;
	
//	DB Connect
	HashMap dbValues = new HashMap();
	DBGameConnect dbGame;

	private float boardSize;
	private float numberOfSpacesPerRow;
	private float windowWidth;
	private Array<Vector2> boardTransforms;
	private Array<GamePiece> gamePieces;
	
	private int index = 0;
	private int index1 = 0;
	
//	ShapeRenderer shapeRenderer;
	
	GamePiece gamePiece;
	GamePiece gamePiece1;
	
	
	Stage gamestage; 	
	Skin gameskin;
	Skin gameskinp;
	
	TextButton rollDice;
	TextButton playerOne;
	TextButton Pawn_One;
	TextButton playerTwo;
	TextButton Pawn_Two;
	TextButton useCard;
	
	String playername;
	GameBoard obj = new GameBoard();
	
	
	int[] temporary_PortalsPosition;
	int[] temporary_TimeMachinePosition;
	int[] bounty_positions;
	static int timeMachine_counter = 0;
	static int time_machine_flag = 0;
	static int final_pos = 0;

	static int timeMachine_counter1 = 0;
	static int time_machine_flag1 = 0;
	static int final_pos1 = 0;
	/**
	 * This function is used for the purpose of drawing the board and
	 * setting up other board pieces initially.
	 * 
	 *  @param none
	 */
	@Override
	public void create () {
		// Sprite batch to store all sprites before sending to GPU
		playername = JOptionPane.showInputDialog("Please enter your usename: ");
		
		
		while(playername.isEmpty())
		{
			playername = JOptionPane.showInputDialog("Please enter your username: ");
		}
		JOptionPane.showMessageDialog(null, "Hello " + playername + '!' + "\nWelcome to Portals & Time-Machines" );
		
		dbValues.put("pname",playername);
		
//		dbGame = new DBGameConnect();
//		dbGame.dbConnect(dbValues);
	
		batch = new SpriteBatch();
		gamestage = new Stage();
		gameskin = new Skin();
		gameskinp = new Skin();
		// Checkered background texture
		boardBackground = new Texture("10x10_checkered_board.png");
		gamePieceTexture = new Texture("Chess-Game.png");
		gamePieceTexture1 = new Texture("Green_piece.png");
		portalTexture = new Texture("brunswick-spiral-black-white.png");
		timemachineTexture = new Texture("Time-Machine.png");
		bountyTexture = new Texture("bounty.png");
		
		
		BitmapFont bfont=new BitmapFont();
//		bfont.scale(1);
		gameskin.add("default",bfont);
		
		Pixmap pixmap = new Pixmap(100, 50, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		gameskin.add("white", new Texture(pixmap));

		Gdx.input.setInputProcessor(gamestage);
        
		dice = new Dice();
		
        TextButtonStyle textButtonStyle = new TextButtonStyle();
        textButtonStyle.fontColor =  Color.BLACK;
		textButtonStyle.up = gameskin.newDrawable("white", Color.WHITE);
		textButtonStyle.down = gameskin.newDrawable("white", Color.WHITE);
		textButtonStyle.checked = gameskin.newDrawable("white", Color.WHITE);
		textButtonStyle.over = gameskin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.disabled = gameskin.newDrawable("white", Color.BLACK);

		
		textButtonStyle.font = gameskin.getFont("default");

		gameskin.add("default", textButtonStyle);
		
		rollDice = new TextButton("Roll Dice", gameskin);
		rollDice.setPosition(0, 0);
		

		
		Pawn_One = new TextButton("Pawn 1", gameskin);
		Pawn_One.setPosition(0, 350);
		
		Pawn_Two = new TextButton("Pawn 2", gameskin);
		Pawn_Two.setPosition(0, 250);
		
		playerOne = new TextButton("Player 1", gameskin);
		playerOne.setPosition(0, 700);
		
		playerTwo = new TextButton("Player 2", gameskin);
		playerTwo.setPosition(0, 600);
		playerTwo.setDisabled(true);
		
		useCard = new TextButton("Use Card",gameskin);
		useCard.setPosition(0, 150);
		
		gamestage.addActor(rollDice);
		gamestage.addActor(playerOne);
		gamestage.addActor(playerTwo);
		gamestage.addActor(Pawn_One);
		gamestage.addActor(Pawn_Two);
		gamestage.addActor(useCard);
		
	    // Camera to manage viewport and  view matrices
		camera = new OrthographicCamera();
		// Initialize camera to window size
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		// Initialize viewport size to camera size
		viewport = new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera);
		// helper variable for board size
		boardSize = camera.viewportHeight;
		numberOfSpacesPerRow = 10;
		windowWidth = camera.viewportWidth;

		// Sprite to help with board texture transformation
		boardSprite = new Sprite(boardBackground);
		boardSprite.setOriginCenter();
		boardSprite.setSize(boardSize, boardSize);
		boardSprite.setPosition(Gdx.graphics.getWidth()/2 - boardSize/2, 0); // 0 may need to be set as screenHeight/2 - boardsize/2

		timemachineSprite1 = new Sprite(timemachineTexture);
		timemachineSprite1.setOriginCenter();
		timemachineSprite1.setSize(32, 32);
		timemachineSprite1.setColor(0,0,0,0);
		
		timemachineSprite2 = new Sprite(timemachineTexture);
		timemachineSprite2.setOriginCenter();
		timemachineSprite2.setSize(32, 32);
		timemachineSprite2.setColor(0,0,0,0);
		
		timemachineSprite3 = new Sprite(timemachineTexture);
		timemachineSprite3.setOriginCenter();
		timemachineSprite3.setSize(32, 32);
		timemachineSprite3.setColor(0,0,0,0);
		
		portalSprite1 = new Sprite(portalTexture);
		portalSprite1.setOriginCenter();
		portalSprite1.setSize(32, 32);
		
		portalSprite2 = new Sprite(portalTexture);
		portalSprite2.setOriginCenter();
		portalSprite2.setSize(32, 32);
		
		portalSprite3 = new Sprite(portalTexture);
		portalSprite3.setOriginCenter();
		portalSprite3.setSize(32, 32);
		
		portalSprite4 = new Sprite(portalTexture);
		portalSprite4.setOriginCenter();
		portalSprite4.setSize(32, 32);
		
		portalSprite5 = new Sprite(portalTexture);
		portalSprite5.setOriginCenter();
		portalSprite5.setSize(32, 32);
		
		portalSprite6 = new Sprite(portalTexture);
		portalSprite6.setOriginCenter();
		portalSprite6.setSize(32, 32);
		
		portalSprite7 = new Sprite(portalTexture);
		portalSprite7.setOriginCenter();
		portalSprite7.setSize(32, 32);
		
		portalSprite8 = new Sprite(portalTexture);
		portalSprite8.setOriginCenter();
		portalSprite8.setSize(32, 32);		
		
		
		bountySprite1 = new Sprite(bountyTexture);
		bountySprite1.setOriginCenter();
		bountySprite1.setSize(32, 32);
		
		bountySprite2 = new Sprite(bountyTexture);
		bountySprite2.setOriginCenter();
		bountySprite2.setSize(32, 32);
		
		bountySprite3 = new Sprite(bountyTexture);
		bountySprite3.setOriginCenter();
		bountySprite3.setSize(32, 32);
		// Used to draw shapes on screen 
//		shapeRenderer = new ShapeRenderer();
		
		// ArrayList to hold centers of the spaces
		boardTransforms = new Array<Vector2>(100);
		boardTransforms = calculateTransforms(boardSize, numberOfSpacesPerRow, windowWidth);
		
		// Draw portalSprite at the 44th space
		// Simply for testing purposes.
		
		obj.init();
		obj.init_time_machine();
		obj.init_bounty();
		
		temporary_PortalsPosition = obj.portal_positions(); 
		int i_index = 0;
		temporary_TimeMachinePosition = obj.timeMachine_positions();
		int j_index = 0;
		bounty_positions = obj.bounty_positions();
		int k_index = 0;
		
		timemachineSprite1.setPosition(boardTransforms.get(temporary_TimeMachinePosition[j_index] ).x - 32, boardTransforms.get(temporary_TimeMachinePosition[j_index]).y);
		j_index++;
		
		timemachineSprite2.setPosition(boardTransforms.get(temporary_TimeMachinePosition[j_index] ).x - 32, boardTransforms.get(temporary_TimeMachinePosition[j_index]).y);
		j_index++;
		
		timemachineSprite3.setPosition(boardTransforms.get(temporary_TimeMachinePosition[j_index] ).x - 32, boardTransforms.get(temporary_TimeMachinePosition[j_index]).y);
		j_index++;
		
		portalSprite1.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite2.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite3.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite4.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index]).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite5.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite6.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite7.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index] ).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		portalSprite8.setPosition(boardTransforms.get(temporary_PortalsPosition[i_index]).x - 32, boardTransforms.get(temporary_PortalsPosition[i_index]).y);
		i_index++;
		
		bountySprite1.setPosition(boardTransforms.get(bounty_positions[k_index]).x - 32, boardTransforms.get(bounty_positions[k_index]).y);
		k_index++;
		
		bountySprite2.setPosition(boardTransforms.get(bounty_positions[k_index]).x - 32, boardTransforms.get(bounty_positions[k_index]).y);
		k_index++;
		
		bountySprite3.setPosition(boardTransforms.get(bounty_positions[k_index]).x - 32, boardTransforms.get(bounty_positions[k_index]).y);
		k_index++;
		
		
		gamePiece = new GamePiece(0, gamePieceTexture, boardTransforms.get(0));
		gamePiece1 = new GamePiece(1, gamePieceTexture1, boardTransforms.get(0));
		
		rollDice.addListener(new ChangeListener() {
			public void changed (ChangeEvent event, Actor actor) {
				
				int temp = obj.roll_die();
				dice.showNumber(temp);
				

				moving_piece(temp);
				if(index > 98) {
					JOptionPane.showMessageDialog(null,"Congrats " + playername + "! You Won!!");
//				rollDice.setText("Starting new game");
					Gdx.app.exit();
				}
			}
		});
	}
	
	
	/**
	 * This function handles the movement of the pawn in a proper order on the board.
	 * 
	 * @param boardSize		size of the board is passed.
	 * @param numberOfSpacesPerRow	passing the number of spaces in the row.
	 * @param windowWidth	passing the window width for manipulation as parameter.
	 * @return	Array<Vector2> which is the movement that it should follow.
	 */
	public Array<Vector2> calculateTransforms(float boardSize, float numberOfSpacesPerRow, float windowWidth){
		Array<Vector2> temporaryArray = new Array<Vector2>(100);
		for(int i = 0; i < 10; i++){ // Horizontal movement
			if(i % 2 == 0){
				for(int j = 0; j < 10; j++){ // Vertical movement
					//Vector2 transform = new Vector2(768/10 * i, 768/10 * j);
					temporaryArray.add(new Vector2(boardSize/numberOfSpacesPerRow * j + ((boardSize/numberOfSpacesPerRow)/2) + ((windowWidth - boardSize)/2), 
							boardSize/numberOfSpacesPerRow * i + ((boardSize/numberOfSpacesPerRow)/2)));
				}
			}
			else{
				for(int j = 9; j >= 0; j--){ // Vertical movement
					//Vector2 transform = new Vector2(768/10 * i, 768/10 * j);
					temporaryArray.add(new Vector2(boardSize/numberOfSpacesPerRow * j + ((boardSize/numberOfSpacesPerRow)/2) + ((windowWidth - boardSize)/2), 
							boardSize/numberOfSpacesPerRow * i + ((boardSize/numberOfSpacesPerRow)/2)));
				}
			}
		}
		return temporaryArray;
	}
	
	/**
	 * Function that is used for the purpose resizing the board.
	 * 
	 * @param width used for altering the width.
	 * @param height used for altering the height.
	 */
	public void resize(int width, int height) {
	    viewport.update(width, height);
	}
	
	/**
	 * This is the main function that is used for rendering the UI of the application.
	 * 
	 */
	@Override
	public void render () {
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		gamestage.draw();
		
		batch.begin();
		boardSprite.draw(batch);
		timemachineSprite1.draw(batch);
		timemachineSprite2.draw(batch);
		timemachineSprite3.draw(batch);
		portalSprite1.draw(batch);
		portalSprite2.draw(batch);
		portalSprite3.draw(batch);
		portalSprite4.draw(batch);
		portalSprite5.draw(batch);
		portalSprite6.draw(batch);
		portalSprite7.draw(batch);
		portalSprite8.draw(batch);
		
		bountySprite1.draw(batch);
		bountySprite2.draw(batch);
		bountySprite3.draw(batch);
		
		gamePiece.draw(batch);
		gamePiece1.draw(batch);
		dice.draw(batch);
		batch.end();
		
//		shapeRenderer.setProjectionMatrix(camera.combined);
//		shapeRenderer.begin(ShapeType.Filled);
//		shapeRenderer.setColor(1, 0, 0, 1);
//		for(Vector2 transform: boardTransforms){
//			shapeRenderer.circle(transform.x, transform.y, 5);
//		}
//		shapeRenderer.circle(boardTransforms.get(index).x, boardTransforms.get(index).y, 5);
//		shapeRenderer.end();
		
		if(Gdx.input.isKeyJustPressed(Keys.SPACE)){
//			if(index < 99){
//				index++;
//			}
//			else{
//				index = 0;
//			}
//			
//			gamePiece.moveToPosition(boardTransforms.get(temp));
//			moving_piece(43);
//			gamePiece.setAlpha();
//			dice.changeAnimate();
//			dice.showNumber(6);
		}
	}
	
	int playersel = 0;
	
	/**
	 * This function is used for moving the pawn on the board.
	 * 
	 * @param value_tomove	value of which the pawn is to be moved.
	 */
	public void moving_piece(int value_tomove)
	{

		++playersel;
		if(playersel % 2 == 0)
		{
			playerTwo.setDisabled(false);
			if(time_machine_flag == 0)
			{
				index = index + value_tomove;
				if(index > 98)
				{
					gamePiece.moveToPosition(boardTransforms.get(99));
					//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
				}
				else
				{
					gamePiece.moveToPosition(boardTransforms.get(index));
				}
			}
			else if( time_machine_flag == 1)
			{
				index = index + value_tomove;
				if(timeMachine_counter > 0 && index < final_pos )
				{
					timeMachine_counter--;
					if(timeMachine_counter == 0 && index <final_pos)
					{
						time_machine_flag = 0;
						index = 0;
						gamePiece.moveToPosition(boardTransforms.get(index));
					}
					else
					{
						if(index > 98)
						{
							gamePiece.moveToPosition(boardTransforms.get(99));
							//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
						}
						else
						{
							gamePiece.moveToPosition(boardTransforms.get(index));
						}
					}
				}
				else if(timeMachine_counter == 0 && index < final_pos)
				{
					time_machine_flag = 0;
					index = 0;
					gamePiece.moveToPosition(boardTransforms.get(index));
				}
				else if(timeMachine_counter >= 0 && index >= final_pos)
				{

					time_machine_flag = 0;
					if(index > 98)

						time_machine_flag = 0;
					index = index + value_tomove;
					if(index > 98)
					{
						gamePiece.moveToPosition(boardTransforms.get(99));
						//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
					}
					else
					{
						gamePiece.moveToPosition(boardTransforms.get(index));
					}
				}
			}
	//		index = index + value;
	//		gamePiece.moveToPosition(boardTransforms.get(index));
	//		System.out.println(index +" "+ value_tomove);

			int ret = obj.check_portal(index);
			int bounty_ret = obj.check_bounty(index);
			if(ret != 0)
			{
				index = ret;
	//			if(index )
				System.out.println("Portal ");
				if(index > 98)
				{
					gamePiece.secondaryMove(boardTransforms.get(99));
					//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
				}
				else
				{
					gamePiece.secondaryMove(boardTransforms.get(index));
					if(obj.check_portal(index) != 0)
					{
						index = index + obj.check_portal(index);
						System.out.println("Portal ");
						if(index > 98)
						{
							gamePiece.secondaryMove(boardTransforms.get(99));
							//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
						}
						else
						{
							gamePiece.secondaryMove(boardTransforms.get(index));
						}
					}
				}
			}
			else if(bounty_ret == 1)
			{
//				JOptionPane.showMessageDialog(null,"You have won a Bounty Card");
			}
	//		
			if(obj.check_timeMachine(index) != 0)
			{
	//			System.out.println(" time machine");
				timeMachine_counter = 2;
				time_machine_flag = 1;
				if(index == temporary_TimeMachinePosition[0])
				{
					timemachineSprite1.setColor(1,1,1,1);
				}
				else if(index == temporary_TimeMachinePosition[1])
				{
					timemachineSprite2.setColor(1,1,1,1);
				}
				else
				{
					timemachineSprite3.setColor(1,1,1,1);
				}
				final_pos = obj.check_timeMachine(index);
			}
			//if(index > 98)
				//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
		}
		else
		{
			playerTwo.setDisabled(true);
			if(time_machine_flag1 == 0)
			{
				index1 = index1 + value_tomove;
				if(index1 > 98)
				{
					gamePiece1.moveToPosition(boardTransforms.get(99));
					//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
				}
				else
				{
					gamePiece1.moveToPosition(boardTransforms.get(index1));
				}
			}
			else if( time_machine_flag1 == 1)
			{
				index1 = index1 + value_tomove;
				if(timeMachine_counter1 > 0 && index1 < final_pos1 )
				{
					timeMachine_counter1--;
					if(timeMachine_counter1 == 0 && index1 <final_pos1)
					{
						time_machine_flag1 = 0;
						index1 = 0;
						gamePiece1.moveToPosition(boardTransforms.get(index1));
					}
					else
					{
						if(index1 > 98)
						{
							gamePiece1.moveToPosition(boardTransforms.get(99));
							//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
						}
						else
						{
							gamePiece1.moveToPosition(boardTransforms.get(index1));
						}
					}
				}
				else if(timeMachine_counter1 == 0 && index1 < final_pos1)
				{
					time_machine_flag1 = 0;
					index1 = 0;
					gamePiece1.moveToPosition(boardTransforms.get(index1));
				}
				else if(timeMachine_counter1 >= 0 && index1 >= final_pos1)
				{

					time_machine_flag1 = 0;
					if(index1 > 98)

						time_machine_flag1 = 0;
					index1 = index1 + value_tomove;
					if(index1 > 98)
					{
						gamePiece1.moveToPosition(boardTransforms.get(99));
						//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
					}
					else
					{
						gamePiece1.moveToPosition(boardTransforms.get(index1));
					}
				}
			}
	//		index = index + value;
	//		gamePiece.moveToPosition(boardTransforms.get(index));
	//		System.out.println(index +" "+ value_tomove);
			int bounty_ret = obj.check_bounty(index);
			int ret = obj.check_portal(index1);
			if(ret != 0)
			{
				index1 = ret;
	//			if(index )
				System.out.println("Portal ");
				if(index1 > 98)
				{
					gamePiece1.secondaryMove(boardTransforms.get(99));
					//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
				}
				else
				{
					gamePiece1.secondaryMove(boardTransforms.get(index1));
					if(obj.check_portal(index1) != 0)
					{
						index1 = index1 + obj.check_portal(index1);
						System.out.println("Portal ");
						if(index1 > 98)
						{
							gamePiece1.secondaryMove(boardTransforms.get(99));
							//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
						}
						else
						{
							gamePiece1.secondaryMove(boardTransforms.get(index1));
						}
					}
				}
			}
			else if(bounty_ret == 1)
			{
//				JOptionPane.showMessageDialog(null,"You have won a Bounty Card");
			}
	//		
			if(obj.check_timeMachine(index1) != 0)
			{
	//			System.out.println(" time machine");
				timeMachine_counter1 = 2;
				time_machine_flag1 = 1;
				if(index1 == temporary_TimeMachinePosition[0])
				{
					timemachineSprite1.setColor(1,1,1,1);
				}
				else if(index1 == temporary_TimeMachinePosition[1])
				{
					timemachineSprite2.setColor(1,1,1,1);
				}
				else
				{
					timemachineSprite3.setColor(1,1,1,1);
				}
				final_pos1 = obj.check_timeMachine(index1);
			}
			//if(index > 98)
				//JOptionPane.showMessageDialog(null, "Congrats! You Won!!");
			
		}
		
	}
	
	/**
	 * This function is used for disposing the board.
	 */
	@Override
	public void dispose(){
		boardBackground.dispose();
//		shapeRenderer.dispose();
		batch.dispose();
	}
}
