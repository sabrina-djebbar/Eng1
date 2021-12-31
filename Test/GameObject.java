import java.awt.Graphics;

public abstract class GameObject{

    protected float x, y;
    protected float velX, velY;
    protected ID id;

    public GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick();
    public abstract void render(Graphics g);

    ////Setters and Getters////
    public float getX(float x){
        return x;
    }

    public void setX(float x){
        this.x = x;
    }
    
    public float getY(float y){
        return y;
    }

    public void setY(float y){
        this.y = y;
    }

    public float getVelX(float velX){
        return velX;
    }

    public void setVelX(float velX){
        this.velX = velX;
    }

    public float getVelY(float velY){
        return velY;
    }

    public void setVelY(float velY){
        this.velY = velY;
    }

    public ID getId(){
        return id;
    }

    public void setId(ID id){
        this.id = id;
    }





}
