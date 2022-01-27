package screens;

import java.util.Iterator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import entities.Player;
import com.yorkpiratesgame.io.YorkPirates;

public class mainGameScreen implements Screen {
	
	static final int WORLD_WIDTH = 100;
	static final int WORLD_HEIGHT = 100;

    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer tiledMapRenderer;
    private OrthographicCamera camera;
    private int mapHeight;
    private int mapWidth;
    
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
        mapHeight = ((int) tiledMap.getProperties().get("tileheight")) * ((int) tiledMap.getProperties().get("height"));
        mapWidth = ((int) tiledMap.getProperties().get("tilewidth")) * ((int) tiledMap.getProperties().get("width"));
        //camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        //camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        player = new Player();
        player.setXPos(Gdx.graphics.getWidth()/2);
        player.setYPos(Gdx.graphics.getHeight()/2);
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        
        if (player.getXPos() > mapWidth - player.getHeight() * 2) player.setXPos(mapWidth - player.getHeight() * 2);
        if (player.getYPos() > mapHeight - player.getHeight() * 2) player.setYPos(mapHeight - player.getHeight() * 2);
        
        Vector3 camPos = camera.position;
        int widthBorder = (Gdx.graphics.getWidth()/2) - player.getHeight();
        int heightBorder = (Gdx.graphics.getHeight()/2) - player.getHeight();
        if (player.getXPos() > camPos.x + widthBorder - player.getHeight()){
        	camPos.x = player.getXPos() - widthBorder + player.getHeight();
        }
        if (player.getXPos() < camPos.x - widthBorder){
        	camPos.x = player.getXPos() + widthBorder;
        }
        if (player.getYPos() > camPos.y + heightBorder - player.getHeight()){
        	camPos.y = player.getYPos() - heightBorder + player.getHeight();
        }
        if (player.getYPos() < camPos.y - heightBorder) {
        	camPos.y = player.getYPos() + heightBorder;
        }
        camera.position.set(camPos);
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
