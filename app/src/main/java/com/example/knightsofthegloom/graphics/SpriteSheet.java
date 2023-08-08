package com.example.knightsofthegloom.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.knightsofthegloom.R;

public class SpriteSheet {
    private static final int SPRITE_WIDTH_PIXELS = 192;
    private static final int SPRITE_HEIGHT_PIXELS = 192;
    private static final int TOTAL_SPRITE_MOVING_FRAMES = 12;
    private static final int TOTAL_SPRITE_NOT_MOVING_FRAMES = 14;
    private Bitmap bitmap;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.player_sheet, bitmapOptions);
        // bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Sprite[] getPlayerMovingSpriteArray() {
        Sprite[] spriteArray = new Sprite[TOTAL_SPRITE_MOVING_FRAMES];
        for (int i = 0; i < spriteArray.length; i++) {
            spriteArray[i] = spriteArray[0] = new Sprite(this, new Rect(i*SPRITE_WIDTH_PIXELS, 1*SPRITE_HEIGHT_PIXELS, (i+1)*SPRITE_WIDTH_PIXELS, 2*SPRITE_HEIGHT_PIXELS));
        }
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public Sprite getWaterSprite() {
        return getSpriteByIndex(1, 0);
    }

    public Sprite getLavaSprite() {
        return getSpriteByIndex(1, 1);
    }

    public Sprite getGroundSprite() {
        return getSpriteByIndex(1, 2);
    }

    public Sprite getGrassSprite() {
        return getSpriteByIndex(1, 3);
    }

    public Sprite getTreeSprite() {
        return getSpriteByIndex(1, 4);
    }

    private Sprite getSpriteByIndex(int idxRow, int idxCol) {
       return new Sprite(this, new Rect(
                idxCol*SPRITE_WIDTH_PIXELS,
                idxRow*SPRITE_HEIGHT_PIXELS,
                SPRITE_WIDTH_PIXELS * (idxCol + 1),
                SPRITE_HEIGHT_PIXELS * (idxRow + 1)
                ));
    }


}
