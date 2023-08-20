package com.example.knightsofthegloom.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.knightsofthegloom.R;

public class SpriteSheet {
    private static int SPRITE_WIDTH_PIXELS;
    private static int SPRITE_HEIGHT_PIXELS;
    private static int TOTAL_SPRITE_NOT_MOVING_FRAMES;
    private static int TOTAL_SPRITE_MOVING_FRONT_FRAMES;
    private static int TOTAL_SPRITE_MOVING_LEFT_FRAMES;
    private static int TOTAL_SPRITE_MOVING_RIGHT_FRAMES;
    private static int TOTAL_SPRITE_MOVING_BACK_FRAMES;

    private Bitmap bitmap;

    public SpriteSheet(Context context, int spriteWidth, int spriteHeight, int notMovingFrames, int movingFrontFrames, int movingLeftFrames, int movingRightFrames, int movingBackFrames, int resourceId) {
        this.SPRITE_WIDTH_PIXELS = spriteWidth;
        this.SPRITE_HEIGHT_PIXELS = spriteHeight;
        this.TOTAL_SPRITE_NOT_MOVING_FRAMES = notMovingFrames;
        this.TOTAL_SPRITE_MOVING_FRONT_FRAMES = movingFrontFrames;
        this.TOTAL_SPRITE_MOVING_LEFT_FRAMES = movingLeftFrames;
        this.TOTAL_SPRITE_MOVING_RIGHT_FRAMES = movingRightFrames;
        this.TOTAL_SPRITE_MOVING_BACK_FRAMES = movingBackFrames;
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, bitmapOptions);
    }

    public Sprite[][] getMovingSpriteMatrix() {
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
        return bitmap;
    }




}
