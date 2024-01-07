package com.roguedm.r3d.dungeon;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.roguedm.r3d.AssetManager;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import squidpony.squidgrid.mapping.DungeonGenerator;
import squidpony.squidgrid.mapping.DungeonUtility;
import squidpony.squidmath.Coord;
import squidpony.squidmath.GreasedRegion;
import squidpony.squidmath.RNG;

public class Dungeon {

    private char[][] map;

    private int screenWidth;
    private int screenHeight;

    private RNG rng;

    public Dungeon(int screenWidth, int screenHeight, long seed) {
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;

        // Seed
        this.rng = new RNG(seed);

        DungeonGenerator dungeonGenerator = new DungeonGenerator(30, 30, rng);
        dungeonGenerator.addDoors(70, true);
        dungeonGenerator.addTraps(2);
        dungeonGenerator.addStairs(false, true);

        ClassicRogueMapGenerator classic = new ClassicRogueMapGenerator(4, 4,
                30, 30, 4, 8, 3, 6, rng);
        this.map = DungeonUtility.closeDoors(dungeonGenerator.generate(classic.generate()));

        for (int y=0; y<map.length; y++) {
            for (int x=0; x<map[0].length; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println("");
        }
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

    public Coord getRandomFloorSpace() {
        if (map != null) {
            int x = MathUtils.random(0, map[0].length - 1);
            int y = MathUtils.random(0, map.length - 1);
            char c = '#';
            int frustration = 0;
            while (c != '.' && frustration < 1000) {
                x = MathUtils.random(0, map[0].length - 1);
                y = MathUtils.random(0, map.length - 1);
                c = map[x][y];
                frustration++;
            }
            return Coord.get(x, y);
        }
        return Coord.get(0, 0);
    }

    public boolean isWalkable(int x, int y) {
        Tile tile = Tile.getTile(String.valueOf(map[x][y]));
        if (tile != null) {
            return tile.isWalkable();
        }
        return true;
    }

    private void north(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x - 2, y - 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y - 2, 0, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y - 2, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y - 2, 0, 3, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y - 2, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y - 2, 0, 5, this.screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x - 2, y - 1, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y - 1, 0, 7, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y - 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y - 1, 1, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y - 1, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y - 1, 1, 3, this.screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x - 1, y, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y, 1, 5, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, this.screenWidth / 2, offsetX, offsetY);
    }

    private void south(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x + 2, y + 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y + 2, 0, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y + 2, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y + 2, 0, 3, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y + 2, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y + 2, 0, 5, this.screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x + 2, y + 1, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y + 1, 0, 7, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y + 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y + 1, 1, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 0, y + 1, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 0, y + 1, 1, 3, this.screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x + 1, y, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y, 1, 5, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, this.screenWidth / 2, offsetX, offsetY);
    }

    private void east(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x + 2, y - 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y + 2, 0, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 2, y - 1, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y + 1, 0, 3, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 2, y + 0, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x + 2, y + 0, 0, 5, this.screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x + 1, y - 2, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y + 2, 0, 7, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y - 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y + 1, 1, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x + 1, y + 0, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x + 1, y + 0, 1, 3, this.screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x, y - 1, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x, y + 1, 1, 5, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, this.screenWidth / 2, offsetX, offsetY);
    }

    private void west(SpriteBatch batch, int x, int y, int offsetX, int offsetY) {
        // Back
        drawTile(batch, x - 2, y + 2, 0, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y - 2, 0, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 2, y + 1, 0, 2, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y - 1, 0, 3, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 2, y + 0, 0, 4, 0, offsetX, offsetY);
        drawTile(batch, x - 2, y + 0, 0, 5, this.screenWidth / 2, offsetX, offsetY);

        // Middle
        drawTile(batch, x - 1, y + 2, 0, 6, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y - 2, 0, 7, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y + 1, 1, 0, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y - 1, 1, 1, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x - 1, y + 0, 1, 2, 0, offsetX, offsetY);
        drawTile(batch, x - 1, y + 0, 1, 3, this.screenWidth / 2, offsetX, offsetY);

        // Front
        drawTile(batch, x, y + 1, 1, 4, 0, offsetX, offsetY);
        drawTile(batch, x, y - 1, 1, 5, this.screenWidth / 2, offsetX, offsetY);
        drawTile(batch, x, y, 1, 6, 0, offsetX, offsetY);
        drawTile(batch, x, y, 1, 7, this.screenWidth / 2, offsetX, offsetY);
    }

    private void drawTile(SpriteBatch batch, int x, int y, int col, int row, int offset, int offsetX, int offsetY) {
        TextureRegion t = getTileTexture(x, y, col, row);
        if (t != null && batch != null & batch.isDrawing()) {
            batch.draw(t, offset + offsetX, offsetY);
        }
    }

    private TextureRegion getTileTexture(int x, int y, int col, int row) {
        if (x >= 0 && y >= 0 && map != null && x < map[0].length && y < map.length) {
            Tile tile = Tile.getTile(String.valueOf(map[x][y]));
            if (tile != null) {
                return tile.getTextureRegion(col, row);
            }
        }
        return null;
    }

}
