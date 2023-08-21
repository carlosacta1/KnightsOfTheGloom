package com.example.knightsofthegloom.graphics;

import static com.example.knightsofthegloom.map.MapLayout.TILE_HEIGHT_PIXELS;
import static com.example.knightsofthegloom.map.MapLayout.TILE_WIDTH_PIXELS;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Tile {

    private static int TILE_WIDTH_PIXELS = 64;
    private static int TILE_HEIGHT_PIXELS = 64;
    protected final Rect mapLocationRect;
    private final TileSheet tileSheet;
    private int idxTileType;
    private int idxCol;
    private int idxRow = 1;

    public enum TileType {
        WATER_TILE,
        LAVA_TILE,
        GROUND_TILE,
        GRASS_TILE,
        TREE_TILE
    }

    public Tile(TileSheet tileSheet, int idxTileType) {
        this.idxTileType = idxTileType;
        this.tileSheet = tileSheet;

        switch(TileType.values()[this.idxTileType]) {

            case WATER_TILE:
                this.idxCol = 0;
                break;
            case LAVA_TILE:
                this.idxCol = 1;
                break;
            case GROUND_TILE:
                this.idxCol = 2;
                break;
            case GRASS_TILE:
                this.idxCol = 3;
                break;
            case TREE_TILE:
                this.idxCol = 4;
                break;
            default:
                this.idxCol = 2;
                break;
        }
        this.mapLocationRect = new Rect(
                idxCol*TILE_WIDTH_PIXELS,
                idxRow*TILE_HEIGHT_PIXELS,
                TILE_WIDTH_PIXELS * (idxCol + 1),
                TILE_HEIGHT_PIXELS * (idxRow + 1)
        );
    }

    public void draw(Canvas canvas, int idxRow, int idxCol) {
        canvas.drawBitmap(
                tileSheet.getTilesBitmap(),
                mapLocationRect,
                new Rect(idxCol*TILE_WIDTH_PIXELS,idxRow*TILE_HEIGHT_PIXELS,(idxCol+1)*TILE_WIDTH_PIXELS,(idxRow+1)*TILE_HEIGHT_PIXELS),
                null
        );
    }

    public int getWidth() {
        return mapLocationRect.width();
    }

    public int getHeight() {
        return mapLocationRect.height();
    }
}
