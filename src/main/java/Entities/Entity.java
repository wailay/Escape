/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Level.LevelManager;
import Level.Map;
import escape.Fenetre;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author wail
 */
public class Entity {

    protected int duration;
    protected float hitPoints, maxHP;
    protected int height, width,hitBoxWidth, hitBoxHeight;
    protected int direction;
    protected float vitesseX = 0.05f, vitesseY = 0.05f;
    protected SpriteSheet hero, bat, squeleton,wolf,spider;
    protected float x, y;

    protected Animation[] anim;

    protected boolean moving = true, collided;
    protected Rectangle box, privateBox;
    protected Circle triggerRadius, chaseRadius;
    boolean hit;
    protected float damage;
    
    public Entity(float x, float y) {
        this.hitPoints = 100;
        
        this.x = x;
        this.y = y;
    }
    /**
     * Initialise l'image de l'entité pour la creer
     * @param path Chemin des textures de l'entité
     * @param tw Largeur d'une tuile d'image de l'entité
     * @param th Hauteur d'une tuile d'image de l'entité
     * @throws SlickException 
     */
    public void createEntity(String path, int tw, int th) throws SlickException {

    }
    /**
     * Affiche l'entité sur l'écran
     * @param g
     * @throws SlickException 
     */

    public void render(Graphics g) throws SlickException {
        box = new Rectangle(x, y, hitBoxWidth, hitBoxHeight);
       
        
        chaseRadius = new Circle(x + (width / 2), y + (height / 2), 60);
        renderHealthBar(g);
        
    }

    float timePassed = 0;

    /**
     * Mise à jour de la position des entités du jeux, c'est a dire les ennemis.
     * Lorsque l'entité n'est pas sur l'écran elle ne bouge, l'entité est mise a
     * joue seulement lorsqu'elle se trouve sur l'écran
     *
     * @param d La constante delta par rapport au temps.
     * @param map La map courante qui se fait render
     * @param player Le joueur de notre jeux
     */
    public void update(int d, Map map, Player player) {
        if(moving){
        if (x + width > (player.x - Fenetre.offsetX) && y + height > (player.y - Fenetre.offsetY)) {
            map.checkCollision(this);
            map.checkTriggerCollision(this);
            detection(map, player, d);
            attack(player);
            if(box.intersects(player.attackHitBox)){
            
            if(player.attacking){
                
                hitPoints -= player.getDamage();
            }
        }
        }
        }
        
    }

    /**
     * Lorsque le joueur se trouve à l'interieur d'un certain radius d'une
     * entité il se fait alors detecter. L'entité va alors commencer a le
     * chaser. Les position de l'entité sont determiné par la trigonométrie
     * d'une triangle
     *
     * @param map La map courante qui se fait render
     * @param player Le joueur de notre jeux
     * @param d La constante delta par rapport au temps.
     */
    public void detection(Map map, Player player, int d) {
        float destinationX = player.x;
        float destinationY = player.y;
        float differenceX = destinationX - this.x;
        float differenceY = destinationY - this.y;
        float angle = (float) Math.atan2(differenceY, differenceX);

        if (chaseRadius.intersects(player.box)) {
            if (Math.abs(Math.round(angle)) == 1) {
                direction = 0;
            }
            if (Math.abs(Math.round(angle)) == 0) {
                direction = 1;
            } else {
                direction = Math.abs(Math.round(angle));
            }
            this.x += Math.cos(angle) * vitesseX * d;
            this.y += Math.sin(angle) * vitesseY * d;
        } else {
            randomMovement(map, d);
        }

    }
    
   
    int timer = 0;
    boolean firstHit = true;
    /**
     * Lorsque le joueur rentre en collision avec un entité. celle-ci va
     * l'attaquer et lui infliger des degats.
     *
     * @param player Le joueur de notre jeux
     */
    
    public void attack(Player player) {

       
        if (box.intersects(player.box)) {
            timer++;
            if(firstHit){
                
                firstHit = false;
                player.setHitPoints(player.getHitPoints()- (damage));
                
            }
            
            if(timer%50==0){
                  player.setHitPoints(player.getHitPoints()- (damage));
                  if(timer>=0xFFF) timer=0;
            }
          
         
          
            
            

        } else {
            firstHit = true;
            player.setPlayerHitColor(null);
        }
        
    }

    /**
     * Lorsque une entité est assez loin du joueur, elle agit comme une
     * sentinelle dont ces mouvements et sa direction sont aléatoires. Elle se
     * met a jour à chaque 65 frames pour rendre le mouvement comme celui d'une
     * sentinelle.
     *
     * @param map La map courante qui se fait render
     * @param d La constante delta par rapport au temps.
     *
     */
    public void randomMovement(Map map, int d) {
        if (timePassed == 65) {
            timePassed = 0;
            changeDirection();
        }
        if (isCollided(map)) {

            changeDirection();

        }
        switch (direction) {
            case 0:
                y -= vitesseY * d;
                timePassed++;
                break;
            case 1:
                x += vitesseX * d;
                timePassed++;
                break;
            case 2:
                y += vitesseY * d;
                timePassed++;
                break;
            case 3:
                x -= vitesseX * d;
                timePassed++;
                break;
        }
    }

    /**
     * Cette méthode choisit une direction de l'entité au hasard
     */
    public void changeDirection() {
        Random rand = new Random();
        int randDir = 0;
        do {

            randDir = rand.nextInt(4);
        } while (randDir == direction);

        direction = randDir;

    }

    public void renderHealthBar(Graphics g){
        
        g.setColor(new Color(58,31,40));
          
        g.fillRect(x, y, 26.5f, 2.5f);
        g.fillRect(x-0.5f, y-0.5f, 26.5f, 3f);
        g.setColor(Color.green);
        g.fillRect(x, y, (hitPoints / maxHP) * 26.0f, 2.0f);
        g.setColor(Color.white);
        
       
    }
    public boolean dead() {
        if(hitPoints<=0){
            hitPoints=0;
                }
        return hitPoints ==0;
    }

    public int getDirection() {
        return direction;
    }

    public boolean isCollided(Map map) {
        return map.isInCollision(this);
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

    public Rectangle getBox() {
        return box;
    }

    public Circle getTriggerRadius() {
        return triggerRadius;
    }

    public void setHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
    }

    public float getHitPoints() {
        return hitPoints;
    }
    
    
}
