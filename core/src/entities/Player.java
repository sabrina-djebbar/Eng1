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
    private float SPEED = 90;

    public float playerWidth = 10;
    public float playerHeight = 10;
    public int maxHealth = 100;
    public int currentHealth, armourRating, weaponDamage;
    public int gold, score, width, height;

    public Player(){
        img = new Texture("Ships/ship (1).png");
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

        sprite.setPosition(pos.x, pos.y);
    }

    public float getPlayerWidth(){ return(playerWidth); }

    public float getPlayerHeight(){ return(playerHeight); }

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
