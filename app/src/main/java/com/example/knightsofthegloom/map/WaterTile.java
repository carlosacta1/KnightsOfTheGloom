package com.example.knightsofthegloom.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.knightsofthegloom.graphics.Sprite;
import com.example.knightsofthegloom.graphics.SpriteSheet;

class WaterTile extends Tile {
    private final Sprite sprite;

    public WaterTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getWaterSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.drawTile(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
