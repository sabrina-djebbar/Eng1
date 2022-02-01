package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.yorkpiratesgame.io.YorkPirates;

import entities.Player;

public class endGameScreen implements Screen {

    YorkPirates game;
    private Player player;
    
    private static final int EXIT_BUTTON_Y = 300;
    
    private OrthographicCamera camera;
    private boolean survived;
    private String collegeName;
    private String gameMap;
    private TiledMap tiledMap;
    private TiledMapRenderer tiledMapRenderer;

    private BitmapFont pirateFont;
    private Color fontColour;

    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    public endGameScreen(YorkPirates game, boolean survived, Player player, String gameMap) {
        this.game = game;
        this.survived = survived;
        this.player = player;
        this.gameMap = gameMap;
    }

    @Override
    public void show() {
    	//Create colour for labels
        fontColour = new Color(225/255f,225/255f,225/255f, 3/2);
        pirateFont = new BitmapFont(Gdx.files.internal("UI/pirateFont.fnt"));
        pirateFont.setColor(fontColour);

        exitButtonActive = new Texture("menu/exitActive.png");
        exitButtonInactive = new Texture("menu/exit.png");

        collegeName = player.getObjectiveCollege();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        tiledMap = new TmxMapLoader().load(gameMap);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        
    }

    @Override
    public void render(float delta) {
    	ScreenUtils.clear(0.1f, 0.4f, 0.99f, 1);
        camera.update();

        game.batch.begin();

        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();

        int buttonCordX = (YorkPirates.WIDTH / 2) - (exitButtonActive.getWidth() / 2);
        if (Gdx.input.getX() < buttonCordX + exitButtonActive.getWidth() && Gdx.input.getX() > buttonCordX && YorkPirates.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + exitButtonActive.getHeight() && YorkPirates.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive, buttonCordX, EXIT_BUTTON_Y, exitButtonActive.getWidth(),exitButtonActive.getHeight());
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, buttonCordX, EXIT_BUTTON_Y, exitButtonActive.getWidth(),exitButtonActive.getHeight());
        }

    	drawUI();

    	game.batch.end();
    }
    
    
    public void drawUI(){
    	String outputString = "Score: " +  player.getGold();
        pirateFont.draw(game.batch, outputString, Gdx.graphics.getWidth()/2 - outputString.length()*7.7f, Gdx.graphics.getHeight()/2 - 40);
        if (survived) {
        	 outputString = "Well done! You beat " + collegeName;
             pirateFont.draw(game.batch, outputString, Gdx.graphics.getWidth()/2 - outputString.length()*7.7f, Gdx.graphics.getHeight()/2);
        }
        else {
        	outputString = "Bad Luck! You died";
        	pirateFont.draw(game.batch, outputString, Gdx.graphics.getWidth()/2 - outputString.length()*7.7f, Gdx.graphics.getHeight()/2 + 100);
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

    }
}
