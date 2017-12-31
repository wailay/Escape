/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import Entities.Player;
import Graphique.Tile.HelperGuyTile;
import Level.Map;
import escape.Fenetre;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Wail
 */
public class HUD {
    AngelCodeFont font;
    AngelCodeFont font2;
    Image hudImage;
    Image potionHolder;
    Image potion;
    Image dialog;
    public HUD() throws SlickException {
        font = new AngelCodeFont("res/text/font/HUD_24pixel.fnt", new Image("res/text/font/HUD_24pixel_0.png"));
        font2 = new AngelCodeFont("res/text/font/HUD_8pixel.fnt", new Image("res/text/font/HUD_8pixel_0.png"));
        hudImage = new Image("res/text/HUD/hud.png");
        potionHolder = new Image("res/text/HUD/potionHolder.png");
        potion = new Image("res/text/HUD/potion.png");
        dialog = new Image("res/text/HUD/dialog.png");
        
    
    }
    
    
    private float cooldown,size=0;
    /**
     * Permet d'afficher l'interface graphique, contenant les informations du 
     * joueur, sur l'écran
     * @param g
     * @param map Map dans laquelle le joueur se trouve
     */
    public void render(Graphics g, Map map){
        float posX = (map.getPlayer().getX()-Fenetre.offsetX)/0.5f;
        float posY = (map.getPlayer().getY()-Fenetre.offsetY)/0.5f;
        
        
        
         
       g.scale(0.5f, 0.5f);
      renderHudInfo(g, map.getPlayer());
        g.drawImage(hudImage,posX,posY);
        
        font.drawString(posX+160,posY+60, "Health : ",Color.white);
        font.drawString(posX+250,posY+60, map.getPlayer().getHitPoints()+"/"+map.getPlayer().getMaxHP(),Color.white);
        
        font.drawString(posX+160,posY+80, "Mana   : ",Color.white);
        font.drawString(posX+250,posY+80, map.getPlayer().getManaPoints()+"/"+map.getPlayer().getMaxMana(),Color.white);
        
        font.drawString(posX+160,posY+100, "Damage : ",Color.white);
        font.drawString(posX+250,posY+100, map.getPlayer().getDamage()+"",Color.white);
        
        font.drawString(posX+160,posY+120, "Money  : ",Color.white);
        font.drawString(posX+250,posY+120, map.getPlayer().getMoney()+" $",Color.white);
        
         //potion
         
         g.drawImage(potionHolder, posX+320, posY+100);
         g.drawImage(potion, posX+320, posY+100);
         cooldown++;
         
         g.setColor(Color.green);
         if(size<=32){
             size++;
             
         }
          g.fillRect(posX+320, posY+132, size, 5);
         
         
         
        
         
    }
   /**
    * Faire un rendu des informations du joueur
    * @param g
    * @param player La reference du joueur actuel du jeux
    */
   public void renderHudInfo(Graphics g,Player player){
       float posX = (player.getX()-Fenetre.offsetX)/0.5f;
        float posY = (player.getY()-Fenetre.offsetY)/0.5f;
        //health bar
         GradientFill fill = new GradientFill(posX+119, posY+16, Color.green, posX+119+254, posY+16+17, new Color(99,183,141));
        
        g.setColor(new Color(58,31,40));
        g.fillRect(posX+122,posY+16, 251f, 17f);
        Rectangle rec = new Rectangle(posX+122, posY+16,(player.getHitPoints()/ player.getMaxHP()) * 251f, 17f);
        g.fill(rec, fill);
        
        //mana bar
       g.setColor(new Color(58,31,40));
        g.fillRect(posX+139,posY+37, 234f, 17f);
         g.setColor(new Color(34,0,78));
        g.fillRect(posX+139, posY+37, (player.getManaPoints()/ player.getMaxMana()) * 234f, 17f);
        
        
    }
   
   public void renderDialogue(Graphics g,Map map,String text){
       
        float posX = (map.getPlayer().getX()-Fenetre.offsetX);
        float posY = (map.getPlayer().getY()-Fenetre.offsetY);
        if(HelperGuyTile.isShowDialog()){
       g.drawImage(dialog, posX+100, posY+300);
       try{
       font2.drawString((posX)+104, (posY)+313, text);
       }catch(NullPointerException e){}
        }
       
       
   }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }
   
    
}
