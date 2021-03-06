/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Item;

import Graphique.AnimationLoader;
import static Graphique.Item.Item.item_0;
import java.util.LinkedHashMap;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;

/**
 *
 * @author 1529565
 */
public final class ItemTexture {
    
    
    protected static SpriteSheet itemSheet, itemSheetInventaire;
    protected static Animation[] anim;
    protected static LinkedHashMap<Integer, SpriteSheet> sprites = new LinkedHashMap<Integer, SpriteSheet>();
    protected static LinkedHashMap<Integer, Animation[]> animations = new LinkedHashMap<Integer, Animation[]>();
    
    protected static SpriteSheet item_0, item_3, item_4, item_5, item_7, item_8, item_24,item_25,item_26,item_37, item_42, item_44
            ,item_43, item_48, item_55;
    protected static int notPickedSize = 32, width = 50, height = 50, duration = 130;

    public ItemTexture() throws SlickException {
             itemSheet = new SpriteSheet("res/text/item/item.png", notPickedSize, notPickedSize);
        itemSheetInventaire = new SpriteSheet("res/text/item/itemInventaire.png", notPickedSize*2, notPickedSize*2);
        item_0 = new SpriteSheet("res/text/item/equipable/item_0.png", width, height);
        item_3 = new SpriteSheet("res/text/item/equipable/item_3.png", width, height);
        item_4 = new SpriteSheet("res/text/item/equipable/item_4.png", width, height);
        item_5 = new SpriteSheet("res/text/item/equipable/item_5.png", width, height);
        item_7 = new SpriteSheet("res/text/item/equipable/item_7.png", width, height);
        item_8 = new SpriteSheet("res/text/item/equipable/item_8.png", width, height);
        item_24 = new SpriteSheet("res/text/item/equipable/item_24.png", width, height);
        item_25 = new SpriteSheet("res/text/item/equipable/item_25.png", width, height);
        item_26 = new SpriteSheet("res/text/item/equipable/item_26.png", width, height);
        item_37 = new SpriteSheet("res/text/item/equipable/item_37.png", width, height);
        item_42 = new SpriteSheet("res/text/item/equipable/item_42.png", width, height);
        item_43 = new SpriteSheet("res/text/item/equipable/item_43.png", width, height);
        item_44 = new SpriteSheet("res/text/item/equipable/item_44.png", width, height);
        item_48 = new SpriteSheet("res/text/item/equipable/item_48.png", width, height);
        item_55 = new SpriteSheet("res/text/item/equipable/item_55.png", width, height);
        anim = new Animation[8];
        load();
        
        
    }
    /**
     * Permet d'initialiser un HashMap de texture selon chaque ID d'un item
     */
      public void load() {
        
        sprites.put(0, item_0);
        sprites.put(3, item_3);
        sprites.put(4, item_4);
        sprites.put(5, item_5);
        sprites.put(7, item_7);
        sprites.put(8, item_8);
        sprites.put(24, item_24);
        sprites.put(25, item_25);
        sprites.put(26, item_26);
        sprites.put(37, item_37);
        sprites.put(42, item_42);
        sprites.put(43, item_43);
        sprites.put(44, item_44);
        sprites.put(48, item_48);
        sprites.put(55, item_55);
        animation();
    }
    /**
     * Initialise une animation propre a chaque item lorsqu'il est équipé
     */
    public void animation() {
        
        for (Integer key: sprites.keySet()) {
          Animation[] anim = new Animation[8];
          
            
            
        anim[0] = AnimationLoader.loadAnimation(sprites.get(key), 1, 2, 0, duration);
        anim[1] = AnimationLoader.loadAnimation(sprites.get(key), 1, 2, 1, duration);
        anim[2] = AnimationLoader.loadAnimation(sprites.get(key), 1, 2, 2, duration);
        anim[3] = AnimationLoader.loadAnimation(sprites.get(key), 1, 2, 3, duration);
        anim[4] = AnimationLoader.loadAnimation(sprites.get(key), 0, 3, 0, duration);
        anim[5] = AnimationLoader.loadAnimation(sprites.get(key), 0, 3, 1, duration);
        anim[6] = AnimationLoader.loadAnimation(sprites.get(key), 0, 3, 2, duration);
        anim[7] = AnimationLoader.loadAnimation(sprites.get(key), 0, 3, 3, duration);
            animations.put(key, anim);
            
        }
        
      
    
    }

    
    
    
}
