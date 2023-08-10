package com.example.knightsofthegloom.map;

import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.knightsofthegloom.graphics.Sprite;
import com.example.knightsofthegloom.graphics.SpriteSheet;

class TreeTile extends Tile {
    private final Sprite grassSprite;
    private final Sprite treeSprite;

    public TreeTile(SpriteSheet spriteSheet, Rect mapLocationRect) {
        super(mapLocationRect);
        grassSprite = spriteSheet.getGrassSprite();
        treeSprite = spriteSheet.getTreeSprite();
    }

    @Override
    public void draw(Canvas canvas) {
        grassSprite.drawTile(canvas, mapLocationRect.left, mapLocationRect.top);
        treeSprite.drawTile(canvas, mapLocationRect.left, mapLocationRect.top);
    }
}
