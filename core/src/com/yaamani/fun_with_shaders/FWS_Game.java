package com.yaamani.fun_with_shaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class FWS_Game extends ApplicationAdapter {
	private int WORLD_SIZE = 1000;


	SpriteBatch batch;

	Texture img;
	Texture depthMap;

	ShaderProgram shaderProgram;

	FitViewport viewport ;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		initColors();
//		initBuilding();
//		initFairy();
//		initFairy2();

		img.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

		shaderProgram = new ShaderProgram(Gdx.files.internal("Shaders/default.vs.glsl"), Gdx.files.internal("Shaders/grayscaleMousePercentage.fs.glsl"));
		if (!shaderProgram.isCompiled()) Gdx.app.error("Shader compile error", shaderProgram.getLog());

		viewport = new FitViewport(WORLD_SIZE, (int) (WORLD_SIZE * getTextureAspectRatio(img)));

		Gdx.graphics.setWindowedMode((int) viewport.getWorldWidth(), (int) viewport.getWorldHeight());
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

		//Gdx.gl20.glActiveTexture(GL);

        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.setShader(shaderProgram);
        shaderProgram.setUniformf("u_percentage", Gdx.input.getX()/viewport.getWorldWidth());
		// shaderProgram.setUniformi("u_depthMap", 1);

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
}
