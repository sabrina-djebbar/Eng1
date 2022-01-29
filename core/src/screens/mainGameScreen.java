package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import entities.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import MapResources.GameMap;
import MapResources.TileType;
import MapResources.TiledGameMap;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.yorkpiratesgame.io.YorkPirates;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class mainGameScreen implements Screen {

    public static OrthographicCamera camera;
    public static GameMap gamemap;
    private Player player;

    YorkPirates game;

    public Texture healthBar;
    public Stage stage;
    public Color labelColour;
    public Label.LabelStyle pirateLabelStyle;

    //Set current screen to game
    public mainGameScreen (YorkPirates game){
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera();
        //camera.position.set(camera.viewportWidth, camera.viewportHeight, 0);
        camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
        //camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.update();

        //Initialise the Tilemap for this game
        gamemap = new TiledGameMap();

        //Initialise Player & centre on the screen for rendering
        player = new Player();
        player.setXPos(Gdx.graphics.getWidth()/2);
        player.setYPos(Gdx.graphics.getHeight()/2);

        //Randomly select an objective at the start of each game from available colleges
        List<String> objectives = new ArrayList<>(Arrays.asList("objectiveJames", "objectiveConstantine", "objectiveHalifax", "objectiveGoodricke"));
        Random randomizer = new Random();
        String random = objectives.get(randomizer.nextInt(objectives.size()));
        //Set objectiveCollege in player
        if(random == "objectiveJames"){player.setObjectiveCollege("James College");}
        else if (random == "objectiveConstantine")player.setObjectiveCollege("Constantine College");
        else if (random == "objectiveHalifax"){player.setObjectiveCollege("Halifax College");}
        else if (random == "objectiveGoodricke"){player.setObjectiveCollege("Goodricke College");}

        //Assign stage for user interface
        stage = new Stage(new ScreenViewport());

        //Create colour for labels
        labelColour = new Color(59/255f,60/255f,54/255f, 3/2);
        //Create label style
        pirateLabelStyle = new Label.LabelStyle();
        BitmapFont myFont = new BitmapFont(Gdx.files.internal("UI/pirateFont.fnt"));
        pirateLabelStyle.font = myFont;

    }

    //drawUI() will overlay all necessary information for the user.
    //for instance their objective, score/plunder amount and health
    public void drawUI(){
        //Create score label which will be
        Label ScoreLabel = new Label(("Plunder: " + player.getGold()),pirateLabelStyle);
        ScoreLabel.setSize(Gdx.graphics.getWidth(),12);
        ScoreLabel.setPosition(0,Gdx.graphics.getHeight()-12*2);
        ScoreLabel.setAlignment(Align.left);
        ScoreLabel.setColor(labelColour);
        stage.addActor(ScoreLabel);

        Label ObjectiveLabel = new Label("Objective",pirateLabelStyle);
        ObjectiveLabel.setSize(Gdx.graphics.getWidth(),12);
        ObjectiveLabel.setPosition(0,Gdx.graphics.getHeight()-30);
        ObjectiveLabel.setAlignment(Align.center);
        ObjectiveLabel.setColor(labelColour);
        ObjectiveLabel.setFontScale(2);
        stage.addActor(ObjectiveLabel);

        Label ObjectiveCollegeLabel = new Label(("DEFEAT " + player.getObjectiveCollege()),pirateLabelStyle);
        ObjectiveCollegeLabel.setSize(Gdx.graphics.getWidth(),10);
        ObjectiveCollegeLabel.setPosition(0,Gdx.graphics.getHeight()-80);
        ObjectiveCollegeLabel.setAlignment(Align.center);
        ObjectiveCollegeLabel.setColor(labelColour);
        stage.addActor(ObjectiveCollegeLabel);

        //Draw UI Stage
        stage.act();
        stage.draw();
    }

    @Override
    public void render(float delta) {

        //Draw background colour for regions outside the game
        Gdx.gl.glClearColor(59/255f,60/255f,54/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Check for player movement and update positions
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

        //This series of conditional statements will check to make sure the sprite Player is within the margin of the game
        //And will reset the relevant axis position to keep them within the bounds
        //First the maximum bounds (top & right sides)
        if (player.getXPos() > gamemap.getMapWidth() - player.getWidth() * 2) player.setXPos(gamemap.getMapWidth() - player.getWidth() * 2);
        if (player.getYPos() > gamemap.getMapHeight() - player.getHeight() * 2) player.setYPos(gamemap.getMapHeight() - player.getHeight() * 2);
        //Secondly the minimum bounds (bottom & left sides)
        if (player.getXPos() < player.getWidth()) player.setXPos(player.getWidth());
        if (player.getYPos() < player.getHeight()) player.setYPos(player.getHeight());

        //Moves camera if player is pushing against margin and more map can be rendered
        Vector3 camPos = camera.position;
        //Borders of where the player can move on the screen
        int widthBorder = (Gdx.graphics.getWidth()/2) - player.getWidth();
        int heightBorder = (Gdx.graphics.getHeight()/2) - player.getHeight();

        //Right-side margin check
        if (player.getXPos() > camPos.x + widthBorder - player.getWidth()){
            camPos.x = player.getXPos() - widthBorder + player.getWidth();
        }
        //Left-side margin check
        if (player.getXPos() < camPos.x - widthBorder){
            camPos.x = player.getXPos() + widthBorder;
        }
        //Top-side margin check
        if (player.getYPos() > camPos.y + heightBorder - player.getHeight()){
            camPos.y = player.getYPos() - heightBorder + player.getHeight();
        }
        //Bottom-side margin check
        if (player.getYPos() < camPos.y - heightBorder) {
            camPos.y = player.getYPos() + heightBorder;
        }
        //Update camera position
        camera.position.set(camPos);
        camera.update();

        //Begin rendering batch
        game.batch.begin();
        //Render tilemap to camera
        gamemap.render(camera);
        //Render player
        player.render(game.batch);
        //Finish rendering batch
        game.batch.end();

        //Draw the user interface
        drawUI();

    }

    @Override
    public void resize(int width, int height) {
        //Set view size of camera
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

    //Dispose of img's and other batch objects to save on memory space
    @Override
    public void dispose() {
    }
}
