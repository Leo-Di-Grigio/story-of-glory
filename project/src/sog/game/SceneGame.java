package sog.game;

import com.badlogic.gdx.assets.loaders.TextureLoader.TextureParameter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.interfaces.Event;
import com.owlengine.resources.Assets;
import com.owlengine.scenes.Scene;

import sog.game.data.GameData;

public class SceneGame extends Scene {
    
    private OrthographicCamera camera;
    private GameData data;
    
    public SceneGame(OrthographicCamera camera) {
        loadResources();
        
        this.camera = camera;
        this.data = new GameData();
    }
    
    private void loadResources() {
        TextureParameter param = new TextureParameter();
        param.genMipMaps = true;
        param.minFilter = TextureFilter.Linear;
        
        //
        Assets.loadTex(Const.TEX_TILE_GRASS, param);
        Assets.loadTex(Const.TEX_TILE_DUST, param);
        Assets.loadTex(Const.TEX_TILE_WATER, param);
        Assets.loadTex(Const.TEX_UNIT, param);
        
        //
        Assets.loadFont(Const.FONT);
    }

    @Override
    protected void update(OrthographicCamera camera) {
        data.update(camera);
    }

    @Override
    protected void draw(SpriteBatch batch) {
        data.draw(batch);
    }

    @Override
    protected void drawHUD(SpriteBatch batch) {
        data.drawHUD(batch);
    }
    
    @Override
    public void event(int code) {
        switch (code) {
            
            case Event.MOUSE_KEY_LEFT:
                data.select();    
                break;
                
            case Event.MOUSE_KEY_RIGHT:
                data.moveObj();
                break;

            default:
                break;
        }
    }
    
    @Override
    public void event(int code, int value) {
        if(code == Event.MOUSE_SCROLL){
            data.zoom(camera, value);
        }
    }

    @Override
    public void dispose() {
        data.dispose();
    }
}