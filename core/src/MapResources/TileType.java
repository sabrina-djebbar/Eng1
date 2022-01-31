package MapResources;

import java.util.HashMap;

public enum TileType {

    //Sea Tiles initialised
    SeaTile1(1, false, "Sea Tile"),
    SeaTile2(2, false, "Sea Tile"),
    SeaTile3(3, false, "Sea Tile"),
    SeaTile4(4, false, "Sea Tile"),
    SeaTile5(5, false, "Sea Tile"),
    SeaTile6(6, false, "Sea Tile"),
    SeaTile34(34, false, "Sea Tile"),
    SeaTile35(35, false, "Sea Tile"),
    SeaTile36(36, false, "Sea Tile"),
    SeaTile37(37, false, "Sea Tile"),
    SeaTile38(38, false, "Sea Tile"),
    SeaTile39(39, false, "Sea Tile"),
    SeaTile66(66, false, "Sea Tile"),
    SeaTile67( 67 , false, "Sea Tile"),
    SeaTile68( 68 , false, "Sea Tile"),
    SeaTile69( 69 , false, "Sea Tile"),
    SeaTile70( 70 , false, "Sea Tile"),
    SeaTile71( 71 , false, "Sea Tile"),
    SeaTile99( 99 , false, "Sea Tile"),
    SeaTile100( 100 , false, "Sea Tile"),
    SeaTile101( 101 , false, "Sea Tile"),
    SeaTile102( 102 , false, "Sea Tile"),
    SeaTile103( 103 , false, "Sea Tile"),
    SeaTile104( 104 , false, "Sea Tile"),
    SeaTile132( 132 , false, "Sea Tile"),
    SeaTile133( 133 , false, "Sea Tile"),
    SeaTile134( 134 , false, "Sea Tile"),
    SeaTile135( 135 , false, "Sea Tile"),
    SeaTile136( 136 , false, "Sea Tile"),
    SeaTile137( 137 , false, "Sea Tile"),
    SeaTile166( 166 , false, "Sea Tile"),
    SeaTile167( 167 , false, "Sea Tile"),
    SeaTile168( 168 , false, "Sea Tile"),
    SeaTile169( 169 , false, "Sea Tile"),
    SeaTile170( 170 , false, "Sea Tile"),
    SeaTile171( 171 , false, "Sea Tile"),

    //Goodricke College tile initialised
    goodrickeCollegeTile ( 7 , true, "Goodricke College"),

    //Halifax College tile initialised
    halifaxCollegeTile (28, true, "Halifax College"),

    //James College tile initialised
    jamesCollegeTile (49, true, "James College"),

    //Constantine College tile initialised
    constantineCollegeTile (70, true, "Constantine College"),

    //Smaller Island tiles initialised
    smallerIslandsTile (91, true, "Smaller Islands");


    //Set Tile size (24x24)
    public static final int TILE_SIZE = 24;

    //Initialise values
    private int id;
    private boolean collidable;
    private String name;
    private float damage;

    //Constructor that appends damage value if not given
    private TileType (int id, boolean collidable, String name){
            this(id, collidable, name, 0);
    }

    //Enum Constructor
    private TileType (int id, boolean collidable, String name, float damage){
        this.id = id;
        this.collidable = collidable;
        this.name = name;
        this.damage = damage;
    }

    //Getters and setters for enum values
    public int getId(){return id;}
    public boolean isCollidable(){return collidable;}
    public String getName(){return name;}
    public float getDamage(){return damage;}

    //Initialise hashmap which will hold the tiles created
    private static HashMap<Integer, TileType> tileMap;

    //Set tiles to the hashmap
    static{
        tileMap = new HashMap<Integer, TileType>();
        for(TileType tileType : TileType.values()){
            tileMap.put(tileType.getId(), tileType);
        }
    }

    //Return tile type by id
    public static TileType getTileTypeById(int id){
        return tileMap.get(id);
    }
}
