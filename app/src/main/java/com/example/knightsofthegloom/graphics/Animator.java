package com.example.knightsofthegloom.graphics;

import android.graphics.Canvas;
import android.graphics.Matrix;

import com.example.knightsofthegloom.GameDisplay;
import com.example.knightsofthegloom.gameobject.Player;
import com.example.knightsofthegloom.gameobject.PlayerState;

public class Animator {

    private Sprite[][] playerSpriteMatrix;

    private int notMovingFrameArrayPosition = 0;
    private int startIdxNotMovingFrame = 0;
    private int endIdxNotMovingFrame = 8;
    private int idxNotMovingFrame = 0;

    private int movingFrontFrameArrayPosition = 1;
    private int startIdxMovingFrontFrame = 0;
    private int endIdxMovingFrontFrame = 3;
    private int idxMovingFrontFrame = 0;

    private int movingLeftFrameArrayPosition = 2;
    private int startIdxMovingLeftFrame = 0;
    private int endIdxMovingLeftFrame = 3;
    private int idxMovingLeftFrame = 0;

    private int movingRightFrameArrayPosition = 3;
    private int startIdxMovingRightFrame = 0;
    private int endIdxMovingRightFrame = 3;
    private int idxMovingRightFrame = 0;

    private int movingBackFrameArrayPosition = 4;
    private int startIdxMovingBackFrame = 0;
    private int endIdxMovingBackFrame = 3;
    private int idxMovingBackFrame = 0;


    private int updatesBeforeNextMoveFrame;
    private static final int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = 2;

    public Animator(Sprite[][] playerSpriteMatrix) {
        this.playerSpriteMatrix = playerSpriteMatrix;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, Player player) {
        switch (player.getPlayerState().getState()) {
            case NOT_MOVING:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextNotMovingFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteMatrix[notMovingFrameArrayPosition][idxNotMovingFrame]);
                break;
            case STARED_MOVING:
                updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                break;
            case MOVING_FRONT:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingFrontFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteMatrix[movingFrontFrameArrayPosition][idxMovingFrontFrame]);
                break;
            case MOVING_LEFT:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingLeftFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteMatrix[movingLeftFrameArrayPosition][idxMovingLeftFrame]);
                break;
            case MOVING_RIGHT:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingRightFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteMatrix[movingRightFrameArrayPosition][idxMovingRightFrame]);
                break;
            case MOVING_BACK:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingBackFrame();
                }
                drawFrame(canvas, gameDisplay, player, playerSpriteMatrix[movingBackFrameArrayPosition][idxMovingBackFrame]);
                break;
            default:
                break;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, Player player, Sprite sprite) {

        int displayX = (int) gameDisplay.gameToDisplayCoordinatesX(player.getPositionX());
        int displayY = (int) gameDisplay.gameToDisplayCoordinatesY(player.getPositionY());
        sprite.draw(
                canvas,
                displayX - sprite.getWidth()/2,
                displayY - sprite.getHeight()/2
        );
    }

    private void nextNotMovingFrame() {
        if(idxNotMovingFrame == endIdxNotMovingFrame)
            idxNotMovingFrame = startIdxNotMovingFrame;
        else
            idxNotMovingFrame++;
    }

    private void nextMovingFrontFrame() {
        if(idxMovingFrontFrame == endIdxMovingFrontFrame)
            idxMovingFrontFrame = startIdxMovingFrontFrame;
        else
            idxMovingFrontFrame++;
    }

    private void nextMovingLeftFrame() {
        if(idxMovingLeftFrame == endIdxMovingLeftFrame)
            idxMovingLeftFrame = startIdxMovingLeftFrame;
        else
            idxMovingLeftFrame++;
    }

    private void nextMovingRightFrame() {
        if(idxMovingRightFrame == endIdxMovingRightFrame)
            idxMovingRightFrame = startIdxMovingRightFrame;
        else
            idxMovingRightFrame++;
    }

    private void nextMovingBackFrame() {
        if(idxMovingBackFrame == endIdxMovingBackFrame)
            idxMovingBackFrame = startIdxMovingBackFrame;
        else
            idxMovingBackFrame++;
    }
}
