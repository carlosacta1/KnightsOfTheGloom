package com.example.knightsofthegloom.gameobject;

import android.content.Context;

import com.example.knightsofthegloom.graphics.Animator;
import com.example.knightsofthegloom.graphics.TileSheet;
import com.example.knightsofthegloom.map.Tilemap;

import java.util.List;

public class Level {

    private Player player;
    private List<Enemy> enemies;

    private Animator enemyAnimator;
    private Animator playerAnimator;

    private Tilemap tilemap;
    private TileSheet tileSheet;

    private int level;

    public Level(Context context, int level) {
        this.level = level;
        this.tileSheet = new TileSheet(context);
        this.tilemap = new Tilemap(tileSheet, this.level);
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Tilemap getTilemap() {
        return tilemap;
    }

    public void changeLevel(int newLevel) {
        this.level = newLevel;
        tilemap.loadLevel(newLevel);
    }

    public int getCurrentLevel() {
        return level;
    }
}
