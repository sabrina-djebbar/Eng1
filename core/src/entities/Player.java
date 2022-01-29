package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    private Texture img;
    private Sprite sprite;
    private Vector2 pos;
    //Initialise speed - how fast the player sprite can move
    private float SPEED = 90;

    //Initialise player values to be used later in development
    public String objectiveCollege;
    public float playerWidth = 10;
    public float playerHeight = 10;
    public int maxHealth = 100;
    public int currentHealth, armourRating, weaponDamage;
    public int gold, score, width, height;

    //Initialise player as a sprite and position
    public Player(){
        img = new Texture("Ships/ship.png");
        sprite = new Sprite(img);
        pos = new Vector2(0,0);
        width = img.getWidth();
        height = img.getHeight();
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
            sprite.setRotation(180);
        }
        //move Down
        if(down){
            pos.y -= SPEED * Gdx.graphics.getDeltaTime();
            sprite.setRotation(0);
        }
        //move Left
        if(left){
            pos.x -= SPEED * Gdx.graphics.getDeltaTime();
            sprite.setRotation(270);
        }
        //move Right
        if(right){
            pos.x += SPEED * Gdx.graphics.getDeltaTime();
            sprite.setRotation(90);
        }
        //taking away movement to make it same speed
        if (up && right) {
            pos.x -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.y -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(135);
        }
        if (up && left) {
            pos.y -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.x += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(225);
        }
        if (down && right) {
            pos.x -= SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.y += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(45);
        }
        if (down && left) {
            pos.x += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            pos.y += SPEED * (1-sin45) * Gdx.graphics.getDeltaTime();
            sprite.setRotation(315);
        }
        //Set new sprite position
        sprite.setPosition(pos.x, pos.y);
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

    public float getXPos(){
        return(pos.x);
    }

    public float getYPos(){
        return(pos.y);
    }

    public void setXPos(float x){
        pos.x = x;
    }

    public void setYPos(float y){
        pos.y = y;
    }

    public void setObjectiveCollege(String objectiveCollege){
        this.objectiveCollege = objectiveCollege;
    }

    public void setGold(int collectedGold){

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
