/**
 * This is the launcher class which is used for running the application.
 * It holds the main method which runs the application.
 *  
 *  @author Team24 for CSE 360 Spring 2016
 *  @version 1.1 April 15,2016
 */

package com.portalsandtimemachines.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.portalsandtimemachines.game.Game;



public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Portals and Time Machines"; 									// Title of the dice game.
		config.width = 1024;
		config.height = 768;
		config.resizable = false;
		new LwjglApplication(new Game(), config);                                       // Application run.  
	}
}
