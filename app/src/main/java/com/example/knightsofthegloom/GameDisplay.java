package com.example.knightsofthegloom;

import android.graphics.Rect;

import com.example.knightsofthegloom.gameobject.GameObject;

public class GameDisplay {
    public final Rect DISPLAY_RECT;
    private final int widthPixels;
    private final int heightPixels;
    private double gameToDisplayCoordinateOffsetX;
    private double gameToDisplayCoordinateOffsetY;
    private double displayCenterX;
    private double displayCenterY;
    private double gameCenterX;
    private double gameCenterY;
    private GameObject centerObject;

    public GameDisplay(int widthPixels, int heightPixels, GameObject centerObject) {
        this.widthPixels = widthPixels;
        this.heightPixels = heightPixels;
        this.centerObject = centerObject;
        DISPLAY_RECT = new Rect(0,0,widthPixels, heightPixels);

        displayCenterX = widthPixels/2.0;
        displayCenterY = heightPixels/2.0;
    }

    public void update() {
        gameCenterX = centerObject.getPositionX();
        gameCenterY = centerObject.getPositionY();
        gameToDisplayCoordinateOffsetX = displayCenterX - gameCenterX;
        gameToDisplayCoordinateOffsetY = displayCenterY - gameCenterY;
    }

    public double gameToDisplayCoordinatesX(double x) {
        return x + gameToDisplayCoordinateOffsetX;
    }

    public double gameToDisplayCoordinatesY(double y) {
        return y + gameToDisplayCoordinateOffsetY;
    }

    public Rect getGameRect() {
        return new Rect(
                (int) (gameCenterX - widthPixels/2),
                (int) (gameCenterY - heightPixels/2),
                (int) (gameCenterX + widthPixels/2),
                (int) (gameCenterY + heightPixels/2)
        );
    }
}
