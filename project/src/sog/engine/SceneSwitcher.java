package sog.engine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.owlengine.scenes.SceneMng;

import sog.game.SceneGame;
import sog.menu.SceneMenu;

public class SceneSwitcher {

    private static SceneMng scenesMng;
    private static OrthographicCamera camera;
    
    public SceneSwitcher(SceneMng scenes, OrthographicCamera camera) {
        SceneSwitcher.scenesMng = scenes;
        SceneSwitcher.camera = camera;
    }

    public static void sceneGame(){
        scenesMng.loadScene(new SceneGame(camera));
    }
    
    public static void sceneMenu(){
        scenesMng.loadScene(new SceneMenu());
    }

    public static void exit() {
        Gdx.app.exit();
    }
}