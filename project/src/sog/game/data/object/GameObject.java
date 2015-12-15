package sog.game.data.object;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.interfaces.Draw;
import com.owlengine.resources.Assets;

import sog.game.Const;
import sog.game.data.GameMap;

public class GameObject implements Draw {

    private static int ID;
    
    // data
    public final int id;
    private Sprite sprite;
    
    public GameObject(){
        id = ID++;
        sprite = new Sprite(Assets.getTex(Const.TEX_UNIT));
    }

    public int tileX() {
        return (int)(sprite.getX()/Const.MAP_SIZE_TILE_PIXELS);
    }
    
    public int tileY() {
        return (int)(sprite.getY()/Const.MAP_SIZE_TILE_PIXELS);
    }
    
    public int chunkX() {
        return (int)(sprite.getX()/Const.MAP_CHUNK_SIZE_PIXELS);
    }

    public int chunkY() {
        return (int)(sprite.getY()/Const.MAP_CHUNK_SIZE_PIXELS);
    }

    public void setPos(float x, float y) {
        sprite.setPosition(x, y);
    }
    
    public void update(GameMap map){
        
    }
    
    @Override
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
    }
}
