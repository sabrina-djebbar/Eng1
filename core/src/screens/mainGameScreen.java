package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import entities.Player;

import com.yorkpiratesgame.io.GameMap;
import com.yorkpiratesgame.io.TiledGameMap;
import com.yorkpiratesgame.io.YorkPirates;

public class mainGameScreen implements Screen {

    public static OrthographicCamera camera;
    public static GameMap gamemap;
    private Player player;
    private float playerPosX;
    YorkPirates game;

    public mainGameScreen (YorkPirates game){
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        gamemap = new TiledGameMap();
        player = new Player();
        //Tile-size is 24x24. Therefore positions need be multiplied by 24
        player.setPosition(1200,2880);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(59/255f,60/255f,54/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.set(player.getXPos() - (player.getPlayerWidth() / 2), player.getYPos()  - (player.getPlayerHeight() / 2), 0);
        camera.update();
        player.update();

        game.batch.begin();
        gamemap.render(camera);
        player.render(game.batch);
        game.batch.end();


    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
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

    }
}
