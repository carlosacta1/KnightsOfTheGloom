package com.example.knightsofthegloom.gameobject;

public class PlayerState {

    public enum State {
        NOT_MOVING,
        STARED_MOVING,
        MOVING_FRONT,
        MOVING_LEFT,
        MOVING_RIGHT,
        MOVING_BACK
    }

    private Player player;
    private State state;
    private double MAX_DIRECTION_Y_BEFORE_CHANGE = 0.8;

    public PlayerState(Player player) {
        this.player = player;
        this.state = State.NOT_MOVING;
    }

    public State getState() {
        return state;
    }

    public void update() {
        switch (state) {
            case NOT_MOVING:
                if (player.velocityX != 0 || player.velocityY != 0)
                    state = State.STARED_MOVING;
                break;
            case STARED_MOVING:
                if (player.velocityX > 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (player.velocityX < 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (player.velocityY > 0 && player.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                } else if (player.velocityY < 0 && player.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                }
                break;
            case MOVING_FRONT:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (player.velocityX < 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (player.velocityX > 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (player.velocityY < 0 && player.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                }
                break;
            case MOVING_LEFT:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (player.velocityX > 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (player.velocityY < 0 && player.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                } else if (player.velocityY > 0 && player.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                }
                break;
            case MOVING_RIGHT:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (player.velocityX < 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (player.velocityY < 0 && player.directionY < -MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_BACK;
                } else if (player.velocityY > 0 && player.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                }
                break;
            case MOVING_BACK:
                if (player.velocityX == 0 && player.velocityY == 0) {
                    state = State.NOT_MOVING;
                } else if (player.velocityX < 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_LEFT;
                } else if (player.velocityX > 0 && player.directionY >= -MAX_DIRECTION_Y_BEFORE_CHANGE && player.directionY <= MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_RIGHT;
                } else if (player.velocityY > 0 && player.directionY > MAX_DIRECTION_Y_BEFORE_CHANGE) {
                    state = State.MOVING_FRONT;
                }
                break;
            default:
                break;
        }
    }
}
