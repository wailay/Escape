/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sound;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

/**
 *
 * @author wail
 */
public class SoundLoader {
    
    public static Sound menuSound;
    public static Music musicJeux;
    public static Sound door;

    public SoundLoader() throws SlickException {
        menuSound = loadSound("res/sound/tick.wav");
        musicJeux = loadMusic("res/sound/fantasy.wav");
        door = loadSound("res/sound/door.wav");
    }
    private Sound loadSound(String path) throws SlickException{
        Sound s = new Sound(path);
        return s;
    }
    private Music loadMusic(String path) throws SlickException{
        Music m = new Music(path);
        return m;
    }
}
