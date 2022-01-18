package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.yorkpiratesgame.io.YorkPirates;

public class combatScreen implements Screen {

    YorkPirates game;
    private OrthographicCamera camera;
    /* game.batch is the main SpriteBatch of the whole game, saves making a new one for each screen
    * as when the game window is closed they all get disposed. */

    public combatScreen(YorkPirates game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, game.WIDTH, game.HEIGHT);

    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {

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
