/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Tile;

import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author 1526328
 */
public class TileLoad extends Tile{
    
    private static final int TP_TILE_ID = 8;
    
    
    
    
    public  TileLoad(TiledMap map) {
        super(map);
        this.id = 8;
    }
    
    
    public void compteurTileMap(TiledMap map){
        
        for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                if(map.getTileId(i, j, solidTileSet) == TP_TILE_ID){
                    System.out.println("allo");
            }
                
            }
        }
        
    }
}
