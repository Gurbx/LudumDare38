package com.gurbx.ld38.message;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.gurbx.ld38.utils.Constants;

public class Message {
	private String message;
	private float spawnX, spawnY;
	private float targetX, targetY = Constants.UI_VIRTUAL_HEIGHT-80;
	private float x, y;
	private float speed = 240;
	private float timer, timeDisplayed;
	private boolean waiting;
	private boolean display;
	private boolean displayed;
	private boolean canRemove;
	private float height;
	private float width;
	private float modifer;
	
	public Message(float timer, float timeDisplayer, String message, float height, float width, float modifer) {
		this.modifer = modifer;
		this.message = message;
		this.timer = timer;
		this.timeDisplayed = timeDisplayer;
		waiting = true;
		displayed =false;
		display = false;
		y = Constants.UI_VIRTUAL_HEIGHT + height;
		x = Constants.UI_VIRTUAL_WIDTH/2 - width/2;
		this.width = width;
		this.height = height;
	}

	public void update(float delta) {
		timer -= delta;
		if (timer <= 0) {
			display = true;
			timeDisplayed -= delta;
			if (timeDisplayed <= 0) {
				displayed = true;
				if (y > Constants.UI_VIRTUAL_HEIGHT + height) {
					canRemove = true;
				}
			}
		}
		if (display && displayed == false) {
			y -= speed * delta;
			if (y < targetY) y = targetY;
		}
		if (displayed) {
			y += speed * delta;
		}
	}

	public void render(SpriteBatch batch, BitmapFont font, TextureRegion texture) {
		batch.draw(texture, x, y - height * 0.5f);
		font.draw(batch, message, x + 40 + modifer, y + 14);
		
	}
	
	public boolean shouldRemove(){
		return canRemove;
	}


}
