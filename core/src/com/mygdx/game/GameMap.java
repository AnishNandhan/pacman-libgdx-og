package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.Enums.TileType;

public abstract class GameMap {
    public abstract void render(OrthographicCamera camera);
    public abstract void update(float delta);
    public abstract void dispose();

    public TileType getTileTypeByLocation(int layer, float x, float y) {
        return this.getTileTypeByCoordinate(layer, (int)x, (int)y);
    }

    public abstract TileType getTileTypeByCoordinate(int layer, int col, int row);

    public abstract int getLayers();
    public abstract int getWidth();
    public abstract int getHeight();
}
