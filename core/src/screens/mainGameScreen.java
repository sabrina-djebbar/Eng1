package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import entities.Player;

import com.badlogic.gdx.math.Vector3;
import com.yorkpiratesgame.io.GameMap;
import com.yorkpiratesgame.io.TileType;
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
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 , 0);
        camera.update();
        gamemap = new TiledGameMap();
        player = new Player();
        player.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
    }


    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(59/255f,60/255f,54/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.update();

        if (Gdx.input.justTouched()){
            Vector3 pos = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
            TileType type = gamemap.getTileTypeByLocation(1, pos.x, pos.y);

            if(type != null){
                System.out.println("You clicked on tile with id " + type.getId() + " " + type.isCollidable());
            }
            else{
                System.out.println("NULL");
            }
        }

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
