package com.roguedm.r3d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.roguedm.r3d.dungeon.Tile;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {

    private static final String DUNGEON_WALL_FILE = "tiles/dungeon_wall.png";
    private static final String DUNGEON_CEILING_FILE = "tiles/dungeon_ceiling.png";

    private com.badlogic.gdx.assets.AssetManager manager;

    private static AssetManager instance;

    private Map<String, TextureRegion[][]> tiles;

    private AssetManager() {
        manager = new com.badlogic.gdx.assets.AssetManager();

        tiles = new HashMap<String, TextureRegion[][]>();
    }

    public void load() {
        manager.load(DUNGEON_WALL_FILE, Texture.class);
        manager.load(DUNGEON_CEILING_FILE, Texture.class);

        manager.finishLoading();

        if (manager.isLoaded(DUNGEON_WALL_FILE, Texture.class)) {
            tiles.put(Tile.Wall.toString(), getTextureRegions(manager.get(DUNGEON_WALL_FILE, Texture.class), 8, 2));
        }
        if (manager.isLoaded(DUNGEON_CEILING_FILE, Texture.class)) {
            tiles.put(Tile.Floor.toString(), getTextureRegions(manager.get(DUNGEON_CEILING_FILE, Texture.class), 8, 2));
        }
    }

    public TextureRegion[][] getTextureRegions(Texture texture, int columns, int rows) {
        TextureRegion[][] region = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight()
                / rows);
        return region;
    }

    public TextureRegion getTile(String character, int x, int y) {
        if (tiles == null) {
            return null;
        }
        if (tiles.get(character) == null) {
            return null;
        }
        return new TextureRegion(tiles.get(character)[x][y]);
    }

    public static AssetManager getInstance() {
        if (instance == null) {
            instance = new AssetManager();
        }
        return instance;
    }

    public void dispose() {
        if (manager != null) {
            manager.clear();
            manager.dispose();
        }
        if (tiles != null) {
            tiles.clear();
            tiles  = null;
        }
    }

}
