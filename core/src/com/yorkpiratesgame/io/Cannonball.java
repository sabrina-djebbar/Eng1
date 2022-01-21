package com.yorkpiratesgame.io;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;

public class Cannonball extends ApplicationAdapter {
    private Texture cannonImage;

    @Override

    public void create() {
        cannonImage = new Texture(Gdx.files.internal("Cannon_Ball.png"));
    }

}

