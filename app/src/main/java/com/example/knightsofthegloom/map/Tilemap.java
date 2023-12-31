package com.example.knightsofthegloom.map;

import static com.example.knightsofthegloom.map.MapLayout.NUMBER_OF_COLUMN_TILES;
import static com.example.knightsofthegloom.map.MapLayout.NUMBER_OF_ROW_TILES;
import static com.example.knightsofthegloom.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.knightsofthegloom.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.knightsofthegloom.GameDisplay;
import com.example.knightsofthegloom.graphics.Tile;
import com.example.knightsofthegloom.graphics.TileSheet;

public class Tilemap {

    private final MapLayout mapLayout;
    private Tile[][] tilemap;
    private TileSheet tileSheet;
    private Bitmap mapBitmap;
    private int level;
    private int[][] layout;

    public Tilemap(TileSheet tileSheet, int level) {
        this.level = level;
        mapLayout = new MapLayout();
        this.tileSheet = tileSheet;
        initializeTilemap();
    }

    private void initializeTilemap() {
        this.layout = mapLayout.getLayout(level);
        tilemap = new Tile[NUMBER_OF_ROW_TILES][NUMBER_OF_COLUMN_TILES];
        for(int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for(int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++) {
                tilemap[iRow][iCol] = new Tile(tileSheet, layout[iRow][iCol]);
            }
        }
        
        Bitmap.Config config = Bitmap.Config.ARGB_8888;
        
        mapBitmap = Bitmap.createBitmap(
                NUMBER_OF_COLUMN_TILES*TILE_WIDTH_PIXELS, 
                NUMBER_OF_ROW_TILES*TILE_HEIGHT_PIXELS, 
                config
        );

        Canvas mapCanvas = new Canvas(mapBitmap);

        for(int iRow = 0; iRow < NUMBER_OF_ROW_TILES; iRow++) {
            for(int iCol = 0; iCol < NUMBER_OF_COLUMN_TILES; iCol++) {
                tilemap[iRow][iCol].draw(mapCanvas, iRow, iCol);
            }
        }
    }

    private Rect getRectByIndex(int idxRow, int idxCol) {
        return new Rect(
                idxCol*TILE_WIDTH_PIXELS,
                idxRow*TILE_HEIGHT_PIXELS,
                TILE_WIDTH_PIXELS * (idxCol + 1),
                TILE_HEIGHT_PIXELS * (idxRow + 1)
        );
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        canvas.drawBitmap(
                mapBitmap,
                gameDisplay.getGameRect(),
                gameDisplay.DISPLAY_RECT,
                null
        );
    }

    public void loadLevel(int newLevel) {
        this.level = newLevel;
        initializeTilemap();
    }

    public int getTilemapHeightPixels() {
        return NUMBER_OF_ROW_TILES * TILE_HEIGHT_PIXELS;
    }
    public int getTilemapWidthPixels() {
        return NUMBER_OF_COLUMN_TILES * TILE_WIDTH_PIXELS;
    }
}
