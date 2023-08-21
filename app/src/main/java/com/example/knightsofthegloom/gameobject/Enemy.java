package com.example.knightsofthegloom.gameobject;

import android.content.Context;
import android.graphics.Canvas;

import androidx.core.content.ContextCompat;

import com.example.knightsofthegloom.GameDisplay;
import com.example.knightsofthegloom.GameLoop;
import com.example.knightsofthegloom.R;
import com.example.knightsofthegloom.graphics.Animator;

public class Enemy extends Circle{
    private static final double SPEED_PIXELS_PER_SECOND = Player.SPEED_PIXELS_PER_SECOND*0.6;
    private static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;
    private static final double SPAWNS_PER_MINUTE = 20;
    private static final double SPAWNS_PER_SECOND = SPAWNS_PER_MINUTE/60;
    private static final double UPDATES_PER_SPAWN = GameLoop.MAX_UPS/SPAWNS_PER_SECOND;
    private static double updatesUntilNextSpawn = UPDATES_PER_SPAWN;
    private final Player player;
    private Animator animator;
    private MovingState movingState;

    public Enemy(Context context, Player player, double positionX, double positionY, double radius, Animator animator) {
        super(context, ContextCompat.getColor(context, R.color.enemy), positionX, positionY, radius);
        this.player = player;
        this.animator = animator;
        this.movingState = new MovingState(this);
    }

    public Enemy(Context context, Player player, Animator animator) {
        super(context, ContextCompat.getColor(context, R.color.enemy),
                Math.random()*1000,
                Math.random()*1000,
                30
        );
        this.player = player;
        this.animator = animator;
        this.movingState = new MovingState(this);
    }

    public static boolean readyToSpawn() {
        if(updatesUntilNextSpawn <= 0) {
            updatesUntilNextSpawn += UPDATES_PER_SPAWN;
            return true;
        } else {
            updatesUntilNextSpawn --;
            return false;
        }
    }

    @Override
    public void update() {
        //1. Calculate vector from enemy to player
        double distanceToPlayerX = player.getPositionX() - positionX;
        double distanceToPlayerY = player.getPositionY() - positionY;

        //2. Calculate absolute distance between enemy an player
        double distanceToPlayer = GameObject.getDistanceBetweenObjects(this, player);

        //3. Calculate direction from enemy to player
        double directionX = distanceToPlayerX/distanceToPlayer;
        double directionY = distanceToPlayerY/distanceToPlayer;

        //4. Set velocity in the direction of the player
        if (distanceToPlayer > 0) {
            velocityX = directionX*MAX_SPEED;
            velocityY = directionY*MAX_SPEED;
        } else {
            velocityX = 0;
            velocityY = 0;
        }

        //5. Update the position of the enemy
        positionX += velocityX;
        positionY += velocityY;
        movingState.update();

    }

    public void draw(Canvas canvas, GameDisplay gameDisplay) {
        animator.draw(canvas, gameDisplay, this);
    }

    public MovingState.State getState() { return movingState.getState(); }

}
