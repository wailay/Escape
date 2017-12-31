/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique.Tile;

import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Wail
 */
public class HelperGuyTile extends Tile{
    
    private static String text;
    private static int fois;
    private static boolean showDialog = false;
    
    public HelperGuyTile(TiledMap map) {
        super(map);
        this.id = 13;
    }

    public static String getText() {
        return text;
    }

    public static void setText(String text) {
        HelperGuyTile.text = text;
    }

    public static void setShowDialog(boolean showDialog) {
        HelperGuyTile.showDialog = showDialog;
    }

    

    public static boolean isShowDialog() {
        return showDialog;
    }

    public static void setFois(int fois) {
        HelperGuyTile.fois = fois;
    }
    

    public static int getFois() {
        return fois;
    }
    

   
    
    
}
    