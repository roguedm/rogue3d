package com.roguedm.r3d.dungeon;

import static com.roguedm.r3d.AssetManager.DUNGEON_CEILING_FILE;
import static com.roguedm.r3d.AssetManager.DUNGEON_DOOR_FILE;
import static com.roguedm.r3d.AssetManager.DUNGEON_SKULL_FILE;
import static com.roguedm.r3d.AssetManager.DUNGEON_WALL_FILE;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.roguedm.r3d.AssetManager;

public enum Tile {

    Wall("#", DUNGEON_WALL_FILE,false),
    Floor(".", DUNGEON_CEILING_FILE, true),
    Door("+", DUNGEON_DOOR_FILE, true),
    Trap("^", DUNGEON_SKULL_FILE, true);

    private String assetName;

    private String glyph;

    private boolean walkable;

    private Tile(String glyph, String assetName, boolean walkable) {
        this.glyph = glyph;
        this.assetName = assetName;
        this.walkable = walkable;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public String toString() {
        return this.glyph;
    }

    public TextureRegion getTextureRegion(int column, int row) {
        return AssetManager.getInstance().getTile(this.assetName, column, row);
    }

    public static Tile getTile(String glyph) {
        for (Tile tile : Tile.values()) {
            String g = tile.glyph;
            if (g != null && g.equals(glyph)) {
                return tile;
            }
        }
        return null;
    }

}
