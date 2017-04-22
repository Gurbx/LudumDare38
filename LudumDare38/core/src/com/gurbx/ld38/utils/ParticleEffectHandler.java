package com.gurbx.ld38.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class ParticleEffectHandler implements GameInterface{
	ParticleEffect effect;
	private TextureAtlas atlas;
	
	public ParticleEffectHandler(TextureAtlas atlas) {
		this.atlas = atlas;
	}
	

	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyJustPressed(Keys.A)) {
			effect = new ParticleEffect();
			effect.load(Gdx.files.internal("img/bloodEffect.p"), atlas);
			effect.start();
		}
		if (effect != null) effect.setPosition(400, 400);
		if (effect != null) effect.update(delta);
		
//		System.out.println(effect == null);
		
	}

	@Override
	public void render(SpriteBatch batch) {
		if (effect != null) effect.draw(batch);
		
	}

	@Override
	public void dispse() {
		if (effect != null) effect.dispose();
		
	}

}
