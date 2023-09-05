package com.example.knightsofthegloom.gamepanel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

import com.example.knightsofthegloom.R;

public class Points {

    private Context context;
    private int playerPoints;

    public Points(Context context, int playerPoints) {
        this.context = context;
        this.playerPoints = playerPoints;
    }

    public void draw(Canvas canvas) {
        String text = String.valueOf(this.playerPoints);
        float x = 800;
        float y = 200;
        Paint paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.points);
        paint.setColor(color);
        float textSize = 150;
        paint.setTextSize(textSize);
        canvas.drawText(text, x, y, paint);
    }

    public void increment(){
        this.playerPoints++;
    }

    public int getPlayerPoints() {
        return playerPoints;
    }
}
