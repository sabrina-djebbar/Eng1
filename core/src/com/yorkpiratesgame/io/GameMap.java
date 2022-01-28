package com.yorkpiratesgame.io;

import com.badlogic.gdx.graphics.OrthographicCamera;

public abstract class GameMap {

    public abstract void render (OrthographicCamera camera);
    public abstract void update (float delta);
    public abstract void dispose ();

    // Gets a tile by pixel position within the game world at a specific layer
    public TileType getTileTypeByLocation(int layer, float x, float y){
        return this.getTileTypeByCoordinate(layer, (int) (x / TileType.TILE_SIZE), (int) (y / TileType.TILE_SIZE) );
    }

    // Gets a tile at its coordinate within the map at a specified layer
    public abstract TileType getTileTypeByCoordinate(int layer, float col, float row);

    public abstract int getWidth();
    public abstract int getHeight();
    public abstract int getLayers();
}
