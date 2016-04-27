package com.jerickduguran.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.jerickduguran.game.FlappyBurd;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width   = FlappyBurd.WIDTH;
		config.height  = FlappyBurd.HEIGHT;
		config.title   = FlappyBurd.TITLE;

		new LwjglApplication(new FlappyBurd(), config);
	}
}
