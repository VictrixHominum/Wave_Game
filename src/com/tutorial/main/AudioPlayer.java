package com.tutorial.main;

import java.util.*;

import org.newdawn.slick.*;

public class AudioPlayer {

    public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
    public static Map<String, Music> musicMap = new HashMap<String, Music>();

    public static void load() {

        try {
            soundMap.put("MouseClick", new Sound("res/Mouse Click Fast.wav-23232-Free-Loops.com.wav"));
            musicMap.put("ContraChop", new Music("res/Jim Hall - The Contra Chop.wav"));
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public static Music getMusic(String key) {
        return musicMap.get(key);
    }

    public static Sound getSound(String key) {
        return soundMap.get(key);
    }
}
