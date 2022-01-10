package com.yorkpiratesgame.io.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.yorkpiratesgame.io.YorkPirates;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "York Pirates! - Scone Zone";
		config.foregroundFPS = 60;
		config.width = YorkPirates.WIDTH;
		config.height = YorkPirates.HEIGHT;
		config.resizable = false;
		new LwjglApplication(new YorkPirates(), config);
	}
}
