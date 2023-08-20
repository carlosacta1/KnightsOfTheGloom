package com.example.knightsofthegloom.gameobject;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.example.knightsofthegloom.GameDisplay;
import com.example.knightsofthegloom.GameLoop;
import com.example.knightsofthegloom.gamepanel.HealthBar;
import com.example.knightsofthegloom.gamepanel.Joystick;
import com.example.knightsofthegloom.R;
import com.example.knightsofthegloom.Utils;
import com.example.knightsofthegloom.graphics.Animator;
import com.example.knightsofthegloom.map.Tilemap;

//Player is the main character controller via the joystick, the player class is an extension of Circle which is an extension of GameObject

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    public static final int MAX_HEALTH_POINTS = 10;
    private final Joystick joystick;
    private HealthBar healthBar;
    private int healthPoints = MAX_HEALTH_POINTS;
    private Animator animator;
    private MovingState movingState;
    private Tilemap tilemap;


    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius, Animator animator, Tilemap tilemap) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);

        this.joystick = joystick;
        this.healthBar = new HealthBar(context, this);
        this.animator = animator;
        this.movingState = new MovingState(this);
        this.tilemap = tilemap;
    }

    public void update() {
        //Update Velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        // Calculate potential new positions
        double potentialX = positionX + velocityX;
        double potentialY = positionY + velocityY;

        // Check if potential positions are within bounds of the map
        if (potentialX - radius >= 0 && potentialX + radius <= tilemap.getTilemapWidthPixels()) {
            positionX = potentialX;
        }
        if (potentialY - radius >= 0 && potentialY + radius <= tilemap.getTilemapHeightPixels()) {
            positionY = potentialY;
        }

        //Update direction
        if (velocityX != 0 || velocityY != 0) {
            double distance = Utils.getDistanceBetweenPoints(0,0, velocityX, velocityY);
            directionX = velocityX/distance;
            directionY = velocityY/distance;
        }

        movingState.update();
    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this);
        healthBar.draw(canvas, gameDisplay);
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        if(healthPoints >= 0) {
            this.healthPoints = healthPoints;
        }
    }

    public MovingState getPlayerState() {
        return movingState;
    }
}
