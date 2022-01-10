package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.yorkpiratesgame.io.YorkPirates;

public class mainMenuScreen  implements Screen {

    private static final int EXIT_BUTTON_WIDTH = 150;
    private static final int EXIT_BUTTON_HEIGHT = 75;
    private static final int PLAY_BUTTON_WIDTH = 150;
    private static final int PLAY_BUTTON_HEIGHT = 75;
    private static final int EXIT_BUTTON_Y = 100;
    private static final int PLAY_BUTTON_Y = 200;
    public static Texture backgroundTexture;
    public static Sprite backgroundSprite;

    YorkPirates game;

    Texture exitButtonActive;
    Texture exitButtonInactive;
    Texture playButtonActive;
    Texture playButtonInactive;
    ;

    public mainMenuScreen(YorkPirates game){
        this.game = game;
        playButtonActive = new Texture("menu/playActive.png");
        playButtonInactive = new Texture("menu/play.png");
        exitButtonActive = new Texture("menu/exitActive.png");
        exitButtonInactive = new Texture("menu/exit.png");
        backgroundTexture = new Texture("menu/menuBackground.png");
        backgroundSprite = new Sprite(backgroundTexture);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        backgroundSprite.draw(game.batch);

        int buttonCordX = (YorkPirates.WIDTH / 2) - (EXIT_BUTTON_WIDTH / 2);
        if (Gdx.input.getX() < buttonCordX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > buttonCordX && YorkPirates.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + EXIT_BUTTON_HEIGHT && YorkPirates.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive, buttonCordX, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, buttonCordX, EXIT_BUTTON_Y, EXIT_BUTTON_WIDTH,EXIT_BUTTON_HEIGHT);
        }

        buttonCordX = (YorkPirates.WIDTH / 2) - (PLAY_BUTTON_WIDTH / 2);
        if (Gdx.input.getX() < buttonCordX + EXIT_BUTTON_WIDTH && Gdx.input.getX() > buttonCordX && YorkPirates.HEIGHT - Gdx.input.getY() < PLAY_BUTTON_Y + PLAY_BUTTON_HEIGHT && YorkPirates.HEIGHT - Gdx.input.getY() > PLAY_BUTTON_Y){
            game.batch.draw(playButtonActive, buttonCordX, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
            if (Gdx.input.isTouched()){
                game.setScreen(new mainGameScreen(game));
            }
        } else {
            game.batch.draw(playButtonInactive, buttonCordX, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH,PLAY_BUTTON_HEIGHT);
        }

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
