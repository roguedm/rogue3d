package com.roguedm.r3d;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.roguedm.r3d.actor.Avatar;
import com.roguedm.r3d.dungeon.Direction;
import com.roguedm.r3d.dungeon.Tile;
import com.roguedm.r3d.dungeon.World;

import squidpony.squidmath.Coord;

public class MainScreen extends InputAdapter implements Screen {

    protected static final int SCREEN_WIDTH = 640;
    protected static final int SCREEN_HEIGHT = 480;

    private Game parent;

    private FitViewport viewport;

    private SpriteBatch batch;

    private World world;

    private Avatar avatar;

    public MainScreen(Game parent) {
        batch = new SpriteBatch();
        viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, new OrthographicCamera());
        viewport.getCamera().update();

        world = new World(SCREEN_WIDTH, SCREEN_HEIGHT);
        avatar = new Avatar();

        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void show() {
    }

    @Override
    public boolean keyDown (int keycode) {
        if (Input.Keys.RIGHT == keycode) {
            turn(1);
        }
        if (Input.Keys.LEFT == keycode) {
            turn(-1);
        }
        if (Input.Keys.UP == keycode) {
            move(1);
            return true;
        }
        if (Input.Keys.DOWN == keycode) {
            move(-1);
            return true;
        }
        if (Input.Keys.ENTER == keycode) {
            //combat(MonsterFactory.getInstance().getRandomMonster("Cultist"));
            return false;
        }
        return false;
    }

    private void move(int value) {
        Coord c = getNewPosition(value);
        if (world != null && avatar != null && world.isWalkable(avatar.getX() + c.getX(), avatar.getY() + c.getY())) {
            avatar.setXY(c.getX(), c.getY());
        }
    }

    public boolean turn(int dir) {
        Direction facing = avatar.getFacing();
        if (dir > 0) {
            if (facing == Direction.North) {
                facing = Direction.East;
            }
            else if (facing == Direction.East) {
                facing = Direction.South;
            }
            else if (facing == Direction.South) {
                facing = Direction.West;
            }
            else if (facing == Direction.West) {
                facing = Direction.North;
            }
        } else {
            if (facing == Direction.North) {
                facing = Direction.West;
            }
            else if (facing == Direction.West) {
                facing = Direction.South;
            }
            else if (facing == Direction.South) {
                facing = Direction.East;
            }
            else if (facing == Direction.East) {
                facing = Direction.North;
            }
        }
        avatar.setFacing(facing);
        return false;
    }

    public Coord getNewPosition(int value) {
        Direction facing = avatar.getFacing();
        Coord c = null;
        if (facing == Direction.South) {
            c = Coord.get(0, value);
        }
        if (facing == Direction.North) {
            c = Coord.get(0, -value);
        }
        if (facing == Direction.East) {
            c = Coord.get(value, 0);
        }
        if (facing == Direction.West) {
            c = Coord.get(-value, 0);
        }
        return c;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        if (batch != null && viewport != null) {
            Camera camera = viewport.getCamera();
            camera.update();
            viewport.apply(true);
            batch.setProjectionMatrix(camera.combined);

            batch.begin();
            if (world != null && avatar != null) {
                world.draw(batch, avatar.getFacing(), avatar.getX(), avatar.getY(), 0, 0);
            }
            batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (viewport != null) {
            viewport.update(width, height, true);
        }
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
