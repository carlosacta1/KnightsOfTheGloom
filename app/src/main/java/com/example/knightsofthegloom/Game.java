package com.example.knightsofthegloom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.example.knightsofthegloom.gameobject.Circle;
import com.example.knightsofthegloom.gameobject.Enemy;
import com.example.knightsofthegloom.gameobject.Player;
import com.example.knightsofthegloom.gameobject.Spell;
import com.example.knightsofthegloom.gamepanel.GameOver;
import com.example.knightsofthegloom.gamepanel.Joystick;
import com.example.knightsofthegloom.gamepanel.Performance;
import com.example.knightsofthegloom.graphics.Animator;
import com.example.knightsofthegloom.graphics.SpriteSheet;
import com.example.knightsofthegloom.graphics.TileSheet;
import com.example.knightsofthegloom.map.Tilemap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private Animator enemyAnimator;
    private final Joystick joystick;
    private final Tilemap tilemap;
    private GameLoop gameLoop;
    private List<Enemy> enemyList = new ArrayList<Enemy>();
    private List<Spell> spellList = new ArrayList<Spell>();
    private int joystickPointerId = 0;
    private int numberOfSpellsToCast = 0;
    private GameOver gameOver;
    private Performance performance;
    private GameDisplay gameDisplay;

    public Game(Context context) {
        super(context);

        //Get surface holder and add callback
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);

        gameLoop = new GameLoop(this, surfaceHolder);

        //Initialize Game Panels
        performance = new Performance(context, gameLoop);
        gameOver = new GameOver(context);
        joystick = new Joystick(275, 650, 70, 40);

        //Initialize Game Objects
        SpriteSheet playerSpriteSheet = new SpriteSheet(context, 145, 208, 9, 4, 4, 4, 4, R.drawable.player_sheet);
        SpriteSheet enemySpriteSheet = new SpriteSheet(context, 60, 64, 3, 3, 3, 3, 3, R.drawable.spider1);
        TileSheet tileSheet = new TileSheet(context);
        Animator playerAnimator = new Animator(playerSpriteSheet.getMovingSpriteMatrix(), 0, 8, 1, 3, 2, 3, 3, 3, 4, 3, 2);
        this.enemyAnimator = new Animator(enemySpriteSheet.getMovingSpriteMatrix(), 0, 2, 0, 2, 1, 2, 2, 2, 3, 2, 2);

        //Initialize Tilemap
        tilemap = new Tilemap(tileSheet);

        //Initialize player
        player = new Player(context, joystick, 2*500, 500, 32, playerAnimator, tilemap);
        //enemy = new Enemy(context, player);

        //Initialize GameDisplay and center it around the player
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        gameDisplay = new GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, player);



        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Handle touch event actions
        switch(event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if(joystick.getIsPressed()) {
                    numberOfSpellsToCast ++;
                } else if(joystick.isPressed((double) event.getX(), (double) event.getY())) {
                    joystickPointerId = event.getPointerId(event.getActionIndex());
                    joystick.setIsPressed(true);
                } else {
                    numberOfSpellsToCast ++;
                }
                return true;
            case MotionEvent.ACTION_MOVE:
                if(joystick.getIsPressed()) {
                    joystick.setActuator((double) event.getX(), (double) event.getY());
                }
                return true;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if(joystickPointerId == event.getPointerId(event.getActionIndex())) {
                    joystick.setIsPressed(false);
                    joystick.resetActuator();
                }
                return true;
        }

        return super.onTouchEvent(event);
    }


    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceCreated()");
        if (gameLoop.getState().equals(Thread.State.TERMINATED)) {
            gameLoop = new GameLoop(this, holder);
        }
        gameLoop.startLoop();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        Log.d("Game.java", "surfaceChanged()");

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        Log.d("Game.java", "surfaceDestroyed()");
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //Draw Tilemap
        tilemap.draw(canvas, gameDisplay);

        //Draw Game Objects
        player.draw(canvas, gameDisplay);

        for(Enemy enemy : enemyList) {
            enemy.draw(canvas, gameDisplay);
        }
        for(Spell spell : spellList) {
            spell.draw(canvas, gameDisplay);
        }

        //Dray GamePanels
        joystick.draw(canvas);
        //performance.draw(canvas);

        //Draw GameOver
        if(player.getHealthPoints() <= 0) {
            gameOver.draw(canvas);
        }
    }

    public void update() {

        if(player.getHealthPoints() <= 0) {
            return;
        }

        joystick.update();
        player.update();

        if(Enemy.readyToSpawn()) {
            enemyList.add(new Enemy(getContext(), player, enemyAnimator));
        }

        while (numberOfSpellsToCast > 0) {
            spellList.add(new Spell(getContext(), player));
            numberOfSpellsToCast --;
        }

        for(Enemy enemy : enemyList) {
            enemy.update();
        }

        for(Spell spell : spellList) {
            spell.update();
        }

        Iterator<Enemy> iteratorEnemy = enemyList.iterator();
        while (iteratorEnemy.hasNext()) {
            Circle enemy = iteratorEnemy.next();
            if(Circle.isColliding(enemy, player)) {
                iteratorEnemy.remove();
                player.setHealthPoints(player.getHealthPoints() - 1);
                continue;
            }
            Iterator<Spell> iteratorSpell = spellList.iterator();
            while (iteratorSpell.hasNext()) {
                Circle spell = iteratorSpell.next();
                if(Circle.isColliding(spell, enemy)) {
                    iteratorSpell.remove();
                    iteratorEnemy.remove();
                    break;
                }
            }
        }
        gameDisplay.update();
    }

    public void pause() {
        gameLoop.stopLoop();
    }
}
