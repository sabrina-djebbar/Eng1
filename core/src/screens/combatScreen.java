package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.yorkpiratesgame.io.YorkPirates;

import entities.combatCannonball;
import entities.combatGold;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.Input.Keys;

import java.util.Iterator;


public class combatScreen implements Screen {
    YorkPirates game;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Rectangle pirate;
    //List of cannonballs and gold coins
    private Array<combatCannonball> cannonballs;
    private Array<combatGold> coins;
    private long lastcombatCannonballTime;
    private long randomcombatCannonballTime;
    private long lastCoinTime;
    private long randomCoinTime;
    private long startTime = TimeUtils.millis();
    private Texture pirateImage;
    private int pirateHealth;
    private int goldCollected;
    private BitmapFont pirateFont;
    private Color fontColour;

    public combatScreen(YorkPirates game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        pirateImage = new Texture(Gdx.files.internal("Combat/pirateBarrel.png"));
        pirate = new Rectangle();
        pirate.x = Gdx.graphics.getWidth() / 2 - 256 / 2;
        pirate.y = 20;
        pirate.width = pirateImage.getWidth();
        pirate.height = pirateImage.getHeight();
        pirateHealth = 100;
        goldCollected = 0;

        cannonballs = new Array<combatCannonball>();
        spawncombatCannonball();
        coins = new Array<combatGold>();
        spawnCoin();
    }

    public void spawncombatCannonball() {
    	int x = MathUtils.random(0, Gdx.graphics.getWidth()-64);
    	int y = Gdx.graphics.getHeight()-20;
    	Vector2 position = new Vector2 (x, y);
    	combatCannonball cannonball = new combatCannonball(position);
    	cannonballs.add(cannonball);
    	//record time this cannonball was spawned
        lastcombatCannonballTime = TimeUtils.nanoTime();
        //set time for next cannonball
        randomcombatCannonballTime = MathUtils.random(1000000000, 2000000000);
    }
    
    public void spawnCoin() {
    	int x = MathUtils.random(0, Gdx.graphics.getWidth()-64);
    	int y = Gdx.graphics.getHeight()-20;
    	Vector2 position = new Vector2 (x, y);
        combatGold coin = new combatGold(position);
        coins.add(coin);
        lastCoinTime = TimeUtils.nanoTime();
        randomCoinTime = MathUtils.random(1000000000, 2000000000);
    }

    @Override
    public void show() {
    	//Create colour for labels
        fontColour = new Color(59/255f,60/255f,54/255f, 3/2);
        pirateFont = new BitmapFont(Gdx.files.internal("UI/pirateFont.fnt"));
        pirateFont.setColor(fontColour);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.begin();
        game.batch.draw(pirateImage, pirate.x, pirate.y);
        //render cannonballs
        for(combatCannonball cannonball: cannonballs) {
        	cannonball.render(game.batch);
        }
        //render coins
        for(combatGold coin: coins) {
        	coin.render(game.batch);
        }
        drawUI();
        game.batch.end();
        
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            pirate.x = touchPos.x - 64 / 2;
        }

        if(Gdx.input.isKeyPressed(Keys.LEFT)) pirate.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) pirate.x += 200 * Gdx.graphics.getDeltaTime();

        if(pirate.x < 0) pirate.x = 0;
        if(pirate.x > Gdx.graphics.getWidth() - pirate.getWidth()) pirate.x = Gdx.graphics.getWidth() - pirate.getWidth();

        // check if we need to create a new coin or cannonball
        if(TimeUtils.nanoTime() - lastcombatCannonballTime > randomcombatCannonballTime) spawncombatCannonball();
        if(TimeUtils.nanoTime() - lastCoinTime > randomCoinTime) spawnCoin();

        for (Iterator<combatCannonball> iter = cannonballs.iterator(); iter.hasNext(); ) {
            combatCannonball cannonball = iter.next();
            cannonball.update();
            if(cannonball.getPos().y + 64 < 0) iter.remove();
            if(cannonball.getRectangle().overlaps(pirate)) {
                iter.remove();
                pirateHealth -= 5;
            }
        }
        
        for (Iterator<combatGold> iter = coins.iterator(); iter.hasNext(); ) {
            combatGold coin = iter.next();
            coin.update();
            if(coin.getPos().y + 64 < 0) iter.remove();
            if(coin.getRectangle().overlaps(pirate)) {
                iter.remove();
                goldCollected += 1;
            }
        }
    }
    
    public void drawUI(){
        pirateFont.draw(game.batch, "Gold: " + goldCollected, 5, Gdx.graphics.getHeight() - 5);
        pirateFont.draw(game.batch, "Health: " + pirateHealth, 5, Gdx.graphics.getHeight() - 40);
        pirateFont.draw(game.batch, "Time Left: " + String.valueOf(60-((TimeUtils.millis() - startTime)/1000)), 5, Gdx.graphics.getHeight() - 75);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        pirateImage.dispose();
        batch.dispose();
    }
}
