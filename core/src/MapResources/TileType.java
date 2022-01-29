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

    //Goodricke College tiles initialised
    goodrickeCollegeTile8 ( 8 , true, " Goodricke College "),
    goodrickeCollegeTile9 ( 9 , true, " Goodricke College "),
    goodrickeCollegeTile10 ( 10 , true, " Goodricke College "),
    goodrickeCollegeTile12 ( 12 , true, " Goodricke College "),
    goodrickeCollegeTile13 ( 13 , true, " Goodricke College "),
    goodrickeCollegeTile14 ( 14 , true, " Goodricke College "),
    goodrickeCollegeTile41 ( 41 , true, " Goodricke College "),
    goodrickeCollegeTile42 ( 42 , true, " Goodricke College "),
    goodrickeCollegeTile43 ( 43 , true, " Goodricke College "),
    goodrickeCollegeTile44 ( 44 , true, " Goodricke College "),
    goodrickeCollegeTile45 ( 45 , true, " Goodricke College "),
    goodrickeCollegeTile46 ( 46 , true, " Goodricke College "),
    goodrickeCollegeTile47 ( 47 , true, " Goodricke College "),
    goodrickeCollegeTile48 ( 48 , true, " Goodricke College "),
    goodrickeCollegeTile49 ( 49 , true, " Goodricke College "),
    goodrickeCollegeTile73 ( 73 , true, " Goodricke College "),
    goodrickeCollegeTile74 ( 74 , true, " Goodricke College "),
    goodrickeCollegeTile75 ( 75 , true, " Goodricke College "),
    goodrickeCollegeTile76 ( 76 , true, " Goodricke College "),
    goodrickeCollegeTile77 ( 77 , true, " Goodricke College "),
    goodrickeCollegeTile78 ( 78 , true, " Goodricke College "),
    goodrickeCollegeTile79 ( 79 , true, " Goodricke College "),
    goodrickeCollegeTile80 ( 80 , true, " Goodricke College "),
    goodrickeCollegeTile81 ( 81 , true, " Goodricke College "),
    goodrickeCollegeTile82 ( 82 , true, " Goodricke College "),
    goodrickeCollegeTile105 ( 105 , true, " Goodricke College "),
    goodrickeCollegeTile106 ( 106 , true, " Goodricke College "),
    goodrickeCollegeTile107 ( 107 , true, " Goodricke College "),
    goodrickeCollegeTile108 ( 108 , true, " Goodricke College "),
    goodrickeCollegeTile109 ( 109 , true, " Goodricke College "),
    goodrickeCollegeTile110 ( 110 , true, " Goodricke College "),
    goodrickeCollegeTile111 ( 111 , true, " Goodricke College "),
    goodrickeCollegeTile112 ( 112 , true, " Goodricke College "),
    goodrickeCollegeTile113 ( 113 , true, " Goodricke College "),
    goodrickeCollegeTile114 ( 114 , true, " Goodricke College "),
    goodrickeCollegeTile115 ( 115 , true, " Goodricke College "),
    goodrickeCollegeTile116 ( 116 , true, " Goodricke College "),
    goodrickeCollegeTile138 ( 138 , true, " Goodricke College "),
    goodrickeCollegeTile139 ( 139 , true, " Goodricke College "),
    goodrickeCollegeTile140 ( 140 , true, " Goodricke College "),
    goodrickeCollegeTile141 ( 141 , true, " Goodricke College "),
    goodrickeCollegeTile142 ( 142 , true, " Goodricke College "),
    goodrickeCollegeTile143 ( 143 , true, " Goodricke College "),
    goodrickeCollegeTile144 ( 144 , true, " Goodricke College "),
    goodrickeCollegeTile145 ( 145 , true, " Goodricke College "),
    goodrickeCollegeTile146 ( 146 , true, " Goodricke College "),
    goodrickeCollegeTile147 ( 147 , true, " Goodricke College "),
    goodrickeCollegeTile148 ( 148 , true, " Goodricke College "),
    goodrickeCollegeTile149 ( 149 , true, " Goodricke College "),
    goodrickeCollegeTile173 ( 173 , true, " Goodricke College "),
    goodrickeCollegeTile174 ( 174 , true, " Goodricke College "),
    goodrickeCollegeTile175 ( 175 , true, " Goodricke College "),
    goodrickeCollegeTile176 ( 176 , true, " Goodricke College "),
    goodrickeCollegeTile177 ( 177 , true, " Goodricke College "),
    goodrickeCollegeTile178 ( 178 , true, " Goodricke College "),
    goodrickeCollegeTile179 ( 179 , true, " Goodricke College "),
    goodrickeCollegeTile180 ( 180 , true, " Goodricke College "),
    goodrickeCollegeTile181 ( 181 , true, " Goodricke College "),
    goodrickeCollegeTile206 ( 206 , true, " Goodricke College "),
    goodrickeCollegeTile207 ( 207 , true, " Goodricke College "),
    goodrickeCollegeTile208 ( 208 , true, " Goodricke College "),
    goodrickeCollegeTile209 ( 209 , true, " Goodricke College "),
    goodrickeCollegeTile210 ( 210 , true, " Goodricke College "),
    goodrickeCollegeTile211 ( 211 , true, " Goodricke College "),
    goodrickeCollegeTile212 ( 212 , true, " Goodricke College "),
    goodrickeCollegeTile213 ( 213 , true, " Goodricke College "),
    goodrickeCollegeTile241 ( 241 , true, " Goodricke College "),
    goodrickeCollegeTile242 ( 242 , true, " Goodricke College "),
    goodrickeCollegeTile243 ( 243 , true, " Goodricke College "),
    goodrickeCollegeTile244 ( 244 , true, " Goodricke College "),
    goodrickeCollegeTile245 ( 245 , true, " Goodricke College "),
    goodrickeCollegeTile246 ( 246 , true, " Goodricke College "),
    goodrickeCollegeTile274 ( 274 , true, " Goodricke College "),
    goodrickeCollegeTile275 ( 275 , true, " Goodricke College "),
    goodrickeCollegeTile276 ( 276 , true, " Goodricke College "),
    goodrickeCollegeTile277 ( 277 , true, " Goodricke College "),
    goodrickeCollegeTile278 ( 278 , true, " Goodricke College "),
    goodrickeCollegeTile279 ( 279 , true, " Goodricke College "),
    goodrickeCollegeTile308 ( 308 , true, " Goodricke College "),
    goodrickeCollegeTile309 ( 309 , true, " Goodricke College "),
    goodrickeCollegeTile310 ( 310 , true, " Goodricke College "),
    goodrickeCollegeTile311 ( 311 , true, " Goodricke College ");


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
