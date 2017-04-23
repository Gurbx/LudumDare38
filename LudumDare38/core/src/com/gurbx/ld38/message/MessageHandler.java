package com.gurbx.ld38.message;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gurbx.ld38.Application;
import com.gurbx.ld38.utils.GameInterface;

public class MessageHandler implements GameInterface {
	private Application app;
	private ArrayList<Message> messages;
	private TextureRegion messageTex;
	
	private String[] messageStrings;
	
	public MessageHandler(Application app, TextureAtlas atlas) {
		this.app = app;
		messages = new ArrayList<Message>();
		messageTex = new TextureRegion(atlas.findRegion("messageBox"));
		messageStrings = new String[8];
		messageStrings[0] = "Defend your world from the evil bugs!";
		messageStrings[1] = "Some buildings produce resin \n   and some produce pollen.";
		messageStrings[2] = "You can spend your resin on new buildings.";
		messageStrings[3] = "The pollen you can use to train new warriors.";
		messageStrings[4] = "Train new warriors by selecting the round building\n              			     in the middle of the town.";
		messageStrings[5] = "Click the right mouse button to get back \n         to the buildings menu again";
		messageStrings[6] = "There is a limit to how many warriors \n              you can command.";
		messageStrings[7] = "To increase the number of warriors you can train,\n                          buy more houses.";
		
		float dtime = 7;
		//Init messages
		messages.add(new Message(2f, dtime, messageStrings[0], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 32));
		messages.add(new Message(0.4f, dtime, messageStrings[1], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 64));
		messages.add(new Message(0.4f, dtime, messageStrings[2], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 22));
		messages.add(new Message(0.4f, dtime, messageStrings[3], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 14));
		messages.add(new Message(0.4f, dtime, messageStrings[4], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 0));
		messages.add(new Message(0.4f, dtime, messageStrings[5], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 32));
		messages.add(new Message(0.4f, dtime, messageStrings[6], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 40));
		messages.add(new Message(0.4f, dtime, messageStrings[7], messageTex.getRegionHeight(), messageTex.getRegionWidth(), 0));
	}

	@Override
	public void update(float delta) {
		if (messages.isEmpty() == false) {
			messages.get(0).update(delta);
			if (messages.get(0).shouldRemove()) {
				messages.remove(0);
			}
		}
		
	}

	@Override
	public void render(SpriteBatch batch) {
		app.font.setColor(Color.WHITE);
		if (messages.isEmpty() == false) {
			messages.get(0).render(batch, app.font, messageTex);
		}
		
	}

	@Override
	public void dispse() {
		
	}

}
