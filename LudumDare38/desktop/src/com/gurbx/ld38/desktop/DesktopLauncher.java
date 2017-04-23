package com.gurbx.ld38.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gurbx.ld38.Application;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 800;
		config.height = 800;
		config.title = "LD38: Bug Attack";
		config.addIcon("icon.png", FileType.Internal);
		config.resizable = false;
		new LwjglApplication(new Application(), config);
	}
}
