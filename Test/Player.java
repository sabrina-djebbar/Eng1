import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Player extends GameObject{

    public Player(float x, float y, ID id) {
        super(x, y, id);
    }

    public static int bob = Game.WIDTH / 25;


    @Override
    public void tick() {
        x -= 5;

        if(x < -150) x=Game.WIDTH;
    }

    @Override
    public void render(Graphics g) {
        Image PlayerImg = Toolkit.getDefaultToolkit().getImage("playerImg.png");
        g.drawImage(PlayerImg, (int)x, (int)y, null);
    }
        
}
