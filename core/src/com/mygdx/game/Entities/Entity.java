package com.mygdx.game.Entities;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Enums.Direction;

public abstract class Entity {
    float x;
    float y;
    final float SPEED;
    Direction CURRENT_DIRECTION;
    Direction NEXT_DIRECTION;
    float ANIMATION_SPEED;
    public static final int FRAME_COLS = 14, FRAME_ROWS = 13;
    int frame;
    public static final int SIZE_PIXEL = 16;
    public static final int SIZE = SIZE_PIXEL * 3;

    public Entity(float x, float y, float speed, Direction DIRECTION) {
        this.x = x;
        this.y = y;
        this.SPEED = speed;
        this.CURRENT_DIRECTION = DIRECTION;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public abstract void render(SpriteBatch batch);
    public abstract void update(float delta);
    public abstract void move();
    public boolean checkCollisionWithWall() {

        return false;
    }

}
