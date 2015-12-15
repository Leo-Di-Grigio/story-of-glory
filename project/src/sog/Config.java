package sog;

import com.owlengine.config.OwlConfig;

class Config extends OwlConfig {
    
    @Override
    public String frameTitle() {
        return Version.TITLE;
    }
    
    @Override
    public int frameWidth() {
        return 1440;
    }
    
    @Override
    public int frameHeight() {
        return 900;
    }
}
