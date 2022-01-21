package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.yorkpiratesgame.io.YorkPirates;
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
    private Array<Rectangle> cannonballs;
    private long lastDropTime;
    private Texture pirateImage;
    private Texture cannonImage;
    /* game.batch is the main SpriteBatch of the whole game, saves making a new one for each screen
    * as when the game window is closed they all get disposed. */

    public combatScreen(YorkPirates game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);
        batch = new SpriteBatch();
        pirateImage = new Texture(Gdx.files.internal("pirate-icon.png"));
        cannonImage = new Texture(Gdx.files.internal("Cannon_Ball.png"));
        pirate = new Rectangle();
        pirate.x = game.WIDTH / 2 - 256 / 2;
        pirate.y = 20;
        pirate.width = 256;
        pirate.height = 256;

        cannonballs = new Array<Rectangle>();
        spawnCannonball();
    }

    public void spawnCannonball() {
        Rectangle cannonball = new Rectangle();
        cannonball.x = MathUtils.random(0, 800-64);
        cannonball.y = 480;
        cannonball.width = 64;
        cannonball.height = 64;
        cannonballs.add(cannonball);
        lastDropTime = TimeUtils.nanoTime();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(pirateImage, pirate.x, pirate.y);
        for(Rectangle cannonball: cannonballs) {
            batch.draw(cannonImage, cannonball.x, cannonball.y);
        }
        batch.end();

        if(Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            pirate.x = touchPos.x - 64 / 2;
        }

        if(Gdx.input.isKeyPressed(Keys.LEFT)) pirate.x -= 200 * Gdx.graphics.getDeltaTime();
        if(Gdx.input.isKeyPressed(Keys.RIGHT)) pirate.x += 200 * Gdx.graphics.getDeltaTime();

        if(pirate.x < 0) pirate.x = 0;
        if(pirate.x > 800 - 64) pirate.x = 800 - 64;

        // check if we need to create a new raindrop
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnCannonball();

        for (Iterator<Rectangle> iter = cannonballs.iterator(); iter.hasNext(); ) {
            Rectangle raindrop = iter.next();
            raindrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if(raindrop.y + 64 < 0) iter.remove();
            if(raindrop.overlaps(pirate)) {
                iter.remove();
            }
        }
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
        cannonImage.dispose();
        pirateImage.dispose();
        batch.dispose();
    }
}
