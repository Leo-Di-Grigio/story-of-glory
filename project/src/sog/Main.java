package sog;

import com.owlengine.OwlFrame;

import sog.engine.Cycle;

public class Main {
    
    public static void main(String [] args) {
        new OwlFrame(new Config(), new Cycle());
    }
}