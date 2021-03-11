package com.tutorial.main;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable {

    public static final int WIDTH = 640, HEIGHT = (WIDTH/12)*9;

    private Thread thread;
    private Boolean running = false;

    private Random r;
    private Handler handler;
    private HUD hud;
    private Spawn spawner;
    private Menu menu;

    public enum STATE {
        Menu,
        Game;
    }

    public STATE gameState = STATE.Menu;

    public Game() {

        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "Let's build a game", this);

        hud = new HUD();
        spawner = new Spawn(handler, hud);
        menu = new Menu();
        r = new Random();

        if(gameState == STATE.Game) {

            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));

            for (int i = 0; i < 1; i++) {
                handler.addObject(new BasicEnemy(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 54), ID.BasicEnemy, handler));
            }
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
        handler.tick();
        if(gameState == STATE.Game) {
            hud.tick();
            spawner.tick();
        } else if(gameState == STATE.Menu) {
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

        if (gameState == STATE.Game) {
            hud.render(g);
        }else if(gameState == STATE.Menu) {
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

    public static void main(String[] args) {
        new Game();
    }
}
