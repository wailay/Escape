/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Tile;

import Entities.Player;
import Level.Map;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author wail
 */
public class Tile {
    protected boolean chestOpened;
    protected int SIZE, x, y, width, height, triggerTileLayer = 2, solidTileSet = 0;
    protected int id;
    private boolean solid, isTriggerable;

    public static final int LOAD_TILE_ID = 6;

    private String nameset;


    public Tile(TiledMap map) {

        this.width = map.getTileWidth();
        this.height = map.getTileHeight();

        this.solid = isSolid(map);

    }

    /**
     * Methode qui prend en parametre les positions du joueur et retourne l'ID de
     * la tuile ou le joueur se trouve
     * @param map Reference de la map courante
     * @param x Position x du joueur
     * @param y Position y du joueur
     * @return ID de la tuile
     */
    public int getTile(Map map, float x, float y) {
        TiledMap tiledMap = map.getTiledMap();

        
        return tiledMap.getTileId((int) x / 32, (int) y / 32, solidTileSet);

    }

    public String getNameset() {
        return nameset;
    }
    /**
     * Methode qui retourne la position, en pixel, en X d'une tuile selon son ID
     * @param map La map ou la tuile se trouve
     * @param id  L'ID de la tuile
     * @return Retourne la position de la tuile en pixel en Float
     */
    public float getX(Map map,int id) {
        float x = 0 ;
        TiledMap tiledMap = map.getTiledMap();
        for (int i = 0; i < tiledMap.getWidth(); i++) {
            for (int j = 0; j < tiledMap.getHeight(); j++) {
                if(tiledMap.getTileId(i, j, solidTileSet)==id){
                   x = i;
                }
            }
        }
     return x*32;
    }
     /**
     * Methode qui retourne la position, en pixel, en Y d'une tuile selon son ID
     * @param map La map ou la tuile se trouve
     * @param id  L'ID de la tuile
     * @return Retourne la position de la tuile en pixel en Float
     */
    public float getY(Map map, int id) {
        float y = 0;
        TiledMap tiledMap = map.getTiledMap();
        for (int i = 0; i < tiledMap.getWidth(); i++) {
            for (int j = 0; j < tiledMap.getHeight(); j++) {
                if(tiledMap.getTileId(i, j, solidTileSet)==id){
                    y = j;
                }
            }
        }
     return y * 32;
    }
    public int getx(){
        return x;
    }
    public int gety(){
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        
        return id;
    }
    

    

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    /**
     * Permet de savoir si une tuile est considéré comme solide
     * @param map La map dans laquelle la tuile se trouve
     * @return True : La tuile est solide 
     *  False : La tuile n'est pas solide
     */
    public boolean isSolid(TiledMap map) {

        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if (map.getTileId(i, j, 0) == 1 || map.getTileId(i, j, 2) != 3) {
                    solid = true;
                } else {
                    solid = false;
                }

            }
        }

        return solid;

    }
    

    public boolean isChestOpened() {
        
            if(this instanceof OpenedChestTile){
                return true;
            }
        
        return false;
        
    }

    public void setChestOpened(boolean chestOpened) {
        this.chestOpened = chestOpened;
    }
    

    
    
    

}
