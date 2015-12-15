package sog.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.owlengine.OwlCycle;
import com.owlengine.input.UserInput;
import com.owlengine.scenes.SceneMng;

public class Cycle extends OwlCycle {
    
    private SceneMng scenes;
    
    private SpriteBatch batchScenes;
    private SpriteBatch batchUi;
    
    private OrthographicCamera camera;
    
    @Override
    public void setup() {
        scenes = engine.getSceneMng();
        
        setupEngine();
        setupGL();
        setupGDX();
        setupGame();
    }
    
    private void setupEngine() {
        Gdx.input.setInputProcessor(new UserInput(scenes));
    }
    
    private void setupGDX() {
        // batches
        batchScenes = new SpriteBatch();
        batchUi = new SpriteBatch();
        
        // camera
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(width, height);
        camera.position.set(width*0.5f, height*0.5f, 0.0f);
    }
    
    private void setupGL() {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    }
    
    private void setupGame() {
        new SceneSwitcher(scenes, camera);
        SceneSwitcher.sceneMenu();
    }
    
    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // update
        scenes.update(camera);
        camera.update();
        batchScenes.setProjectionMatrix(camera.combined);
        
        // render
        scenes.draw(batchScenes, batchUi);
        scenes.postUpdate();
        scenes.drawHUD(batchUi);
    }
    
    @Override
    public void dispose() {
        
    }
}