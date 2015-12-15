package sog.game;

public class Const {
    
    // fonts
    public static final String FONT = "assets/font/font.ttf";
    
    // textures
    public static final String TEX_NULL = "assets/tex/null.png";
    
    public static final String TEX_TILE_GRASS = "assets/tex/tile/grass.png";
    public static final String TEX_TILE_DUST = "assets/tex/tile/dust.png";
    public static final String TEX_TILE_WATER = "assets/tex/tile/water.png";
    
    public static final String TEX_CURSOR = "assets/tex/tile/cursor.png";
    
    public static final String TEX_UNIT = "assets/tex/unit/unit.png";

    // tile codes
    public static final byte TILE_GRASS = 0;
    public static final byte TILE_DUST = 1;

    // map params
    public static final int MAP_SIZE_TILE_PIXELS = 32;
    public static final int MAP_SIZE_TILES = 256;
    public static final int MAP_CHUNK_SIZE_TILES = 8;
    
    public static final int MAP_SIZE_CHUNKS = MAP_SIZE_TILES / MAP_CHUNK_SIZE_TILES;
    public static final int MAP_SIZE_PIXELS = MAP_SIZE_TILES * MAP_SIZE_TILE_PIXELS;
    public static final int MAP_CHUNK_SIZE_PIXELS = MAP_CHUNK_SIZE_TILES * MAP_SIZE_TILE_PIXELS;
    
    // Camera
    public static final float ZOOM_MAX = 3.0f;
    public static final float ZOOM_MIN = 0.5f;

    public static final float ZOOM_SPEED_BASE = 0.05f;
    public static final float CAMERA_SPEED_BASE = 7.0f;
}