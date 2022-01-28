package com.yorkpiratesgame.io;

import java.util.HashMap;

public enum TileType {

    SEA(144, false, "Sea"),
    BORDER_RIGHT(330, true, "BorderRight"),
    BORDER_LEFT(391, true, "BorderLeft"),
    BORDER_BOTTOM(306, true, "BorderBottom"),
    BORDER_TOP(415, true, "BorderTop"),
    //BORDER_TOP_RIGHT(308, true, "BorderTopRight"),
    BORDER_TOP_LEFT(308, true, "BorderTopLeft"),
    BORDER_BOTTOM_LEFT(336, true, "BorderBottomLeft"),
    //BORDER_BOTTOM_RIGHT(2, true, "BorderBottomRight"),
    BORDER_BLOCK(332,true, "BorderBlock");
    //GOLD_CHEST(3, true, "GoldAtSea"),
    //COLLEGE(4, true, "College");

    public static final int TILE_SIZE = 24;

    private int id;
    private boolean collidable;
    private String name;
    private float damage;

    private TileType (int id, boolean collidable, String name){
        this(id, collidable, name, 0);
    }

    private TileType (int id, boolean collidable, String name, float damage){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }

    public int getId(){return id;}
    public boolean isCollidable(){return collidable;}
    public String getName(){return name;}
    public float getDamage(){return damage;}

    private static HashMap<Integer, TileType> tileMap;

    static{
        for(TileType tileType : TileType.values()){
            tileMap.put(tileType.getId(), tileType);
        }
    }

    public static TileType getTileTypeById(int id){
        return tileMap.get(id);
    }
}
