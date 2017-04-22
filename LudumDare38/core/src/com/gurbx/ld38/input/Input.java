package com.gurbx.ld38.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.gurbx.ld38.mobs.Mob;
import com.gurbx.ld38.mobs.MobHandler;

public class Input implements InputProcessor {
	private Vector2 lastTouch;
	private Vector2 touchUp;
	private Vector2 mousePos;
	private boolean selecting;
	
	private MobHandler mobHandler;
	private Selection selection;
	
	private float selectionX, selectionY;
	private float selectionWidth, selectionHeight;
	
	public Input(MobHandler mobHandler) {
		this.mobHandler = mobHandler;
		this.selecting = false;
		this.lastTouch = new Vector2();
		this.touchUp = new Vector2();
		this.mousePos = new Vector2();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		lastTouch.set(screenX, Gdx.graphics.getHeight()- screenY);

//		mob.moveTo(screenX, Gdx.graphics.getHeight()  - screenY);
//		System.out.println("x:" + screenX + " y:" + screenY );
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		touchUp.set(screenX, Gdx.graphics.getHeight()- screenY);
		
		if (button == 0) {
			mobHandler.deselect();
			if (selecting) {
				mobHandler.select(selectionX, selectionY, selectionWidth, selectionHeight);
			}
		}
		selecting = false;
//		Gdx.input.isButtonPressed(B)
		if (button == 1) {
			mobHandler.moveSelectedMobsToPoint(touchUp.x, touchUp.y);
		}
		
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mousePos.set(screenX, Gdx.graphics.getHeight()- screenY);
		Vector2 newTouch = new Vector2(screenX, Gdx.graphics.getHeight()- screenY);
		
	    Vector2 delta = newTouch.cpy().sub(lastTouch);
	 
	    //Only select if significant drag
	    if (Math.abs(delta.x)> 1 || Math.abs(delta.y) > 1) {
	    	selecting= true;
	    	selection = null;
	    }
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		mousePos.set(screenX, Gdx.graphics.getHeight()- screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void update(float delta) {
		if (selecting) {
			selectionX = lastTouch.x;
			selectionY = lastTouch.y;
			selectionWidth = -lastTouch.x + mousePos.x;
			selectionHeight = -lastTouch.y + mousePos.y;
		}
		
	}
	
	public void renderSelection (ShapeRenderer shapeRender) {
		shapeRender.setColor(Color.GREEN);
		if (selecting) {
			shapeRender.begin(ShapeType.Line);
			shapeRender.box(selectionX, selectionY, 0, selectionWidth, selectionHeight, 0);
			shapeRender.end();
		}
		
		
	}

}
