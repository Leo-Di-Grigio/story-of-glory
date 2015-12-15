package sog.game.data;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.interfaces.Draw;
import com.owlengine.resources.Assets;
import com.owlengine.tools.Log;
import com.owlengine.tools.Tools;

import sog.game.Const;
import sog.game.data.object.GameObject;

public class GameMap implements Draw {

    // data
    private byte  [][] map;
    private Chunk [][] chunks;
    private HashMap<Integer, GameObject> objects;
    
    // textures loaded
    private static final int TEX_COUNT = 3;
    private Texture [] tex;
    
    // render
    private int minX, minY, maxX, maxY;
    private int chunkMinX, chunkMinY, chunkMaxX, chunkMaxY;
    
    public GameMap() {
        loadResources();

        objects = new HashMap<Integer, GameObject>();
        map = new byte[Const.MAP_SIZE_TILES][Const.MAP_SIZE_TILES];
        chunks = new Chunk[Const.MAP_SIZE_CHUNKS][Const.MAP_SIZE_CHUNKS];
        
        for(int i = 0; i < Const.MAP_SIZE_CHUNKS; ++i){
            for(int j = 0; j < Const.MAP_SIZE_CHUNKS; ++j){
                chunks[i][j] = new Chunk();
            }
        }
        
        generateMap();
    }

    private void generateMap() {
        for(int i = 0; i < Const.MAP_SIZE_TILES; ++i){
            for(int j = 0; j < Const.MAP_SIZE_TILES; ++j){
                map[i][j] = (byte)Tools.rand(0, 1);
            }
        }
    }

    private void loadResources() {
        tex = new Texture[TEX_COUNT];
        
        tex[0] = Assets.getTex(Const.TEX_TILE_GRASS);
        tex[1] = Assets.getTex(Const.TEX_TILE_DUST);
        tex[2] = Assets.getTex(Const.TEX_TILE_WATER);
    }

    public void update(OrthographicCamera camera){
        updateRenderBounds(camera);
        updateObjects();
    }
    
    private void updateRenderBounds(OrthographicCamera camera) {
        float w = (Gdx.graphics.getWidth() * camera.zoom)/2;
        float h = (Gdx.graphics.getHeight() * camera.zoom)/2;
        
        minX = Math.max((int)(camera.position.x - w)/Const.MAP_SIZE_TILE_PIXELS, 0);
        maxX = Math.min((int)(camera.position.x + w)/Const.MAP_SIZE_TILE_PIXELS, Const.MAP_SIZE_TILES - 1);
        
        minY = Math.max((int)(camera.position.y - h)/Const.MAP_SIZE_TILE_PIXELS, 0);
        maxY = Math.min((int)(camera.position.y + h)/Const.MAP_SIZE_TILE_PIXELS, Const.MAP_SIZE_TILES - 1);
        
        chunkMinX = minX/Const.MAP_CHUNK_SIZE_TILES;
        chunkMaxX = maxX/Const.MAP_CHUNK_SIZE_TILES;
        
        chunkMinY = minY/Const.MAP_CHUNK_SIZE_TILES;
        chunkMaxY = maxY/Const.MAP_CHUNK_SIZE_TILES;
    }

    private void updateObjects() {
        for(GameObject obj: objects.values()){
            obj.update(this);
        }
    }

    public void addObj(GameObject obj) {
        objects.put(obj.id, obj);
        chunks[obj.chunkX()][obj.chunkY()].addObj(obj);
    }
    
    public void delObj(GameObject obj){
        objects.remove(obj.id);
        chunks[obj.chunkX()][obj.chunkY()].delObj(obj);
    }

    public void delObj(int id) {
        delObj(objects.get(id));
    }
    
    public void setPos(GameObject obj, float x, float y){
        if(x < 0 || x > Const.MAP_SIZE_PIXELS || y < 0 || y > Const.MAP_SIZE_PIXELS){
            Log.err("GameMap.setPos(): out of bound warning");
        }
        
        int oldx = obj.chunkX();
        int oldy = obj.chunkY();
        
        obj.setPos(x, y);
        
        int newx = obj.chunkX();
        int newy = obj.chunkY();
        
        if(oldx - newx != 0 || oldy - newy != 0){
            chunks[oldx][oldy].delObj(obj);
            chunks[newx][newy].addObj(obj);
        }
    }

    public GameObject selectObj(Cursor cursor) {
        if(cursor.inBound()){
            return chunks[cursor.chunkX()][cursor.chunkY()].selectObj(cursor);
        }
        else{
            return null;
        }
    }
    
    @Override
    public void draw(SpriteBatch batch) {
        for(int i = minX; i <= maxX; ++i){
            for(int j = minY; j <= maxY; ++j){
                batch.draw(tex[map[i][j]], i*Const.MAP_SIZE_TILE_PIXELS, j*Const.MAP_SIZE_TILE_PIXELS);
            }
        }
        
        for(int i = chunkMinX; i <= chunkMaxX; ++i){
            for(int j = chunkMinY; j <= chunkMaxY; ++j){
                chunks[i][j].draw(batch);
            }
        }
    }
}