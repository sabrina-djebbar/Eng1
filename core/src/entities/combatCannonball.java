package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class combatCannonball extends Entity {
	private Rectangle rectangle = new Rectangle();
	private Texture img;
    private Sprite sprite;
    private Vector2 pos;
    //Initialise speed - how fast the combatCannonball sprite moves
    private float SPEED = 200;
    
    public combatCannonball(Vector2 position){
    	img = new Texture("Combat/cannonball.png");
    	rectangle.width = img.getWidth();
        rectangle.height = img.getHeight();
        sprite = new Sprite(img);
        pos = position;
    }
    
    public void render(SpriteBatch batch){
        sprite.draw(batch);
    }
    
    public void update(){
        move();
    }
    
    private void move() {
    	pos.y -= SPEED * Gdx.graphics.getDeltaTime();
    	sprite.setPosition(pos.x, pos.y);
    	rectangle.setPosition(pos);
    }
    
    public Rectangle getRectangle(){
    	return (rectangle);
    }
    
    public Vector2 getPos() {
    	return (pos);
    }
}