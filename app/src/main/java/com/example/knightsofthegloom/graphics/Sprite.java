package com.example.knightsofthegloom.graphics;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private final SpriteSheet spriteSheet;
    private final Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect) {
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getPlayerBitmap(),
                rect,
                new Rect(x,y,x+getWidth(),y+getHeight()),
                null
        );
    }

    public void drawTile(Canvas canvas, int x, int y) {
        canvas.drawBitmap(
                spriteSheet.getTilesBitmap(),
                rect,
                new Rect(x,y,x+getWidth(),y+getHeight()),
                null
        );
    }

    public int getWidth() {
        return rect.width();
    }

    public int getHeight() {
        return rect.height();
    }
}
