package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.mygdx.game.Enums.TileType;

public class TiledGameMap extends GameMap {
    TiledMap tiledMap;
    OrthogonalTiledMapRenderer tiledMapRenderer;

    public TiledGameMap() {
        tiledMap = new TmxMapLoader().load("map.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap, 1/16f);
    }

    @Override
    public void render(OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void dispose() {
        tiledMap.dispose();
    }

    @Override
    public TileType getTileTypeByCoordinate(int layer, int col, int row) {
        Cell cell = ((TiledMapTileLayer)tiledMap.getLayers().get(layer)).getCell(col, row);

        if(cell != null) {
            TiledMapTile tile = cell.getTile();

            if(tile != null) {
                int id = tile.getId();
                return TileType.getTileTypeById(id);
            }
        }
        return null;
    }

    @Override
    public int getLayers() {
        return tiledMap.getLayers().getCount();
    }

    @Override
    public int getWidth() {
        return ((TiledMapTileLayer)tiledMap.getLayers().get(1)).getWidth();
    }

    @Override
    public int getHeight() {
        return ((TiledMapTileLayer)tiledMap.getLayers().get(1)).getHeight();
    }
}
