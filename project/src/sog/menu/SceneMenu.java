package sog.menu;

import com.owlengine.interfaces.Script;
import com.owlengine.scenes.Scene;
import com.owlengine.ui.UI;
import com.owlengine.ui.widgets.Button;

import sog.engine.SceneSwitcher;

public class SceneMenu extends Scene {

    private static final String UI_FILE = "interface/menu.json";
    private static final String UI_BUTTON_PLAY = "button_menu_play";
    private static final String UI_BUTTON_EXIT = "button_menu_exit";
    
    public SceneMenu() {
        setUI(UI_FILE);
        setupUI(getUI());
    }

    private void setupUI(UI ui) {
        Button button = null;
        
        button = (Button)ui.getWidget(UI_BUTTON_PLAY);
        if(button != null){
            button.setScriptOnAction(new Script() {
                
                @Override
                public void execute() {
                    SceneSwitcher.sceneGame();
                }
            });
        }
        
        button = (Button)ui.getWidget(UI_BUTTON_EXIT);
        if(button != null){
            button.setScriptOnAction(new Script() {
                
                @Override
                public void execute() {
                    SceneSwitcher.exit();
                }
            });
        }
        
    }
}
