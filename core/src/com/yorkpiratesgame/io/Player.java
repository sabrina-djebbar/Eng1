package com.yorkpiratesgame.io;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Player extends Sprite {

    private TextureAtlas textureAtlas;

    public Player(TextureAtlas atlas){
        super(atlas.getRegions().get(0));
        textureAtlas = atlas;
    }

    public void setTexture(String textureName){
        setRegion(textureAtlas.findRegion(textureName));
    }
}
