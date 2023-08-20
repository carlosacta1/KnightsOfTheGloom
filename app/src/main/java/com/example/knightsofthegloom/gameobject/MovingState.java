package com.example.knightsofthegloom.gameobject;

import com.google.android.material.shape.ShapePath;

public class MovingState {
    public enum State {
        NOT_MOVING,
        STARED_MOVING,
        MOVING_FRONT,
        MOVING_LEFT,
        MOVING_RIGHT,
        MOVING_BACK
    }

    private GameObject gameObject;
    private State state;
    private double MAX_DIRECTION_Y_BEFORE_CHANGE = 0.8;

    public MovingState(GameObject gameObject) {
        this.gameObject = gameObject;
        this.state = State.NOT_MOVING;
    }

    public State getState() {return state;}

    public void update() {
        switch (state) {
            case NOT_MOVING:
                if (gameObject.velocityX != 0 || gameObject.velocityY != 0)
                    state = State.STARED_MOVING;
                break;
            case STARED_MOVING:
                if (gameObject.velocityX > 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (gameObject.velocityX < 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (gameObject.velocityY > 0 && gameObject.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                } else if (gameObject.velocityY < 0 && gameObject.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                }
                break;
            case MOVING_FRONT:
                if (gameObject.velocityX == 0 && gameObject.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (gameObject.velocityX < 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (gameObject.velocityX > 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (gameObject.velocityY < 0 && gameObject.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                }
                break;
            case MOVING_LEFT:
                if (gameObject.velocityX == 0 && gameObject.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (gameObject.velocityX > 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (gameObject.velocityY < 0 && gameObject.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                } else if (gameObject.velocityY > 0 && gameObject.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                }
                break;
            case MOVING_RIGHT:
                if (gameObject.velocityX == 0 && gameObject.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (gameObject.velocityX < 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (gameObject.velocityY < 0 && gameObject.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                } else if (gameObject.velocityY > 0 && gameObject.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                }
                break;
            case MOVING_BACK:
                if (gameObject.velocityX == 0 && gameObject.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (gameObject.velocityX < 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (gameObject.velocityX > 0 && gameObject.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && gameObject.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (gameObject.velocityY > 0 && gameObject.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                }
                break;
            default:
                break;
        }
    }
}
