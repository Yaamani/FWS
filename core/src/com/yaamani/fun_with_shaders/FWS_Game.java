package com.yaamani.fun_with_shaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.yaamani.fun_with_shaders.ShaderBehaviours.MouseGrayscale;
import com.yaamani.fun_with_shaders.ShaderBehaviours.ShaderBehaviour;

import java.security.Key;

public class FWS_Game extends ApplicationAdapter implements InputProcessor {
	private int WORLD_SIZE = 1000;

	SpriteBatch batch;

	Texture img;
	Texture depthMap;

	FitViewport viewport ;

	int currentBehaviour = 0;
	Array<ShaderBehaviour> behaviours;

	@Override
	public void create () {
		batch = new SpriteBatch();
		initColors();
//		initBuilding();
//		initFairy();
//		initFairy2();
		img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		viewport = new FitViewport(WORLD_SIZE, (int) (WORLD_SIZE * getTextureAspectRatio(img)));
		Gdx.graphics.setWindowedMode((int) viewport.getWorldWidth(), (int) viewport.getWorldHeight());

		behaviours = new Array<ShaderBehaviour>();
        behaviours.add(new MouseGrayscale(viewport));

		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void resize(int width, int height) {
		viewport.update(width, height, true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();


        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.setShader(behaviours.get(currentBehaviour).getShaderProgram());
        behaviours.get(currentBehaviour).update();


		batch.draw(img,
				0,
				0,
				viewport.getWorldWidth(),
				viewport.getWorldHeight());

		batch.setShader(null);
		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		depthMap.dispose();
		for (ShaderBehaviour behaviour : behaviours) {
			behaviour.dispose();
		}
	}










	public float getTextureAspectRatio(Texture texture) {
		return (float) texture.getHeight() / (float) texture.getWidth();
	}



	private void initColors() {
		img =  new Texture("Pictures/Colors.jpg");
		depthMap = new Texture("Depth Maps/Colors.jpg");
	}

	private void initBuilding() {
		WORLD_SIZE = 700;
		img = new Texture("Pictures/Building.jpg");
		depthMap = new Texture("Depth Maps/Building.jpg");
	}

	private void initFairy() {
		img = new Texture("Pictures/Fairy.jpg");
		depthMap = new Texture("Depth Maps/Fairy.jpg");
	}

	private void initFairy2() {
		img = new Texture("Pictures/Fairy2.jpg");
		depthMap = new Texture("Depth Maps/Fairy.jpg");
	}











	@Override
	public boolean keyDown(int keycode) {
		behaviours.get(currentBehaviour).keyDown(keycode);
		return false;
	}

	@Override
	public boolean keyUp(int keycode) { //ShaderBehaviours can pretty much use any input method possible except pressing the SPACE key cuz it loops through the different behaviours.
		if (keycode == Input.Keys.SPACE) {
			if (currentBehaviour == behaviours.size-1) currentBehaviour = 0;
			else currentBehaviour++;
		} else {
			behaviours.get(currentBehaviour).keyUp(keycode);
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		behaviours.get(currentBehaviour).keyTyped(character);
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		behaviours.get(currentBehaviour).touchDown(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		behaviours.get(currentBehaviour).touchUp(screenX, screenY, pointer, button);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		behaviours.get(currentBehaviour).touchDragged(screenX, screenY, pointer);
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		behaviours.get(currentBehaviour).mouseMoved(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		behaviours.get(currentBehaviour).scrolled(amount);
		return false;
	}
}
