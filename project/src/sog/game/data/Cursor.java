package sog.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.owlengine.input.UserInput;
import com.owlengine.interfaces.Draw;
import com.owlengine.resources.Assets;

import sog.game.Const;

public class Cursor implements Draw {

    private Vector3 pointVector;
    private Vector3 unprojectVector;
    
    private Texture texture;

    public Cursor() {
        TextureParameter param = new TextureParameter();
        param.genMipMaps = true;
        param.minFilter = TextureFilter.Linear;
        
        Assets.loadTex(Const.TEX_CURSOR, param);
        texture = Assets.getTex(Const.TEX_CURSOR);
        
        pointVector = new Vector3();
        unprojectVector = new Vector3();
    }

    public void update(OrthographicCamera camera) {
        pointVector.set(UserInput.mouseX(), Gdx.graphics.getHeight() - UserInput.mouseY(), 0.0f);
        unprojectVector = camera.unproject(pointVector);
    }
    
    public float x(){
        return unprojectVector.x;
    }
    
    public float y(){
        return unprojectVector.y;
    }

    public int tileX() {
        return (int)(unprojectVector.x/Const.MAP_SIZE_TILE_PIXELS);
    }

    public int tileY() {
        return (int)(unprojectVector.y/Const.MAP_SIZE_TILE_PIXELS);
    }

    public int chunkX() {
        return (int)(unprojectVector.x/Const.MAP_CHUNK_SIZE_PIXELS);
    }

    public int chunkY() {
        return (int)(unprojectVector.y/Const.MAP_CHUNK_SIZE_PIXELS);
    }

    public boolean inBound() {
        return (unprojectVector.x >= 0 && 
                unprojectVector.x < Const.MAP_SIZE_PIXELS &&
                unprojectVector.y >= 0 &&
                unprojectVector.y < Const.MAP_SIZE_PIXELS);
    }
    
    @Override
    public void draw(SpriteBatch batch) {
        if(inBound()){
            batch.draw(texture, tileX()*Const.MAP_SIZE_TILE_PIXELS, tileY()*Const.MAP_SIZE_TILE_PIXELS);
        }
    }
}