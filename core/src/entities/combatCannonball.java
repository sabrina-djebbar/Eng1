package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class combatCannonball extends Entity {
	private Rectangle rectangle = new Rectangle();
	private String imgCannonball;
	private Texture img;
    private Sprite sprite;
    private Vector2 pos;
    //Initialise speed - how fast the combatCannonball sprite moves
    private float SPEED = 200;
    
    public combatCannonball(Vector2 position, String college){
        if(college == "Goodricke College"){
            imgCannonball = "Combat/goodrickeAttack.png";
        }
        else if(college == "James College"){
            imgCannonball = "Combat/jamesAttack.png";
        }
        else if(college == "Halifax College"){
            imgCannonball = "Combat/halifaxAttack.png";
        }
        else if(college == "Constantine College"){
            imgCannonball = "Combat/constantineAttack.png";
        }
    	img = new Texture(imgCannonball);
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