package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import entities.Player;
import com.yorkpiratesgame.io.YorkPirates;

public class mainGameScreen implements Screen {

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;

    private Player player;
    YorkPirates game;

    public mainGameScreen (YorkPirates game){
        this.game = game;
    }

    @Override
    public void show() {
        tiledMap = new TmxMapLoader().load("GameMap/mainMap(1).tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        player = new Player();
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        player.update();

        tiledMapRenderer.setView(camera);
        game.batch.begin();
        tiledMapRenderer.render();
        player.render(game.batch);
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = YorkPirates.WIDTH;
        camera.viewportHeight = YorkPirates.HEIGHT;
        camera.update();
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
        tiledMap.dispose();
        tiledMapRenderer.dispose();
    }
}
