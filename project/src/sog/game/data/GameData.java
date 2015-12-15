package sog.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.owlengine.input.UserInput;
import com.owlengine.resources.Assets;

import sog.game.Const;
import sog.game.data.object.GameObject;

public class GameData implements Disposable {
    
    private GameMap map;
    private Cursor cursor;
    
    // camera
    private float cameraSpeed;
    private float zoomSpeed;

    // font
    private BitmapFont font;
    
    // selecting
    private GameObject selectedObj;
    
    public GameData() {
        map = new GameMap();
        cursor = new Cursor();
        
        font = Assets.getFont(Const.FONT);
        
        loadTestData();
    }

    private void loadTestData() {
        addObj(new GameObject());
    }
    
    private void addObj(GameObject obj){
        map.addObj(obj);
    }

    public void update(OrthographicCamera camera) {
        input(camera);
        
        map.update(camera);
        cursor.update(camera);
    }

    private void input(OrthographicCamera camera) {
        cameraSpeed = Const.CAMERA_SPEED_BASE * camera.zoom;
        
        if(UserInput.key(Keys.W)){
            camera.translate(0.0f, cameraSpeed);
        }
        else if(UserInput.key(Keys.S)){
            camera.translate(0.0f, -cameraSpeed);
        }
        
        if(UserInput.key(Keys.D)){
            camera.translate(cameraSpeed, 0.0f);
        }
        else if(UserInput.key(Keys.A)){
            camera.translate(-cameraSpeed, 0.0f);
        }
        
        camera.update();
    }
    
    public void zoom(OrthographicCamera camera, int value){
        zoomSpeed = Const.ZOOM_SPEED_BASE * camera.zoom * value;
        
        if(camera.zoom + zoomSpeed > Const.ZOOM_MIN && camera.zoom + zoomSpeed < Const.ZOOM_MAX){
            camera.zoom += zoomSpeed;
        }
    }
    
    public void select() {
        selectedObj = map.selectObj(cursor);
    }
    
    public void moveObj() {
        if(selectedObj != null && cursor.inBound()){
            float posx = cursor.tileX()*Const.MAP_SIZE_TILE_PIXELS;
            float posy = cursor.tileY()*Const.MAP_SIZE_TILE_PIXELS;
            map.setPos(selectedObj, posx, posy);
        }
    }
    
    public void draw(SpriteBatch batch) {
        map.draw(batch);
        cursor.draw(batch);
    }

    public void drawHUD(SpriteBatch batch) {
        font.draw(batch, "FPS: " + Gdx.graphics.getFramesPerSecond(), 0, 15);
        font.draw(batch, "Cursor x: " + cursor.tileX() + " y: " + cursor.tileY(), 0, 30);
        
        if(selectedObj != null){
            font.draw(batch, "Obj selected id: " + selectedObj.id, 0, 45);    
        }
        else{
            font.draw(batch, "No obj selected", 0, 45);
        }
    }
    
    @Override
    public void dispose() {
        
    }
}
