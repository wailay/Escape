/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Graphique.AnimationLoader;
import Graphique.Item.Item;
import Graphique.Particule;
import Level.LevelManager;
import Level.Map;
import Level.Prison.PrisonMap;
import Sound.SoundLoader;
import States.Inventaire;
import escape.MouseArea;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author wail
 */
public class Player extends Entity {

    private ArrayList<Particule> listeParticule = new ArrayList<>();
    private float width, height, radius = 25, damage = 10, armor = 25, HP, maxMana = 100, manaPoints = 100, money, particulePosX, particulePosY, range;
    private boolean up, down, left, right, hit;
    public boolean attacking;
    Particule particule;
    public Rectangle attackHitBox;

    private Color playerHitColor;

    public Player(String path, int width, int height) throws SlickException {
        super(95, 60);
        this.maxHP = 100;
        this.duration = 130;
        this.vitesseX = 0.2f;
        this.vitesseY = 0.2f;

        anim = new Animation[8];
        listeParticule = new ArrayList<>();
        this.width = width;
        this.height = height;
        createEntity(path, width, height);

        try {
            particule = new Particule();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override

    public void render(Graphics g) throws SlickException {
        this.box = new Rectangle(x, y, width, height);

        triggerRadius = new Circle(x + (width / 2), y + (height / 2), radius);
        attackHitBox = new Rectangle(x - 10, y, width + 20, height + 10);
      
        g.drawAnimation(anim[direction + (moving ? 4 : 0)], x, y, playerHitColor);
        renderHealthBar(g);
        //----- projectile
        if (!listeParticule.isEmpty()) {
            for (Particule spell : listeParticule) {
                try {

                    spell.getSpellsParticule().render();

                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void attack(boolean mbd, int d, GameContainer gc) throws IOException {
        if (!LevelManager.getEquipedItem().isEmpty()) {
            if (LevelManager.getEquipedItem().containsKey(Item.TypeItem.WEAPON)) {
                for (Item.TypeItem typeItem : LevelManager.getEquipedItem().keySet()) {
                    if(LevelManager.getEquipedItem().get(typeItem).getTypeWeapon()==Item.TypeWeapon.WAND){
                    if (gc.getInput().isMousePressed(0)) {
                        listeParticule.add(new Particule());
                        for (Particule p : listeParticule) {
                            p.x = x;
                            p.y = y;

                        }
                    }
                    }
                    if (LevelManager.getEquipedItem().get(typeItem).getTypeWeapon()==Item.TypeWeapon.MELEE) {

                    if (gc.getInput().isMousePressed(0)) {

                        attacking = true;
                    } else {
                        attacking = false;
                    }
                }
                }
                
            }
        }

    }
    private int timer, timerMana;

    @Override
    public void renderHealthBar(Graphics g) {
        timer++;
        timerMana++;
        if (timer % 90 == 0) {
            if (hitPoints != maxHP) {
                if ((hitPoints + 5) > maxHP) {
                    hitPoints = hitPoints + (maxHP - hitPoints);
                } else {
                    hitPoints += 5;
                }
            }
            if (timer >= 0xFFF) {
                timer = 0;
            }
        }
        if (timerMana % 60 == 0) {
            if (manaPoints != maxMana) {
                if ((manaPoints + 3) > maxMana) {
                    manaPoints = manaPoints + (maxMana - manaPoints);
                } else {
                    manaPoints += 3;
                }
            }
            if (timerMana >= 0xFFF) {
                timerMana = 0;
            }
        }
        g.setColor(new Color(58, 31, 40));

        g.fillRect(x, y, 26.5f, 2.5f);
        g.fillRect(x - 0.5f, y - 0.5f, 26.5f, 3f);
        g.setColor(Color.green);
        g.fillRect(x, y, (hitPoints / maxHP) * 26.0f, 2.0f);
        g.setColor(Color.white);

       

    }

    @Override
    public void createEntity(String path, int tw, int th) throws SlickException {
        hero = new SpriteSheet(path, tw, th);
        anim[0] = AnimationLoader.loadAnimation(hero, 1, 2, 0, duration);
        anim[1] = AnimationLoader.loadAnimation(hero, 1, 2, 1, duration);
        anim[2] = AnimationLoader.loadAnimation(hero, 1, 2, 2, duration);
        anim[3] = AnimationLoader.loadAnimation(hero, 1, 2, 3, duration);
        anim[4] = AnimationLoader.loadAnimation(hero, 0, 3, 0, duration);
        anim[5] = AnimationLoader.loadAnimation(hero, 0, 3, 1, duration);
        anim[6] = AnimationLoader.loadAnimation(hero, 0, 3, 2, duration);
        anim[7] = AnimationLoader.loadAnimation(hero, 0, 3, 3, duration);

    }

    public void update(boolean[] keys, int d, Map map, GameContainer gc) throws SlickException {

        move(keys[0], keys[2], keys[1], keys[3], keys[9], keys[14], d, map);

        updateTrigger(d, keys[10], keys[15], this, map, gc);
        try {
            attack(keys[16], d, gc);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!listeParticule.isEmpty()) {
            for (Particule spell : listeParticule) {
                try {

                    spell.getSpellsParticule().update(d);
                   
//                if((spell.x+=12) >= range*100 || spell.y >= range*100){
//                    SwingUtilities.invokeLater(new Runnable() {
//                        @Override
//                        public void run() {
//                           listeParticule.remove(spell);
//                        }
//                    });
//                    
//                }
                } catch (IOException ex) {
                    Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        takePotion(gc);
        if (hitPoints <= 0) {
            LevelManager.setGameOver(true);
        }

    }

    public void takePotion(GameContainer gc) {
        if (gc.getInput().isKeyPressed(Input.KEY_1)) {
            if (hitPoints < maxHP) {
                hitPoints += 30;
                if (maxHP - hitPoints < 30) {
                    hitPoints = hitPoints + (maxHP - hitPoints);
                }
            }
        }
    }

    public void updateTrigger(int d, boolean e, boolean f, Entity player, Map map, GameContainer gc) throws SlickException {

        if (e) {

            map.checkTrigger(this, map);

        }
        if (f) {

            map.itemPickUp(this, map);

        }

    }

    public void move(boolean up, boolean down, boolean right, boolean left, boolean moving, boolean shift, int d, Map map) {
        this.moving = moving;
        if (shift) {
            vitesseX = 0.4f;
            vitesseY = 0.4f;
        } else {
            vitesseX = 0.2f;
            vitesseY = 0.2f;
        }
        if (this.moving) {
            if (up) {
                direction = 0;
                y -= vitesseY * d;

            }
            if (right) {
                direction = 1;
                x += vitesseX * d;
            }
            if (down) {
                direction = 2;
                y += vitesseY * d;
            }

            if (left) {
                direction = 3;
                x -= vitesseX * d;

            }

            map.checkCollision(this);

            map.checkTriggerCollision(this);

        }

    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public boolean isHit() {
        return hit;
    }

    public Color getPlayerHitColor() {
        return playerHitColor;
    }

    public void setPlayerHitColor(Color playerHitColor) {
        this.playerHitColor = playerHitColor;
    }

    public String getBaseStats() {
        damage = 10;
        maxHP = 100;
        armor = 100;
        maxMana = 100;
        return "Player stats\n\n"
                + damage + " Damage\n\n"
                + maxHP + " Health points\n\n"
                + armor + " Strength\n\n"
                + maxMana + " Mana Points";
    }

    public String getStats(Item item) {

        return "Player stats\n\n"
                + (damage) + " Damage\n\n"
                + (maxHP) + " Health points\n\n"
                + (armor) + " Strength\n\n"
                + maxMana + " Mana Points";
    }

    public void calculateStats(Item item) {

        
        maxMana = maxMana + item.getMana();
        damage = damage + item.getDamage();
        maxHP = maxHP + ((item.getVitality() * maxHP) / 100);
        armor = armor + item.getArmor();
        range = item.getRange();

    }

    public void removeStats(Item item) {

        manaPoints = manaPoints - item.getMana();
        damage = damage - item.getDamage();
        maxHP = maxHP - ((item.getVitality() * maxHP) / 100);
        armor = armor - item.getArmor();

    }

    public void getLoot() {

    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public boolean isMoving() {
        return moving;
    }

    public Circle getTriggerRadius() {
        return triggerRadius;
    }

    public float getMaxHP() {
        return maxHP;
    }

    public float getManaPoints() {
        return manaPoints;
    }

    public float getMaxMana() {
        return maxMana;
    }

    public void setManaPoints(float manaPoints) {
        this.manaPoints = manaPoints;
    }

    public void setMaxHP(float maxHP) {
        this.maxHP = maxHP;
    }

    public void setMaxMana(float maxMana) {
        this.maxMana = maxMana;
    }

    public float getDamage() {
        return damage;
    }

    public float getMoney() {
        return money;
    }

}
