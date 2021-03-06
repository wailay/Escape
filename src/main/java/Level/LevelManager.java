/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Level;

import Entities.Entity;
import Entities.Player;
import Entities.Player2;
import Graphique.HUD;
import Graphique.Item.Item;
import Graphique.Item.ItemTexture;
import Graphique.Tile.Tile;
import States.Etat;
import escape.InputHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author 1526328
 */
public class LevelManager {
    
    
    protected static LinkedHashMap<Item.TypeItem, Item> equipedItem = new LinkedHashMap<>();
    protected static HUD hud;
    protected ArrayList<Entity> listeEntities = new ArrayList();
    protected static ArrayList<Item> inventaire = new ArrayList();
    
    private Tile tile;
    protected Map map;
    protected static ItemTexture itemTexture;
    protected static Player player;
    protected static Player2 player2;
    protected InputHandler clavier;
    
    protected ArrayList<Item> listeItem;
    

    Random r = new Random();

    

   
    protected static boolean gameOver = false;

    public LevelManager() throws SlickException {
           
         player = new Player("res/text/hero/Heroes/player1.png", 24, 32);
         player2 = new Player2("res/text/hero/Heroes/player2.png", 24, 32);
         clavier =  new InputHandler();
         itemTexture = new ItemTexture();
         listeItem = new ArrayList<>();
        hud = new HUD();
          listeEntities = new ArrayList<>();
          
          
    }
    /**
     * Fin du jeux lorsque le joueur a 0 vie
     * @param s 
     */
    public static void gameOver(StateBasedGame s){
   
        if(gameOver){
            s.enterState(Etat.GAME_OVER);
        }
    }

    public static LinkedHashMap<Item.TypeItem, Item> getEquipedItem() {
        return equipedItem;
    }

    public static Player getPlayer() {
        return player;
    }

    public InputHandler getClavier() {
        return clavier;
    }

    public static ArrayList<Item> getInventaire() {
        return inventaire;
    }

    public static ItemTexture getItemTexture() {
        return itemTexture;
    }

    

    public ArrayList<Entity> getListeEntities() {
        return listeEntities;
    }

    public ArrayList<Item> getListeItem() {
        return listeItem;
    }

    public Map getMap() {
        return map;
    }

    public static Player2 getPlayer2() {
        return player2;
    }

    public Tile getTile() {
        return tile;
    }

    public static void setGameOver(boolean gameOver) {
        LevelManager.gameOver = gameOver;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static HUD getHud() {
        return hud;
    }

    public static void setHud(HUD hud) {
        LevelManager.hud = hud;
    }
    
    
    
    
    
}
