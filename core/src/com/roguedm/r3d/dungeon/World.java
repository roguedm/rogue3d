package com.roguedm.r3d.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.roguedm.r3d.AssetManager;

public class World {

    private String[][] map = new String[][] {
            { "#", "#", "#", "#", "#" },
            { "#", ".", ".", ".", "#" },
            { "#", ".", ".", ".", "#" },
            { "#", ".", ".", ".", "#" },
            { "#", ".", ".", ".", "#" },
            { "#", ".", ".", ".", "#" },
            { "#", "#", "#", "#", "#" }
    };

    private int screenWidth;
    private int screenHeight;

    public World(int screenWidth, int screenHeight) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
    }

    public void draw(final SpriteBatch batch, Direction direction, int x, int y, int offsetX, int offsetY) {
        direction = direction != null ? direction : Direction.North;
        if (batch != null && batch.isDrawing()) {
            switch (direction) {
                case North: {
                    north(batch, x, y, offsetX, offsetY);
                    break;
                }
                case South: {
                    south(batch, x, y, offsetX, offsetY);
                    break;
                }
                case East: {
                    east(batch, x, y, offsetX, offsetY);
                    break;
                }
                case West: {
                    west(batch, x, y, offsetX, offsetY);
                    break;
                }
            }
        }
    }

    public boolean isWalkable(int x, int y) {
        return true;
    }

    private void north(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x - 2, y - 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y - 2, 0, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y - 2, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y - 2, 0, 3, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y - 2, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y - 2, 0, 5, screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x - 2, y - 1, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y - 1, 0, 7, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y - 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y - 1, 1, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y - 1, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y - 1, 1, 3, screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x - 1, y, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y, 1, 5, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, screenWidth / 2, offsetX, offsetY);
    }

    private void south(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x + 2, y + 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y + 2, 0, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y + 2, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y + 2, 0, 3, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y + 2, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y + 2, 0, 5, screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x + 2, y + 1, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y + 1, 0, 7, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y + 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y + 1, 1, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y + 1, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y + 1, 1, 3, screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x + 1, y, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y, 1, 5, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, screenWidth / 2, offsetX, offsetY);
    }

    private void east(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x + 2, y - 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y + 2, 0, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 2, y - 1, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y + 1, 0, 3, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 2, y + 0, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y + 0, 0, 5, screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x + 1, y - 2, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y + 2, 0, 7, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y - 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y + 1, 1, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y + 0, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y + 0, 1, 3, screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x, y - 1, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x, y + 1, 1, 5, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, screenWidth / 2, offsetX, offsetY);
    }

    private void west(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x - 2, y + 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y - 2, 0, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 2, y + 1, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y - 1, 0, 3, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 2, y + 0, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y + 0, 0, 5, screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x - 1, y + 2, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y - 2, 0, 7, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y + 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y - 1, 1, 1, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y + 0, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y + 0, 1, 3, screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x, y + 1, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x, y - 1, 1, 5, screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, screenWidth / 2, offsetX, offsetY);
    }

    private void drawTile(SpriteBatch batch, int x, int y, int col, int row, int offset, int offsetX, int offsetY) {
        TextureRegion t = getTile(x, y, col, row);
        if (t != null && batch != null & batch.isDrawing()) {
            batch.draw(t, offset + offsetX, offsetY);
        }
    }

    private TextureRegion getTile(int x, int y, int col, int row) {
        if (x >= 0 && y >= 0 && map != null && x < map[0].length && y < map.length) {
            return AssetManager.getInstance().getTile(map[y][x], col, row);
        }
        return null;
    }

}
