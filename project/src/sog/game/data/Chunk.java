package sog.game.data;

import java.util.HashSet;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.interfaces.Draw;

import sog.game.data.object.GameObject;

public class Chunk implements Draw {

    private HashSet<GameObject> objects;
    
    public Chunk() {
        objects = new HashSet<GameObject>();
    }
    
    public void addObj(GameObject obj) {
        objects.add(obj);
    }
    
    public void delObj(GameObject obj){
        objects.remove(obj);
    }
    
    @Override
    public void draw(SpriteBatch batch) {
        if(objects.size() > 0){
            for(GameObject obj: objects){
                obj.draw(batch);
            }
        }
    }

    public GameObject selectObj(Cursor cursor) {
        if(objects.size() > 0){
            for(GameObject obj: objects){
                if(obj.tileX() == cursor.tileX() && obj.tileY() == cursor.tileY()){
                    return obj;
                }
            }
        }
        
        return null;
    }
}
