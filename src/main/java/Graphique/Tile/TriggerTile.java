/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Tile;

import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author wail
 */
public class TriggerTile extends Tile {

    private int DOOR = 2;
    private int OPENED_DOOR = 3;
    private int CHEST = 4;
    private int OPENED_CHEST = 5;
    
    private int type;
    public int triggerTileDown;
    
   

    public enum TypeTile {
        DOOR, OPENED_DOOR, CHEST, OPENED_CHEST, OPENED_METAL_DOOR,METAL_DOOR 
    }

    public TypeTile typeTile;
    private int triggerTileLayer = 2;
    
    public TriggerTile(TiledMap map) {
        super(map);
      
        switch(type){
            case 2 : typeTile = TypeTile.DOOR; break ;
            case 3 : typeTile = TypeTile.OPENED_DOOR; break ;
            case 4 : typeTile = TypeTile.CHEST; break ;
            case 5 : typeTile = TypeTile.OPENED_CHEST; break ;
            case 6 : typeTile = TypeTile.OPENED_METAL_DOOR; break ;
            case 7 : typeTile = TypeTile.METAL_DOOR; break ;
        }
        
    }
    /**
     * Compte le nombre de tuile qui peuvent etre activé sur la map
     * @param map La map courante
     */
    public void compteurTile(TiledMap map) {
        //id premiere tuile a trigger
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                //id de la derniere tuile a trigger
                
                switch (map.getTileId(i, j, triggerTileLayer)) {
                    case 2:
                        triggerTileDown++;
                        break;
                    case 3:
                        triggerTileDown++;
                        break;
                    case 4:
                        triggerTileDown++;
                        break;
                    case 5:
                        triggerTileDown++;
                        break;
                    case 6:
                        triggerTileDown++;
                        break;
                    case 7:
                        triggerTileDown++;
                        break;
                }

            }
        }

    }

     
    
    

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
    

}
