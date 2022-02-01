package entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

import MapResources.GameMap;
import MapResources.TileType;
import MapResources.TiledGameMap;

public class LostGold extends Entity{

    private Texture img;
    private Player player;
    private Sprite goldIsland;
    private Vector2 pos;
    private int goldIslandWidth = 64;
    private int goldIslandHeight = 64;
    private GameMap gameMap;

    public LostGold(Player player) {
        //Set player to player created in mainGameScreen
        this.player = player;

        //Initialise the Tilemap for this game
        gameMap = new TiledGameMap();

        pos = new Vector2(0,0);
        img = new Texture("GameMaps/goldIsland.png");
        goldIsland = new Sprite(img);
        spawnGold();
    }

    public void spawnGold(){
        int valueX = (int) MathUtils.random(player.getWidth(), gameMap.getMapWidth() - player.getWidth());
        int valueY = (int) MathUtils.random(player.getHeight(), gameMap.getMapHeight() - player.getHeight());
        pos.x = valueX;
        pos.y = valueY;

        boolean tileToSpawnAvailable = true;
        while(tileToSpawnAvailable){
            for(int layer = 1; layer < gameMap.getLayers(); layer++) {
                TileType tiletoSpawn = gameMap.getTileTypeByLocation(layer, pos.x, pos.y);
                if (tiletoSpawn != null) {
                    if (tiletoSpawn.isCollidable()) {
                        valueX = (int) MathUtils.random(player.getWidth(), gameMap.getMapWidth() - player.getWidth());
                        valueY = (int) MathUtils.random(player.getHeight(), gameMap.getMapHeight() - player.getHeight());
                        pos.x = valueX;
                        pos.y = valueY;
                    }
                }
            }
            tileToSpawnAvailable = false;
        }


        goldIsland.setPosition(pos.x, pos.y);
        System.out.println(pos.x + " " + pos.y);
    }

    public float getPosX(){
        return pos.x;
    }

    public float getPosY(){
        return pos.y;
    }

    @Override
    public void render(SpriteBatch batch) {
        goldIsland.draw(batch);
    }

    @Override
    public void update() {
        super.update();
    }
}
