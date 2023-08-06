package com.example.knightsofthegloom.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.knightsofthegloom.graphics.Sprite;
import com.example.knightsofthegloom.graphics.SpriteSheet;

class GrassTile extends Tile {
    private final Sprite sprite;

    public GrassTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        sprite = spriteSheet.getGrassSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        sprite.draw(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
