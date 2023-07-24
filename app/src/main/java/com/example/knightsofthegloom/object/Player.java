package com.example.knightsofthegloom.object;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.knightsofthegloom.GameLoop;
import com.example.knightsofthegloom.Joystick;
import com.example.knightsofthegloom.R;

//Player is the main character controller via the joystick, the player class is an extension of Circle which is an extension of GameObject

public class Player extends Circle {
    public static final double SPEED_PIXELS_PER_SECOND = 400.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    private final Joystick joystick;

    public Player(Context context, Joystick joystick, double positionX, double positionY, double radius) {
        super(context, ContextCompat.getColor(context, R.color.player), positionX, positionY, radius);

        this.joystick = joystick;
    }

    public void update() {
        //Update Velocity based on actuator of joystick
        velocityX = joystick.getActuatorX() * MAX_SPEED;
        velocityY = joystick.getActuatorY() * MAX_SPEED;

        //Update position
        positionX += velocityX;
        positionY += velocityY;
    }
}
