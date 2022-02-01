package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.yorkpiratesgame.io.YorkPirates;

import MapResources.TiledGameMap;
import entities.Player;
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
    private Player player;

    private OrthographicCamera camera;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;
    String gameMap = "";

    //List of cannonballs and gold coins
    private Array<combatCannonball> cannonballs;
    private Array<combatGold> coins;
    private Array<Rectangle> pirateCannonballs;
    private boolean shoot;
    private long time;
    private long lastcombatCannonballTime;
    private long randomcombatCannonballTime;
    private long lastCoinTime;
    private long randomCoinTime;

    //Initialise Boss
    private Rectangle boss;
    private int bossHealth;

    //Initialise variables of Pirate;
    private Rectangle pirate;
    private Texture pirateImage;
    private Texture pirateCannonballImg;
    private int pirateHealth;
    private int goldCollected;

    private BitmapFont pirateFont;
    private Color fontColour;

    
    //public combatScreen(YorkPirates game){
    //}
    
    public combatScreen(YorkPirates game, Player player){
        this.game = game;
        this.player = player;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Initialise the tilemap for the college being attacked
        if (this.player.getObjectiveCollege() == "Goodricke College"){
            gameMap = "GameMaps/combatScreenGoodricke.tmx";
        }
        else if (this.player.getObjectiveCollege() == "Constantine College"){
            gameMap = "GameMaps/combatScreenConstantine.tmx";
        }
        else if (this.player.getObjectiveCollege() == "James College"){
            gameMap = "GameMaps/combatScreenJames.tmx";
        }
        else if (this.player.getObjectiveCollege() == "Halifax College"){
            gameMap = "GameMaps/combatScreenHalifax.tmx";
        }

        //Initialise tilemap and tile renderer
        tiledMap = new TmxMapLoader().load(gameMap);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);

        //Initialise player
        pirateImage = new Texture(Gdx.files.internal("Ships/ship_still.png"));
        pirateCannonballImg = new Texture(Gdx.files.internal("Combat/cannonball.png"));
        pirate = new Rectangle();
        pirate.x = Gdx.graphics.getWidth() / 2 - 256 / 2;
        pirate.y = 20;
        pirate.width = pirateImage.getWidth();
        pirate.height = pirateImage.getHeight();
        pirateHealth = 100;
        goldCollected = 0;

        //Initialise boss entity
        bossHealth = 100;
        boss = new Rectangle();
        boss.width = 288;
        boss.height = 288;
        boss.setPosition((Gdx.graphics.getWidth()/2) - (boss.width/2), Gdx.graphics.getHeight() - boss.height);

        //Initialise enemy cannonballs
        cannonballs = new Array<combatCannonball>();
        spawncombatCannonball();
        coins = new Array<combatGold>();
        spawnCoin();
        pirateCannonballs = new Array<Rectangle>();
    }

    public void spawncombatCannonball() {
    	int x = MathUtils.random(0, Gdx.graphics.getWidth()-64);
    	int y = Gdx.graphics.getHeight()-20;
    	Vector2 position = new Vector2 (x, y);
    	combatCannonball cannonball = new combatCannonball(position, player.getObjectiveCollege());
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

        //Check for player movement with mouse
        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            pirate.x = touchPos.x - 64 / 2;
        }

        //Check for movement of player
        if(Gdx.input.isKeyPressed(Keys.LEFT) | Gdx.input.isKeyPressed(Keys.A)) pirate.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)| Gdx.input.isKeyPressed(Keys.D)) pirate.x += 200 * Gdx.graphics.getDeltaTime();

        //if key pressed shoot cannonballs
        if(Gdx.input.isKeyPressed(Keys.SPACE)) {
            shoot = true;
            time = System.currentTimeMillis();
        }

        if (System.currentTimeMillis() > time + 100) {
            if (shoot) {
                shootCannonball(pirate.x, pirate.y);
                shoot = false;
            }
        }

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

        for (Iterator<Rectangle> iter = pirateCannonballs.iterator(); iter.hasNext(); ) {
            Rectangle pirateCannonball = iter.next();
            pirateCannonball.y += 200 * Gdx.graphics.getDeltaTime();
            pirateCannonball.setPosition(pirateCannonball.x, pirateCannonball.y);
            if(pirateCannonball.y + 64 > 1200) iter.remove();
            if(pirateCannonball.overlaps(boss)) {
                iter.remove();
                hitBoss();
            }
        }

        for (Iterator<combatGold> iter = coins.iterator(); iter.hasNext(); ) {
            combatGold coin = iter.next();
            coin.update();
            if(coin.getPos().y + 64 < 0) iter.remove();
            if(coin.getRectangle().overlaps(pirate)) {
                iter.remove();
                goldCollected += 1;
                player.setGold(1);
            }
        }

        if (pirateHealth <= 0) game.setScreen(new endGameScreen(game, false, player, gameMap));

        if (bossHealth <= 0) game.setScreen(new endGameScreen(game, true, player, gameMap));

        camera.update();
        game.batch.begin();

        //Render tilemap to camera
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        //Draw player
        game.batch.draw(pirateImage, pirate.x, pirate.y);

        //render cannonballs
        for(combatCannonball cannonball: cannonballs) {
        	cannonball.render(game.batch);
        }

        //render pirate cannonballs
        for(Rectangle pirateCannonball: pirateCannonballs){
            game.batch.draw(pirateCannonballImg, pirateCannonball.x, pirateCannonball.y);
        }

        //render coins
        for (combatGold coin : coins) {
            coin.render(game.batch);
        }

        game.batch.end();

        drawUI();
    }

    public void shootCannonball(float pirateX, float pirateY){
        Rectangle pirateCannonball = new Rectangle();
        pirateCannonball.setPosition(pirateX, pirateY);
        pirateCannonballs.add(pirateCannonball);
    }

    public void hitBoss(){
        bossHealth -= player.getWeaponDamage();
    }

    public void drawUI(){
        game.batch.begin();
        pirateFont.draw(game.batch, "Gold: " + goldCollected, 5, Gdx.graphics.getHeight() - 5);
        pirateFont.draw(game.batch, "Health: " + pirateHealth, 5, Gdx.graphics.getHeight() - 40);
        pirateFont.draw(game.batch, "Boss Health Left: " + String.valueOf(bossHealth), 5, Gdx.graphics.getHeight() - 75);
        game.batch.end();
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
        //batch.dispose();
    }
}
