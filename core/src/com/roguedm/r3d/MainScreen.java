package com.roguedm.r3d;

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
import com.roguedm.r3d.dungeon.Dungeon;
import squidpony.squidmath.Coord;

public class MainScreen extends InputAdapter implements Screen {

    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 480;

    private Game parent;

    private FitViewport viewport;

    private SpriteBatch batch;

    private Dungeon dungeon;

    private Avatar avatar;

    public MainScreen(Game parent) {
        this.batch = new SpriteBatch();
        this.viewport = new FitViewport(SCREEN_WIDTH, SCREEN_HEIGHT, new OrthographicCamera());
        this.viewport.getCamera().update();

        this.dungeon = new Dungeon(SCREEN_WIDTH, SCREEN_HEIGHT, 2252637788195L);
        this.avatar = new Avatar();

        Coord randomFloorSpace = dungeon.getRandomFloorSpace();
        if (randomFloorSpace != null) {
            this.avatar.setXY(randomFloorSpace.getX(), randomFloorSpace.getY());
        }

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
            return false;
        }
        return false;
    }

    private void move(int value) {
        Coord coord = getNewPosition(value);
        if (this.dungeon != null && this.avatar != null && coord != null
                && this.dungeon.isWalkable(this.avatar.getX() + coord.getX(), this.avatar.getY() + coord.getY())) {
            this.avatar.setXY(coord.getX(), coord.getY());
        }
    }

    public boolean turn(int direction) {
        Direction facing = this.avatar.getFacing();
        if (direction > 0) {
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
        this.avatar.setFacing(facing);
        return false;
    }

    public Coord getNewPosition(int value) {
        Direction facing = this.avatar.getFacing();
        Coord coord = null;
        if (facing == Direction.South) {
            coord = Coord.get(0, value);
        }
        if (facing == Direction.North) {
            coord = Coord.get(0, -value);
        }
        if (facing == Direction.East) {
            coord = Coord.get(value, 0);
        }
        if (facing == Direction.West) {
            coord = Coord.get(-value, 0);
        }
        return coord;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);
        if (this.batch != null && this.viewport != null) {
            Camera camera = this.viewport.getCamera();
            if (camera != null) {
                camera.update();
                this.viewport.apply(true);
                this.batch.setProjectionMatrix(camera.combined);
            }

            this.batch.begin();
            if (this.dungeon != null && this.avatar != null) {
                this.dungeon.draw(this.batch, this.avatar.getFacing(), this.avatar.getX(), this.avatar.getY(), 0, 0);
            }
            this. batch.end();
        }
    }

    @Override
    public void resize(int width, int height) {
        if (this.viewport != null) {
            this.viewport.update(width, height, true);
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
