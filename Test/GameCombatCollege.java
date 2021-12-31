import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class GameCombatCollege extends Canvas implements Runnable{

    public static int WIDTH = 1024, HEIGHT = 768;
    public String title = "Scone Zone: York Pirates!";

    private Thread thread;
    private Boolean isRunning = false;

    //Instances
    private Handler handler;

    //Constructor
    public GameCombatCollege(){
        new Window(WIDTH, HEIGHT, title, this);
        start();

        init();
        //Below Here
        handler.addObject(new Player(100, 100, ID.Player));
        
    }

    //Object Constructor
    private void init(){
        handler = new Handler();
    }

    //Starts thread
    private synchronized void start(){
        if(isRunning) return;

        thread = new Thread(this);
        thread.start();
        isRunning = true;
    }

    //Ends Thread
    private synchronized void stop(){
        if(!isRunning) return;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }

    //gameLoop
    public void run(){
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                frames = 0;
            }
        }
        stop();
    }

    //Updates the game
    private void tick(){
        handler.tick();
    }

    //Renders the game
    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        //Graphics//
        Color water = new Color(33,55,145);
        g.setColor(water);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        ////////////

        handler.render(g);

        bs.show();
        g.dispose();
    }

    public static void main(String[] args) {
        new Game();
        
    }

}