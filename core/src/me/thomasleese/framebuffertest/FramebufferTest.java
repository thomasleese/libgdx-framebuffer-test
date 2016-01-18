package me.thomasleese.framebuffertest;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.math.*;

public class FramebufferTest extends ApplicationAdapter {

    FrameBuffer fbo;
    SpriteBatch batch;
    TextureRegion texture;

    @Override
    public void create () {
        batch = new SpriteBatch();

        Texture img = new Texture("badlogic.jpg");

        fbo = new FrameBuffer(Pixmap.Format.RGBA8888, img.getWidth(), img.getHeight(), false);

        texture = new TextureRegion(fbo.getColorBufferTexture());
        texture.flip(false, true);

        fbo.begin();

        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Matrix4 matrix = new Matrix4();
        matrix.setToOrtho2D(0, 0, img.getWidth(), img.getHeight());
        batch.setProjectionMatrix(matrix);

        batch.begin();
        batch.draw(img, 0, 0);
        batch.end();

        fbo.end();

        // uncomment this line to see the correct behaviour
        // run this with the line commented will return to the correct behaviour after moving the window
        // HdpiUtils.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
    }

}
