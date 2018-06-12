package com.yaamani.fun_with_shaders.ShaderBehaviours;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.Disposable;

public class ShaderBehaviour implements InputProcessor, Disposable {

    ShaderProgram shaderProgram;

    protected void setShaderBehaviour(FileHandle fragmentShader) {
        shaderProgram = new ShaderProgram(Gdx.files.internal("Shaders/default.vs.glsl"), fragmentShader);
        if (!shaderProgram.isCompiled()) Gdx.app.error("Shader compile error", shaderProgram.getLog());
    }

    protected void setShaderBehaviour(FileHandle vertexShader, FileHandle fragmentShader) {
        shaderProgram = new ShaderProgram(vertexShader, fragmentShader);
        if (!shaderProgram.isCompiled()) Gdx.app.error("Shader compile error", shaderProgram.getLog());
    }




    public void update() {

    }

    public ShaderProgram getShaderProgram() {
        return shaderProgram;
    }

    @Override
    public void dispose() {
        shaderProgram.dispose();
    }









    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
