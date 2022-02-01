package entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import org.w3c.dom.css.Rect;

import MapResources.GameMap;
import MapResources.TileType;
import MapResources.TiledGameMap;

public class Player extends Entity {

    private Texture img;
    private Sprite sprite;
    private Polygon polygon;
    private Vector2 pos;
    //Initialise speed - how fast the player sprite can move
    private float SPEED = 90;
    //Initialise the Tilemap for this game
    TiledGameMap gameMap = new TiledGameMap();
    //Checker for college combat
    public boolean collegeCombat;
    public String collegeToAttack;

    //Initialise player values to be used later in development
    public String objectiveCollege;
    public int maxHealth = 100;
    public int currentHealth, armourRating, weaponDamage;
    public int gold, score, width, height, requiredPlunder;

    //Initialise player as a sprite and position
    public Player(){
        img = new Texture("Ships/ship_still.png");
        sprite = new Sprite(img);
        pos = new Vector2(0,0);
        width = img.getWidth();
        height = img.getHeight();
        polygon = new Polygon(new float[]{ //so we pass a new float array to the constructor
                0,0, //this is the x,y of the first vertex
                50,0, //the second vertex
                50,50, //the third
                0,50}); //and the last
        gold = 0;
        score = 0;
        armourRating = 1;
        weaponDamage = 1;
        currentHealth = maxHealth;
        collegeCombat = false;
    }

    @Override
    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }

    @Override
    public void update(){
        move();
    }

    private void move(){
        float sin45 = 0.7f;
        float oldPosX = pos.x;
        float oldPosY = pos.y;
        boolean up = false;
        boolean down = false;
        boolean left = false;
        boolean right = false;


        //If key is pressed set values to true
        //Player can be operated using both WASD and arrow keys
        if(Gdx.input.isKeyPressed(Input.Keys.W) | Gdx.input.isKeyPressed(Input.Keys.UP)) up = true;
        if(Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN)) down = true;
        if(Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT)) left = true;
        if(Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT)) right = true;

        // move Up
        if(up){
            pos.y += SPEED * Gdx.graphics.getDeltaTime();
            sprite.setRotation(0);
            polygon.setRotation(0);
        }
        //move Down
        if(down){
            pos.y -= SPEED * Gdx.graphics.getDeltaTime();
            sprite.setRotation(180);
            polygon.setRotation(180);
        }
        //move Left
        if(left){
            pos.x -= SPEED * Gdx.graphics.getDeltaTime();
            sprite.setRotation(90);
            polygon.setRotation(90);
        }
        //move Right
        if(right){
            pos.x += SPEED * Gdx.graphics.getDeltaTime();
            sprite.setRotation(270);
            polygon.setRotation(270);
        }
        //taking away movement to make it same speed
        if (up && right) {
            pos.x -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.y -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(315);
            polygon.setRotation(315);
        }
        if (up && left) {
            pos.y -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.x += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(45);
            polygon.setRotation(45);
        }
        if (down && right) {
            pos.x -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.y += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(225);
            polygon.setRotation(225);
        }
        if (down && left) {
            pos.x += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.y += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(135);
            polygon.setRotation(135);
        }
        if(!down && !left && !right && !up){
            //Still animation
        }

        //Check collisions (and college battles)
        checkCollision(oldPosX, oldPosY, pos.x, pos.y);

        //Set new sprite position
        sprite.setPosition(pos.x, pos.y);
        polygon.setPosition(pos.x, pos.y);
    }

    public void checkCollision(float oldX, float oldY, float x, float y){
        for (int row = (int) (y / TileType.TILE_SIZE); row < Math.ceil((y + getHeight()) / TileType.TILE_SIZE); row++) {
            for (int col = (int) (x / TileType.TILE_SIZE); col < Math.ceil((x + getWidth()) / TileType.TILE_SIZE); col++) {
                for (int layer = 0; layer < gameMap.getLayers(); layer++) {
                    TileType tile = gameMap.getTileTypeByCoordinate(layer, col, row);
                    if (tile != null) {
                        if (tile.isCollidable()) {
                            if (tile.getName() == "Goodricke College") {
                                if ("Goodricke College" != objectiveCollege) {
                                    pos.x = oldX;
                                    pos.y = oldY;
                                } else {
                                    if (requiredPlunder == 0) {
                                        collegeCombat = true;
                                        collegeToAttack = objectiveCollege;
                                    } else {
                                        pos.x = oldX;
                                        pos.y = oldY;
                                    }
                                }
                            }
                            if (tile.getName() == "Constantine College") {
                                if ("Constantine College" != objectiveCollege) {
                                    pos.x = oldX;
                                    pos.y = oldY;
                                } else {
                                    if (requiredPlunder == 0) {
                                        collegeCombat = true;
                                        collegeToAttack = objectiveCollege;
                                    } else {
                                        pos.x = oldX;
                                        pos.y = oldY;
                                    }
                                }
                            }
                            if (tile.getName() == "Halifax College") {
                                if ("Halifax College" != objectiveCollege) {
                                    pos.x = oldX;
                                    pos.y = oldY;
                                } else {
                                    if (requiredPlunder == 0) {
                                        collegeCombat = true;
                                        collegeToAttack = objectiveCollege;
                                    } else {
                                        pos.x = oldX;
                                        pos.y = oldY;
                                    }
                                }
                            }
                            if (tile.getName() == "James College") {
                                if ("James College" != objectiveCollege) {
                                    pos.x = oldX;
                                    pos.y = oldY;
                                } else {
                                    if (requiredPlunder == 0) {
                                        collegeCombat = true;
                                        collegeToAttack = objectiveCollege;
                                    } else {
                                        pos.x = oldX;
                                        pos.y = oldY;
                                    }
                                }
                            }
                            if (tile.getName() == "Small Island") {
                                //Nothing yet
                            } else {
                                pos.x = oldX;
                                pos.y = oldY;
                            }
                        }
                    }
                }
            }
        }
    }

    public void checkGoldCollision(LostGold gold){
        if(Intersector.overlapConvexPolygons(gold.polygon, this.polygon)) {
            setGold(5);
            gold.spawnGold();
        }
    }

    public Vector2 getPos() {
        return pos;
    }

    public int getCurrentHealth(){
        return(currentHealth);
    }

    public int getScore(){
        return(score);
    }

    public int getGold(){
        return(gold);
    }

    public int getWidth() {
        return(width);
    }

    public int getHeight() {
        return(height);
    }

    public String getObjectiveCollege(){return objectiveCollege;}

    public int getRequiredPlunder(){return requiredPlunder;}

    public float getXPos(){
        return(pos.x);
    }

    public float getYPos(){
        return(pos.y);
    }

    public void setXPos(float x){
        pos.x = x;
        polygon.setPosition(x, getYPos());
    }

    public void setYPos(float y){
        pos.y = y;
        polygon.setPosition(getXPos(), y);
    }

    public void setObjectiveCollege(String objectiveCollege){
        this.objectiveCollege = objectiveCollege;
        if(this.objectiveCollege == "Goodricke College"){
            requiredPlunder = 50;
        }
        else if(this.objectiveCollege == "Constantine College"){
            requiredPlunder = 50;
        }
        else if(this.objectiveCollege == "Halifax College"){
            requiredPlunder = 50;
        }
        else if(this.objectiveCollege == "James College"){
            requiredPlunder = 50;
        }
    }

    public void setGold(int collectedGold){
        gold += collectedGold;
        if (requiredPlunder != 0){
            requiredPlunder -= collectedGold;
        }
    }

    public void setArmourRating(float newArmour){

    }

    public void setSPEED(float newSpeed){

    }

    public void setWeaponDamage(float newWeaponDamage){

    }

    public void hit(int damage){

    }
}
