package com.example.knightsofthegloom.graphics;

import android.graphics.Canvas;

import com.example.knightsofthegloom.GameDisplay;
import com.example.knightsofthegloom.gameobject.GameObject;
import com.example.knightsofthegloom.gameobject.Player;

public class Animator {

    private Sprite[][] spriteMatrix;

    private int notMovingFrameArrayPosition;
    private int startIdxNotMovingFrame;
    private int endIdxNotMovingFrame;
    private int idxNotMovingFrame;

    private int movingFrontFrameArrayPosition;
    private int startIdxMovingFrontFrame;
    private int endIdxMovingFrontFrame;
    private int idxMovingFrontFrame;

    private int movingLeftFrameArrayPosition;
    private int startIdxMovingLeftFrame;
    private int endIdxMovingLeftFrame;
    private int idxMovingLeftFrame;

    private int movingRightFrameArrayPosition;
    private int startIdxMovingRightFrame;
    private int endIdxMovingRightFrame;
    private int idxMovingRightFrame;

    private int movingBackFrameArrayPosition;
    private int startIdxMovingBackFrame;
    private int endIdxMovingBackFrame;
    private int idxMovingBackFrame;


    private int updatesBeforeNextMoveFrame;
    private static int MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;

    public Animator(Sprite[][] spriteMatrix, int notMovingRowIdx, int totalNotMovingIdx,
                    int movingFrontRowIdx, int totalMovingFrontIdx, int movingLeftRowIdx, int totalMovingLeftIdx,
                    int movingRightRowIdx, int totalMovingRightIdx, int movingBackRowIdx, int totalMovingBackIdx,
                    int maxUpdates) {
        this.spriteMatrix = spriteMatrix;

        this.notMovingFrameArrayPosition = notMovingRowIdx;
        this.startIdxNotMovingFrame = 0;
        this.endIdxNotMovingFrame = totalNotMovingIdx;
        this.idxNotMovingFrame = 0;

        this.movingFrontFrameArrayPosition = movingFrontRowIdx;
        this.startIdxMovingFrontFrame = 0;
        this.endIdxMovingFrontFrame = totalMovingFrontIdx;
        this.idxMovingFrontFrame = 0;

        this.movingLeftFrameArrayPosition = movingLeftRowIdx;
        this.startIdxMovingLeftFrame = 0;
        this.endIdxMovingLeftFrame = totalMovingLeftIdx;
        this.idxMovingLeftFrame = 0;

        this.movingRightFrameArrayPosition = movingRightRowIdx;
        this.startIdxMovingRightFrame = 0;
        this.endIdxMovingRightFrame = totalMovingRightIdx;
        this.idxMovingRightFrame = 0;

        this.movingBackFrameArrayPosition = movingBackRowIdx;
        this.startIdxMovingBackFrame = 0;
        this.endIdxMovingBackFrame = totalMovingBackIdx;
        this.idxMovingBackFrame = 0;

        this.MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME = maxUpdates;
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay, GameObject gameObject) {
        switch (gameObject.getState()) {
            case NOT_MOVING:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextNotMovingFrame();
                }
                drawFrame(canvas, gameDisplay, gameObject, spriteMatrix[notMovingFrameArrayPosition][idxNotMovingFrame]);
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
                drawFrame(canvas, gameDisplay, gameObject, spriteMatrix[movingFrontFrameArrayPosition][idxMovingFrontFrame]);
                break;
            case MOVING_LEFT:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingLeftFrame();
                }
                drawFrame(canvas, gameDisplay, gameObject, spriteMatrix[movingLeftFrameArrayPosition][idxMovingLeftFrame]);
                break;
            case MOVING_RIGHT:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingRightFrame();
                }
                drawFrame(canvas, gameDisplay, gameObject, spriteMatrix[movingRightFrameArrayPosition][idxMovingRightFrame]);
                break;
            case MOVING_BACK:
                updatesBeforeNextMoveFrame--;
                if (updatesBeforeNextMoveFrame == 0) {
                    updatesBeforeNextMoveFrame = MAX_UPDATES_BEFORE_NEXT_MOVE_FRAME;
                    nextMovingBackFrame();
                }
                drawFrame(canvas, gameDisplay, gameObject, spriteMatrix[movingBackFrameArrayPosition][idxMovingBackFrame]);
                break;
            default:
                break;
        }
    }

    public void drawFrame(Canvas canvas, GameDisplay gameDisplay, GameObject gameObject, Sprite sprite) {

        int displayX = (int) gameDisplay.gameToDisplayCoordinatesX(gameObject.getPositionX());
        int displayY = (int) gameDisplay.gameToDisplayCoordinatesY(gameObject.getPositionY());
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
