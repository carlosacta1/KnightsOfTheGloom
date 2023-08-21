package com.example.knightsofthegloom.gameobject;

import android.content.Context;

import androidx.core.content.ContextCompat;

import com.example.knightsofthegloom.GameLoop;
import com.example.knightsofthegloom.R;

public class Spell extends Circle{

    public static final double SPEED_PIXELS_PER_SECOND = 800.0;
    public static final double MAX_SPEED = SPEED_PIXELS_PER_SECOND/ GameLoop.MAX_UPS;

    public Spell(Context context, Player spell_caster) {
        super(
                context,
                ContextCompat.getColor(context, R.color.spell),
                spell_caster.getPositionX(),
                spell_caster.getPositionY(),
                20
        );
        velocityX = spell_caster.getDirectionX() * MAX_SPEED;
        velocityY = spell_caster.getDirectionY() * MAX_SPEED;
    }

    @Override
    public void update() {
        positionX = positionX + velocityX;
        positionY = positionY + velocityY;
    }

    public MovingState.State getState() {
        return null;
    }
}
