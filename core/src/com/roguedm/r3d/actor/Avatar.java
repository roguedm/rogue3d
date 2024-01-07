package com.roguedm.r3d.actor;

import com.roguedm.r3d.dungeon.Direction;

import squidpony.squidmath.Coord;

public class Avatar {

    private Direction facing;

    private Coord coord;

    public Avatar() {
        coord = Coord.get(0, 0);
        facing = Direction.North;
    }

    public Direction getFacing() {
        return this.facing;
    }

    public void setFacing(Direction facing) {
        this.facing = facing;
    }

    public void setX(int x) {
        if (coord != null) {
            this.coord = this.coord.translate(x, 0);
        }
    }

    public void setY(int y) {
        if (coord != null) {
            this.coord = this.coord.translate(0, y);
        }
    }

    public void setXY(int x, int y) {
        setX(x);
        setY(y);
    }

    public int getX() {
        if (coord != null) {
            return this.coord.x;
        }
        return 0;
    }

    public int getY(){
        if (coord != null) {
            return this.coord.y;
        }
        return 0;
    }

}
