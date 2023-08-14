package com.example.knightsofthegloom.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.knightsofthegloom.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 145;
    private static final int TILE_SPRITE_WIDTH_PIXELS = 64;
    private static final int SPRITE_HEIGHT_PIXELS = 208;
    private static final int TILE_SPRITE_HEIGHT_PIXELS = 64;
    private static final int TOTAL_SPRITE_NOT_MOVING_FRAMES = 9;
    private static final int TOTAL_SPRITE_MOVING_FRONT_FRAMES = 4;
    private static final int TOTAL_SPRITE_MOVING_LEFT_FRAMES = 4;
    private static final int TOTAL_SPRITE_MOVING_RIGHT_FRAMES = 4;
    private static final int TOTAL_SPRITE_MOVING_BACK_FRAMES = 4;

    private Bitmap playerBitmap;
    private Bitmap tilesBitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        playerBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_sheet, bitmapOptions);
        tilesBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[][] getPlayerMovingSpriteMatrix() {
        Sprite[][] spriteMatrix = new Sprite[5][];
        spriteMatrix[0] = new Sprite[TOTAL_SPRITE_NOT_MOVING_FRAMES];
        spriteMatrix[1] = new Sprite[TOTAL_SPRITE_MOVING_FRONT_FRAMES];
        spriteMatrix[2] = new Sprite[TOTAL_SPRITE_MOVING_LEFT_FRAMES];
        spriteMatrix[3] = new Sprite[TOTAL_SPRITE_MOVING_RIGHT_FRAMES];
        spriteMatrix[4] = new Sprite[TOTAL_SPRITE_MOVING_BACK_FRAMES];

        for (int i = 0; i < spriteMatrix.length; i++) {
            for (int j = 0; j < spriteMatrix[i].length; j++) {
                spriteMatrix[i][j] = new Sprite(this, new Rect(j*SPRITE_WIDTH_PIXELS, i*SPRITE_HEIGHT_PIXELS, (j+1)*SPRITE_WIDTH_PIXELS, (i+1)*SPRITE_HEIGHT_PIXELS));
            }
        }
        return spriteMatrix;
    }

    public Bitmap getPlayerBitmap() {
        return playerBitmap;
    }

    public Bitmap getTilesBitmap() {
        return tilesBitmap;
    }

    public Sprite getWaterSprite() {
        return getTileByIndex(1, 0);
    }

    public Sprite getLavaSprite() {
        return getTileByIndex(1, 1);
    }

    public Sprite getGroundSprite() {
        return getTileByIndex(1, 2);
    }

    public Sprite getGrassSprite() {
        return getTileByIndex(1, 3);
    }

    public Sprite getTreeSprite() {
        return getTileByIndex(1, 4);
    }

    private Sprite getTileByIndex(int idxRow, int idxCol) {
       return new Sprite(this, new Rect(
                idxCol*TILE_SPRITE_WIDTH_PIXELS,
                idxRow*TILE_SPRITE_HEIGHT_PIXELS,
                TILE_SPRITE_WIDTH_PIXELS * (idxCol + 1),
                TILE_SPRITE_HEIGHT_PIXELS * (idxRow + 1)
                ));
    }


}
