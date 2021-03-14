package com.tutorial.main;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = (WIDTH/12)*9;

    private Thread thread;
    private Boolean running = false;

    public static Boolean paused = false;
    public int diff = 0;

    //0 = normal
    //1 = hard

    private final Random r;
    private final Handler handler;
    private final HUD hud;
    private final Spawn spawner;
    private final Menu menu;

    public enum STATE {
        Menu,
        Help,
        End,
        Select,
        Game
    }

    public STATE gameState = STATE.Menu;

    public Game() {

        handler = new Handler();
        hud = new HUD();
        menu = new Menu(this, handler, hud);

        this.addMouseListener(menu);
        this.addKeyListener(new KeyInput(handler, this));

        AudioPlayer.load();
        AudioPlayer.getMusic("ContraChop").loop();

        new Window(WIDTH, HEIGHT, "Wave", this);

        spawner = new Spawn(handler, hud, this);

        r = new Random();

        if(gameState != STATE.Game) {
            generateParticles();
        }
    }

    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            thread.join();
            running = false;

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void tick() {

        if(gameState == STATE.Game) {
            if(!paused) {
                handler.tick();
                hud.tick();
                spawner.tick();
                if (HUD.HEALTH <= 0) {
                    HUD.HEALTH = 100;
                    handler.object.clear();
                    gameState = STATE.End;
                    generateParticles();
                }
            }
        } else if(gameState == STATE.Menu || gameState == STATE.End || gameState == STATE.Select) {
            handler.tick();
            menu.tick();
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.black);
        g.fillRect(0,0, WIDTH, HEIGHT);

        handler.render(g);

        if (paused) {
            g.setColor(Color.white);
            g.drawString("PAUSED", 100, 100);
        }

        if (gameState == STATE.Game) {
            hud.render(g);
        }else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End || gameState == STATE.Select) {
            menu.render(g);
        }
        g.dispose();
        bs.show();
    }

    public static int clamp(int var, int min, int max) {
        if(var >= max) {
            return var = max;
        }
        else if (var <= min) {
            return var = min;
        }
        else
            return var;
    }

    private void generateParticles() {
        for(int i = 0; i < 10; i++) {
            handler.addObject(new MenuParticle(r.nextInt(Game.WIDTH - 32), r.nextInt(Game.HEIGHT - 54), ID.MenuParticle, handler));
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
