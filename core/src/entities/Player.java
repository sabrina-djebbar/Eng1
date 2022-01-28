package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

public class Player extends Entity {

    private Texture img;
    private Vector2 pos;
    private float SPEED = 60;
    private String direction;

    public float playerWidth = 10;
    public float playerHeight = 10;
    public int maxHealth = 100;
    public int currentHealth;
    public int armourRating;
    public int weaponDamage;
    public int gold;
    public int score;

    public Player(){
        img = new Texture("Ships/ship.png");
        direction = "UP";
        pos = new Vector2(0,0);
        currentHealth = maxHealth;
        armourRating = 1;
        weaponDamage = 10;
    }

    @Override
    public void render(SpriteBatch batch){
        batch.draw(img, pos.x, pos.y);
    }

    @Override
    public void update(){
        move();
    }

    private void move(){
        // move Up
        if(Gdx.input.isKeyPressed(Input.Keys.W) | Gdx.input.isKeyPressed(Input.Keys.UP)){
            pos.y += SPEED * Gdx.graphics.getDeltaTime();
            if (direction != "UP") {
                direction = "UP";
                img.dispose();
                img = new Texture("Ships/ship.png");
            }
        }
        //move Down
        else if(Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            pos.y -= SPEED * Gdx.graphics.getDeltaTime();
            if (direction != "DOWN") {
                direction = "DOWN";
                img.dispose();
                img = new Texture("Ships/shipDOWN.png");
            }
        }
        //move Left
        else if(Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            pos.x -= SPEED * Gdx.graphics.getDeltaTime();
            if (direction != "LEFT") {
                direction = "LEFT";
                img.dispose();
                img = new Texture("Ships/shipLEFT.png");
            }
        }
        //move Right
        else if(Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            pos.x += SPEED * Gdx.graphics.getDeltaTime();
            if (direction != "RIGHT") {
                direction = "RIGHT";
                img.dispose();
                img = new Texture("Ships/shipRIGHT.png");
            }
        }
    }



    public float getXPos(){
        return(pos.x);
    }

    public float getYPos(){
        return(pos.y);
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

    public void setPosition(float x, float y){
        pos.x = x;
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
