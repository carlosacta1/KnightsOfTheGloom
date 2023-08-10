package com.example.knightsofthegloom.graphics;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.knightsofthegloom.GameDisplay;
import com.example.knightsofthegloom.gameobject.Player;
import com.example.knightsofthegloom.gameobject.PlayerState;

public class Animator {

    private Sprite[] playerSpriteArray;
    private int idxNotMovingFrame = 0;
    private int startIdxMovingFrame = 0;
    private int endIdxMovingFrame = 11;
    private int idxMovingFrame = 1;
    private int updatesBeforeNextMoveFrame;
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 2;

    public Animator(Sprite[] playerSpriteArray) {
        this.playerSpriteArray = playerSpriteArray;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()) {
            case NOT_MOVING:
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxNotMovingFrame]);
                break;
            case STARED_MOVING:
                updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            case IS_MOVING:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteArray[idxMovingFrame]);
                break;
            default:
                break;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite) {
        Matrix originalMatrix = canvas.getMatrix();

        int displayX = (int) gameDisplay.gameToDisplayCoordinatesX(player.getPositionX());
        int displayY = (int) gameDisplay.gameToDisplayCoordinatesY(player.getPositionY());

        if (player.getDirectionX() > 0) {
            // Calcula la coordenada X reflejada
            Matrix matrix = new Matrix();
            matrix.setScale(-1.0f, 1.0f);
            canvas.setMatrix(matrix);
            int reflectedX = -displayX;
            displayX = reflectedX;

            sprite.draw(
                    canvas,
                    displayX - sprite.getWidth()/2,
                    displayY - sprite.getHeight()/2
            );

        } else {
            sprite.draw(
                    canvas,
                    displayX - sprite.getWidth()/2,
                    displayY - sprite.getHeight()/2
            );
        }

        // Restablece la matriz de transformación del canvas a su estado original
        canvas.setMatrix(originalMatrix);
    }

    private void nextMovingFrame() {
        if(idxMovingFrame == endIdxMovingFrame)
            idxMovingFrame = startIdxMovingFrame;
        else
            idxMovingFrame++;
    }
}
