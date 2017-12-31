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
public class TileSolid extends Tile{
    public int solidTileDown;
    private static int solidTileSetID = 0;
    
    private static int solidTileId =1 ;
    
    
    public TileSolid(TiledMap map) {
        super(map);
       
        
    }
    
   public int compteurTileSolid(TiledMap map){
       
       for (int i = 0; i < map.getWidth(); i++) {
            for (int j = 0; j < map.getHeight(); j++) {
                
                if (map.getTileId(i, j, solidTileSetID) == solidTileId) {

                    solidTileDown++;
                }
                
            }
        }
       return solidTileDown;
        
   }

   
    
    
}
