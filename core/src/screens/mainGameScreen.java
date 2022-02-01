package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

import entities.LostGold;
import entities.Player;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import MapResources.GameMap;
import MapResources.TiledGameMap;

import com.badlogic.gdx.utils.Align;
import com.yorkpiratesgame.io.YorkPirates;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class mainGameScreen implements Screen {

    public static OrthographicCamera camera;
    public static GameMap gamemap;
    private Player player;
    private LostGold lostGolds;
    private LostGold lostGolds1;
    private LostGold lostGolds2;
    private LostGold lostGolds3;
    private LostGold lostGolds4;
    private LostGold lostGolds5;

    YorkPirates game;

    private BitmapFont pirateFont;


    //Set current screen to game
    public mainGameScreen (YorkPirates game){
        this.game = game;
    }

    @Override
    public void show() {
        //Initialise the Tilemap for this game
        gamemap = new TiledGameMap();

        //Initialise camera
        camera = new OrthographicCamera();
        camera.setToOrtho(false,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        camera.position.set(gamemap.getMapWidth()/2, gamemap.getMapHeight()/2, 0);
        camera.update();

        //Initialise Player & centre on the screen for rendering
        player = new Player();
        player.setXPos(gamemap.getMapWidth()/2);
        player.setYPos(gamemap.getMapHeight()/2);
        lostGolds = new LostGold(player);
        lostGolds1 = new LostGold(player);
        lostGolds2 = new LostGold(player);
        lostGolds3 = new LostGold(player);
        lostGolds4 = new LostGold(player);
        lostGolds5 = new LostGold(player);

        //Randomly select an objective at the start of each game from available colleges
        List<String> objectives = new ArrayList<>(Arrays.asList("objectiveJames", "objectiveConstantine", "objectiveHalifax", "objectiveGoodricke"));
        Random randomizer = new Random();
        String random = objectives.get(randomizer.nextInt(objectives.size()));
        //Set objectiveCollege in player
        switch (random) {
            case "objectiveJames":
                player.setObjectiveCollege("James College");
                break;
            case "objectiveConstantine":
                player.setObjectiveCollege("Constantine College");
                break;
            case "objectiveHalifax":
                player.setObjectiveCollege("Halifax College");
                break;
            case "objectiveGoodricke":
                player.setObjectiveCollege("Goodricke College");
                break;
        }


        //Create colour for labels
        Color fontColour = new Color(59 / 255f, 60 / 255f, 54 / 255f, 3 / 2);
        pirateFont = new BitmapFont(Gdx.files.internal("UI/pirateFont.fnt"));
        pirateFont.setColor(fontColour);
    }

    //drawUI() will overlay all necessary information for the user.
    //for instance their objective, score/plunder amount and health
    public void drawUI(){
        game.batch.begin();
        pirateFont.draw(game.batch, "Plunder: " + player.getGold(), 5, Gdx.graphics.getHeight() - 5);
        if(player.getRequiredPlunder() != 0) {
            pirateFont.draw(game.batch, "Requires (" + player.getRequiredPlunder() + ")", 5, Gdx.graphics.getHeight() - 40);
        }
        pirateFont.draw(game.batch, "Objective", 0,  Gdx.graphics.getHeight() - 5, Gdx.graphics.getWidth(), Align.center, false);
        pirateFont.draw(game.batch, "DEFEAT - " + player.getObjectiveCollege(), 0,  Gdx.graphics.getHeight() - 40, Gdx.graphics.getWidth(), Align.center, false);

        pirateFont.draw(game.batch, "Island Key", 5, 200);
        pirateFont.draw(game.batch, "------------", 5, 190);
        pirateFont.draw(game.batch, "Goodricke - Duck", 5, 160);
        pirateFont.draw(game.batch, "Halifax - Tank", 5, 120);
        pirateFont.draw(game.batch, "James - Spitfire", 5, 80);
        pirateFont.draw(game.batch, "Constantine - LadyBug", 5, 40);
        game.batch.end();
    }

    @Override
    public void render(float delta) {

        //Draw background colour for regions outside the game
        Gdx.gl.glClearColor(59/255f,60/255f,54/255f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //Check for player movement and update positions
        player.update();

        //Check if player has hit a gold island
        player.checkGoldCollision(lostGolds);
        player.checkGoldCollision(lostGolds1);
        player.checkGoldCollision(lostGolds2);
        player.checkGoldCollision(lostGolds3);
        player.checkGoldCollision(lostGolds4);
        player.checkGoldCollision(lostGolds5);

        if (player.collegeCombat){
            game.setScreen(new combatScreen(game, player));
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

        lostGolds.render(game.batch);
        lostGolds1.render(game.batch);
        lostGolds2.render(game.batch);
        lostGolds3.render(game.batch);
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
