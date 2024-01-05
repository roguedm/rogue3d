package com.roguedm.r3d.actor;

import com.roguedm.r3d.dungeon.Direction;

import squidpony.squidmath.Coord;

public class Avatar {

    private Direction facing;

    private Coord coord;

    public Avatar() {
        coord = Coord.get(2, 1);
        facing = Direction.North;
    }

    public Direction getFacing() {
        return facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public void setX(int x) {
        coord = coord.translate(x, 0);
    }

    public void setY(int y) {
        coord = coord.translate(0, y);
    }

    public void setXY(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        return coord.x;
    }

    public int getY(){
        return coord.y;
    }

}
