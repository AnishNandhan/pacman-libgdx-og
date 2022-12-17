package com.mygdx.game.Enums;

import java.util.HashMap;

public enum TileType {

    EMPTY(0, "Empty"),
    WALL(1, "Wall"),
    POINT(2, "Point"),
    SUPER(3, "Super"),
    DOOR(4, "Door");

    public static final int TILE_SIZE = 16;
    private int id;
    private String name;

    TileType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    private static HashMap<Integer, TileType> tileTypeHashMap;

    static {
        tileTypeHashMap = new HashMap<Integer, TileType>();
        for(TileType tile: TileType.values()) {
            tileTypeHashMap.put(tile.getId(), tile);
        }
    }

    public static TileType getTileTypeById(int id) {
        return tileTypeHashMap.get(id);
    }
}
