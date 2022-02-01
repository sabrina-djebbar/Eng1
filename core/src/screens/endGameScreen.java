package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.ScreenUtils;
import com.yorkpiratesgame.io.YorkPirates;

public class endGameScreen implements Screen {

    YorkPirates game;
    
    private static final int EXIT_BUTTON_Y = 300;
    
    private OrthographicCamera camera;
    private boolean survived;
    private BitmapFont pirateFont;
    private Color fontColour;
    private Texture collegeImg;
    private String collegeName = "Halifax";
    
    private Texture exitButtonActive;
    private Texture exitButtonInactive;

    public endGameScreen(YorkPirates game) {
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
    
    public endGameScreen(YorkPirates game, boolean survived, String objectiveCollege) {
        this.game = game;
        this.survived = survived;
        collegeName = objectiveCollege;
        System.out.println(collegeName);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void show() {
    	//Create colour for labels
        fontColour = new Color(225/255f,225/255f,225/255f, 3/2);
        pirateFont = new BitmapFont(Gdx.files.internal("UI/pirateFont.fnt"));
        pirateFont.setColor(fontColour);
        collegeImg = new Texture("Colleges/" + collegeName + ".png");
        exitButtonActive = new Texture("menu/exitActive.png");
        exitButtonInactive = new Texture("menu/exit.png");
        
    }

    @Override
    public void render(float delta) {
    	ScreenUtils.clear(0.1f, 0.4f, 0.99f, 1);
        camera.update();
    	game.batch.begin();
    	if (survived) {
    		game.batch.draw(collegeImg,
    				Gdx.graphics.getWidth()/2 - collegeImg.getWidth()/2,
    				Gdx.graphics.getHeight()/2 - collegeImg.getHeight()/2 + 120);
    	}
    	drawUI();
    	int buttonCordX = (YorkPirates.WIDTH / 2) - (exitButtonActive.getWidth() / 2);
        if (Gdx.input.getX() < buttonCordX + exitButtonActive.getWidth() && Gdx.input.getX() > buttonCordX && YorkPirates.HEIGHT - Gdx.input.getY() < EXIT_BUTTON_Y + exitButtonActive.getHeight() && YorkPirates.HEIGHT - Gdx.input.getY() > EXIT_BUTTON_Y){
            game.batch.draw(exitButtonActive, buttonCordX, EXIT_BUTTON_Y, exitButtonActive.getWidth(),exitButtonActive.getHeight());
            if (Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(exitButtonInactive, buttonCordX, EXIT_BUTTON_Y, exitButtonActive.getWidth(),exitButtonActive.getHeight());
        }
    	game.batch.end();
    }
    
    
    public void drawUI(){
    	String outputString = "Score: 11234";
        pirateFont.draw(game.batch, outputString, Gdx.graphics.getWidth()/2 - outputString.length()*7.7f, Gdx.graphics.getHeight()/2 - 40);
        if (survived) {
        	 outputString = "Well done! You beat " + collegeName;
             pirateFont.draw(game.batch, outputString, Gdx.graphics.getWidth()/2 - outputString.length()*7.7f, Gdx.graphics.getHeight()/2 + 300);
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
