package com.roguedm.r3d;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.roguedm.r3d.dungeon.Tile;

public class MainScreen implements Screen {

    protected static final int SCREEN_WIDTH = 640;
    protected static final int SCREEN_HEIGHT = 480;

    private Game parent;

    private FitViewport viewport;

    private SpriteBatch batch;

    public MainScreen(Game parent) {
        batch = new SpriteBatch();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, new OrthographicCamera());
        viewport.getCamera().update();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        Camera camera = viewport.getCamera();
        camera.update();
        viewport.apply(true);
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        // Just a test
        batch.draw(AssetManager.getInstance().getTile(Tile.Wall.toString(), 1, 1), 0, 0, 300, 300);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        if (batch != null) {
            batch.dispose();
            batch = null;
        }
    }

}
