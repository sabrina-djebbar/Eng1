package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.yorkpiratesgame.io.YorkPirates;

public class mainGameScreen implements Screen {

    public static final float SPEED = 120;

    Sprite ship;
    private static Texture img;
    float x;
    float y;
    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    YorkPirates game;

    public mainGameScreen (YorkPirates game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.update();
        tiledMap = new TmxMapLoader().load("GameMap/mainMap(1).tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        x = 100;
        y = 100;
    }

    @Override
    public void show() {
        img = new Texture(Gdx.files.internal("Ships/ship (1).png"));
        ship = new Sprite(img);
        ship.setPosition(x, y);
    }

    @Override
    public void render(float delta) {
        String direction = "DOWN";
        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += SPEED * Gdx.graphics.getDeltaTime();
            ship.setPosition(x, y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= SPEED * Gdx.graphics.getDeltaTime();
            ship.setPosition(x, y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= SPEED * Gdx.graphics.getDeltaTime();
            ship.setPosition(x, y);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += SPEED * Gdx.graphics.getDeltaTime();
            ship.setPosition(x, y);
        }

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        ship.draw(game.batch);
        camera.update();
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

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

    }
}
