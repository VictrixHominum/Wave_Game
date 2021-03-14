package com.tutorial.main;

import java.util.Random;

public class Spawn {

    private final Handler handler;
    private final HUD hud;
    private final Game game;
    private final Random r = new Random();
    private int scoreKeep = 0;

    public Spawn(Handler handler, HUD hud, Game game) {
        this.handler = handler;
        this.hud = hud;
        this.game = game;
    }

    public void tick() {
        scoreKeep++;
        if(scoreKeep >= 100){
            scoreKeep = 0;
            if(game.diff == 0) {
                if (hud.getLevel() <= 10)
                    hud.setLevel(hud.getLevel() + 1);

                if (hud.getLevel() == 2) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy, handler));
                }
                if (hud.getLevel() == 3) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy, handler));
                }
                if (hud.getLevel() == 4) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
                }
                if (hud.getLevel() == 5) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.SmartEnemy, handler));
                }
                if (hud.getLevel() == 6) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
                }
                if (hud.getLevel() == 7) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.HardEnemy, handler));
                }
                if (hud.getLevel() == 8) {
                    handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy, handler));
                }
                if (hud.getLevel() == 9) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
                }
                if (hud.getLevel() == 10) {
                    handler.clearEnemies();
                    handler.addObject(new EnemyBoss1((Game.WIDTH / 2) - 54, -110, ID.EnemyBoss1, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.SmartEnemy, handler));
                }
            }
            else if (game.diff == 1) {
                if (hud.getLevel() <= 10)
                    hud.setLevel(hud.getLevel() + 1);

                if (hud.getLevel() == 2) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
                }
                if (hud.getLevel() == 3) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.HardEnemy, handler));
                }
                if (hud.getLevel() == 4) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
                }
                if (hud.getLevel() == 5) {
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.SmartEnemy, handler));
                }
                if (hud.getLevel() == 6) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
                }
                if (hud.getLevel() == 7) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.BasicEnemy, handler));
                }
                if (hud.getLevel() == 8) {
                    handler.addObject(new HardEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.HardEnemy, handler));
                }
                if (hud.getLevel() == 9) {
                    handler.addObject(new FastEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.FastEnemy, handler));
                }
                if (hud.getLevel() == 10) {
                    handler.clearEnemies();
                    handler.addObject(new EnemyBoss1((Game.WIDTH / 2) - 54, -110, ID.EnemyBoss1, handler));
                    handler.addObject(new SmartEnemy(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.SmartEnemy, handler));
                }
            }
        }
    }
}
