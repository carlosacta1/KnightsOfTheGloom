package com.example.knightsofthegloom.graphics;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import com.example.knightsofthegloom.R;

public class TileSheet {
    private Bitmap tilesBitmap;

    public TileSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        tilesBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.sprite_sheet, bitmapOptions);
    }

    public Bitmap getTilesBitmap() {
        return tilesBitmap;
    }

}
