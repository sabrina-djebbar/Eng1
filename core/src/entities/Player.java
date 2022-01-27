package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.yorkpiratesgame.io.Entity;

public class Player extends Entity {

    private Texture img;
    private Vector2 pos;
    private float SPEED = 120;
    public int maxHealth = 100;
    public int currentHealth;
    public int armourRating;
    public int weaponDamage;
    public int width;
    public int height;



    public Player(){
        img = new Texture("Ships/ship (1).png");
        pos = new Vector2(0,0);
        width = img.getWidth();
        height = img.getHeight();

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
        }
        //move Down
        if(Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            pos.y -= SPEED * Gdx.graphics.getDeltaTime();
            if (pos.y < height) pos.y = height;
        }
        //move Left
        if(Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            pos.x -= SPEED * Gdx.graphics.getDeltaTime();
            if (pos.x < height) pos.x = height;
        }
        //move Right
        if(Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            pos.x += SPEED * Gdx.graphics.getDeltaTime();
        }
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
    
    public int getWidth() {
    	return(width);
    }
    
    public int getHeight() {
    	return(height);
    }


    public void hit(int damage){

    }

    public int getCurrentHealth(){
        return(currentHealth);
    }
}
