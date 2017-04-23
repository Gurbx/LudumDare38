package com.gurbx.ld38.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool;
import com.badlogic.gdx.graphics.g2d.ParticleEffectPool.PooledEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

public class ParticleEffectHandler {
	private TextureAtlas atlas;
	
	private static ParticleEffectPool bloodPool;
	private static ParticleEffectPool spellPool;
	private static Array<PooledEffect> activeBloodEffects;
	private static Array<PooledEffect> activeSpellEffects;;
	
	
//	ParticleEffect effect;
	
	public ParticleEffectHandler(TextureAtlas atlas) {
		this.atlas = atlas;
		ParticleEffect bloodExplosion = new ParticleEffect();
		bloodExplosion.load(Gdx.files.internal("img/bloodEffect.p"), atlas);
		bloodPool = new ParticleEffectPool(bloodExplosion, 10, 100);
		
		ParticleEffect spellExplosion = new ParticleEffect();
		spellExplosion.load(Gdx.files.internal("img/spellEffect.p"), atlas);
		spellPool = new ParticleEffectPool(spellExplosion, 10, 100);
		
		
		activeSpellEffects = new Array<PooledEffect>();
		activeBloodEffects = new Array<PooledEffect>();
	}
	
	public static void addBloodEffect(float x, float y) {
		PooledEffect effect = bloodPool.obtain();
		if (effect != null) {
			activeBloodEffects.add(effect);
			effect.setPosition(x, y);
		}
	}
	
	public static void addSpellEffect(float x, float y) {
		PooledEffect effect = spellPool.obtain();
		if (effect != null) {
			activeSpellEffects.add(effect);
			effect.setPosition(x, y);
		}
	}
	

	public void update(float delta) {
		
	}

	public void render(SpriteBatch batch, float delta) {
//		if (effect != null) effect.draw(batch);
		for (int i = 0; i < activeBloodEffects.size; i++) {
			PooledEffect effect = activeBloodEffects.get(i);
			if (effect.isComplete()) {
				bloodPool.free(effect);
				activeBloodEffects.removeIndex(i);
			} else {
				effect.draw(batch, delta);
			}
		}
		for (int i = 0; i < activeSpellEffects.size; i++) {
			PooledEffect effect = activeSpellEffects.get(i);
			if (effect.isComplete()) {
				spellPool.free(effect);
				activeSpellEffects.removeIndex(i);
			} else {
				effect.draw(batch, delta);
			}
		}
		
	}

	public void dispse() {
		for (int i = 0; i < activeBloodEffects.size; i++) {
			activeBloodEffects.get(i).dispose();
		}
		for (int i = 0; i < activeSpellEffects.size; i++) {
			activeSpellEffects.get(i).dispose();
		}
		bloodPool.clear();
		spellPool.clear();
	}

}
