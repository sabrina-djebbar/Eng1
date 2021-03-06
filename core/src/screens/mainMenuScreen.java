package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.yorkpiratesgame.io.YorkPirates;

public class mainMenuScreen  implements Screen {

    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 200;

    TiledMap tiledMap;
    OrthographicCamera camera;
    TiledMapRenderer tiledMapRenderer;

    YorkPirates game;

    Texture title;
    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    Texture watermarkBestCollege;

    //Set current screen to game window
    public mainMenuScreen(YorkPirates game){
        this.game = game;
    }

    @Override
    public void show() {
        //Assign menu images to Textures for rendering
        title = new Texture("Menu/titleHeader.png");
        playButtonActive = new Texture("Menu/playActive.png");
        playButtonInactive = new Texture("Menu/play.png");
        exitButtonActive = new Texture("Menu/exitActive.png");
        exitButtonInactive = new Texture("Menu/exit.png");
        watermarkBestCollege = new Texture("Menu/Watermark.png");

        camera = new OrthographicCamera();
        //camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        //camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.update();

        //Initialise the tilemap for the main menu
        tiledMap = new TmxMapLoader().load("GameMaps/menuMap.tmx");
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
    }

    @Override
    public void render(float delta) {

        //Draw background colour for regions outside the game
        Gdx.gl.glClearColor(59/255f,60/255f,54/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Begin rendering batch
        game.batch.begin();
        //Render tilemap to camera
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        //Render title texture
        game.batch.draw(title, ((YorkPirates.WIDTH / 2) - (title.getWidth() / 2)), 600);

        //Check if exit button is pressed and exit game
        int buttonCordX = (YorkPirates.WIDTH / 2) - (exitButtonActive.getWidth() / 2);
        if (Gdx.input.getX() < buttonCordX + exitButtonActive.getWidth() && Gdx.input.getX() > buttonCordX && YorkPirates.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + exitButtonActive.getHeight() && YorkPirates.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive, buttonCordX, EXIT_BUTTON_Y, exitButtonActive.getWidth(),exitButtonActive.getHeight());
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, buttonCordX, EXIT_BUTTON_Y, exitButtonActive.getWidth(),exitButtonActive.getHeight());
        }

        //Check if play button is pressed and move to game screen
        buttonCordX = (YorkPirates.WIDTH / 2) - (playButtonActive.getWidth() / 2);
        if (Gdx.input.getX() < buttonCordX + playButtonActive.getWidth() && Gdx.input.getX() > buttonCordX && YorkPirates.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + playButtonActive.getHeight() && YorkPirates.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
            game.batch.draw(playButtonActive, buttonCordX, PLAY_BUTTON_Y, playButtonActive.getWidth(),playButtonActive.getHeight());
            if (Gdx.input.isTouched()){
                game.setScreen(new mainGameScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, buttonCordX, PLAY_BUTTON_Y, playButtonActive.getWidth(),playButtonActive.getHeight());
        }

        game.batch.draw(watermarkBestCollege, Gdx.graphics.getWidth() - watermarkBestCollege.getWidth(), 0);

        //Finish rendering batch
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

    //Dispose of img's and other batch objects to save on memory space
    @Override
    public void dispose() {
        title.dispose();
        exitButtonActive.dispose();
        exitButtonInactive.dispose();
        playButtonActive.dispose();
        playButtonInactive.dispose();
        tiledMap.dispose();
    }
}
