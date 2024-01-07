package com.roguedm.r3d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.roguedm.r3d.dungeon.Tile;

import java.util.HashMap;
import java.util.Map;

public class AssetManager {

    public static final String DUNGEON_WALL_FILE = "tiles/dungeon_wall.png";
    public static final String DUNGEON_CEILING_FILE = "tiles/dungeon_ceiling.png";
    public static final String DUNGEON_DOOR_FILE = "tiles/dungeon_door.png";
    public static final String DUNGEON_SKULL_FILE = "tiles/skull_pile.png";

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
        manager.load(DUNGEON_DOOR_FILE, Texture.class);
        manager.load(DUNGEON_SKULL_FILE, Texture.class);

        manager.finishLoading();

        if (manager.isLoaded(DUNGEON_WALL_FILE, Texture.class)) {
            tiles.put(DUNGEON_WALL_FILE, getTextureRegions(manager.get(DUNGEON_WALL_FILE, Texture.class), 8, 2));
        }
        if (manager.isLoaded(DUNGEON_CEILING_FILE, Texture.class)) {
            tiles.put(DUNGEON_CEILING_FILE, getTextureRegions(manager.get(DUNGEON_CEILING_FILE, Texture.class), 8, 2));
        }
        if (manager.isLoaded(DUNGEON_DOOR_FILE, Texture.class)) {
            tiles.put(DUNGEON_DOOR_FILE, getTextureRegions(manager.get(DUNGEON_DOOR_FILE, Texture.class), 8, 2));
        }
        if (manager.isLoaded(DUNGEON_SKULL_FILE, Texture.class)) {
            tiles.put(DUNGEON_SKULL_FILE, getTextureRegions(manager.get(DUNGEON_SKULL_FILE, Texture.class), 8, 2));
        }
    }

    public TextureRegion[][] getTextureRegions(Texture texture, int columns, int rows) {
        TextureRegion[][] region = TextureRegion.split(texture, texture.getWidth() / columns, texture.getHeight()
                / rows);
        return region;
    }

    public TextureRegion getTile(String assetName, int x, int y) {
        if (this.tiles == null || assetName == null) {
            return null;
        }
        if (this.tiles.get(assetName) == null) {
            return null;
        }
        if (x < this.tiles.get(assetName).length && y < this.tiles.get(assetName)[0].length) {
            return new TextureRegion(this.tiles.get(assetName)[x][y]);
        }
        return null;
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
