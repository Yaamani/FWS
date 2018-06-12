package com.yaamani.fun_with_shaders.ShaderBehaviours;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MouseGrayscale extends ShaderBehaviour {

    Viewport viewport;

    public MouseGrayscale(Viewport viewport) {
        this.viewport = viewport;
        setShaderBehaviour(new FileHandle("Shaders/grayscaleMousePercentage.fs.glsl"));
    }

    @Override
    public void update() {
        getShaderProgram().setUniformf("u_percentage", Gdx.input.getX()/viewport.getWorldWidth());
    }
}
