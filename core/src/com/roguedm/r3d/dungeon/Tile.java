package com.roguedm.r3d.dungeon;

public enum Tile {

    Wall("#"), Floor(".");

    private String c;

    private Tile(String c) {
        this.c = c;
    }

    public String toString() {
        return c;
    }

}
