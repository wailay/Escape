/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Entities.*;

import Graphique.Item.Item;
import Graphique.Tile.HelperGuyTile;
import Graphique.Tile.Tile;
import Level.*;
import escape.Fenetre;
import java.util.ConcurrentModificationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.*;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.transition.EmptyTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;
import org.newdawn.slick.state.transition.VerticalSplitTransition;

/**
 *
 * @author Ayad
 */
public class StartZoneState extends BasicGameState {

    Map map;
    StartZone startMap;
    Transition t , t2;
    MouseOverArea moa;
    Item item,item2;

    @Override
    public void init(GameContainer gc, StateBasedGame s) throws SlickException {

        map = new Map(gc);
        startMap = new StartZone(gc);
        t = new FadeOutTransition();
        t2 = new FadeInTransition();

      
       
        
    }

    boolean dbug = false;

    @Override
    public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
        g.scale(startMap.getScale(), startMap.getScale());
        g.translate(Fenetre.offsetX - map.getPlayer().getX(), Fenetre.offsetY - map.getPlayer().getY());

        if (map.getPlayer().getX() <= 364 && map.getPlayer().getY() <= 265) {
            startMap.setScale(4);
            Fenetre.offsetX = 128;
            Fenetre.offsetY = 96;
            map.renderLayer(g, startMap, 0);
            map.renderLayer(g, startMap, 1);
            map.renderLayer(g, startMap, 2);
            map.renderLayer(g, startMap, 3);
        } else {
            startMap.setScale(2);
            Fenetre.offsetX = 256;
            Fenetre.offsetY = 192;
            map.render(g, startMap);
            
         
        
        }
        
        map.getPlayer().render(g);
        try{
        for (Item item : startMap.getListeItem()) {

            item.render(g);

        }
        }catch(ConcurrentModificationException e){}

        for (Entity en : startMap.getListeEntities()) {
            en.render(g);
        }

        try {
            for (Item.TypeItem typeItem : LevelManager.getEquipedItem().keySet()) {
                if(typeItem == Item.TypeItem.WEAPON){
                     LevelManager.getEquipedItem().get(typeItem).renderEquipedItem(g, map.getPlayer());
                }
              
            }

        } catch (NullPointerException e) {
        }
        
        
        dialogueMap1(gc);
       LevelManager.getHud().renderDialogue(g, map,HelperGuyTile.getText());
       
        //----------------------------
        startMap.dbug(gc, g);
        LevelManager.getHud().render(g, map);
         
       
    }
    boolean executeOnce = true, kidnap = false;
    int i;
     public void dialogueMap1(GameContainer gc) throws SlickException{
        if(HelperGuyTile.getFois()==1){
            HelperGuyTile.setShowDialog(true);
            HelperGuyTile.setText("Bonjour, je suis ta conscience, tu viens \n de te reveiller ?"
                    + "Tu peux me parler en \nappuyant sur la touche E");
            
            
        }
        if(HelperGuyTile.getFois()==2){
             HelperGuyTile.setText("Bien ! Tu a l'air d'etre en forme, essaye \nde faire le tour du village avec les \ntouches W A S D!");
        }
        if(HelperGuyTile.getFois()==3){
              HelperGuyTile.setText("Super ! Si tu trouves que tu es trop \nlent, tu peux courir en appuyant la \ntouche SHIFT, essaye !");
        }
        if(HelperGuyTile.getFois()==4){
              HelperGuyTile.setText("Tu sais que tu peux ouvrir ton sac a dos \navec la touche I. C'est un sac magique il \npeux contenir de precieuses choses ");
        }
        if(HelperGuyTile.getFois()==5){
              HelperGuyTile.setText("Il est vide en ce moment, mais au cours \nde ton aventure tu trouveras des coffres\n que tu pourras ouvrire avec la touche E");
        }
        
        if(HelperGuyTile.getFois()==6){
              HelperGuyTile.setText("Ces coffres contiennent des items qui \nt'aideront beaucoup.");
        }
        if(HelperGuyTile.getFois()==7){
              HelperGuyTile.setText("Parlons d'items, je vais t'offrir une \narme legendaire pour que tu puisse \nt'entrainer");
              
        }
        if(HelperGuyTile.getFois()==8){
            HelperGuyTile.setText("Tu peux ramasser un item en t'approchant \nde lui et en appuyant sur la touche F");
            if(executeOnce){
                
                executeOnce = false;
                  item = new Item(new Vector2f(340,560));
        
              item.setId(24);
        item.getItemStats();
        startMap.getListeItem().add(item);
            }
        }
        if(HelperGuyTile.getFois()==9){
            HelperGuyTile.setText("En ouvrant ton inventaire tu trouveras\n ton arme a l'interieur. Utilise \nla souris pour t'en équiper."
                    + "Il ya 5 types\n d'items que tu peux equiper.");
        }
            
        if(HelperGuyTile.getFois()==10){
            HelperGuyTile.setText("A cote, tu trouveras les monstres\n typiques de la regions, "
                    + "pourquoi \nn'essayerais-tu pas cette nouvelle épee");
        }
        if(HelperGuyTile.getFois()==11){
             HelperGuyTile.setText("Pour attaquer un ennemi, apporche toi de \nlui et appui sur le bouton gauche de la \nsouris. Attention a ne pas trop \nt'approcher sinon tu prendra des degats");
        }
        
        if(HelperGuyTile.getFois()==12){
            HelperGuyTile.setShowDialog(false);
            
        }
         if((map.getPlayer().getHitPoints()/map.getPlayer().getMaxHP())*100<75){
            HelperGuyTile.setShowDialog(true);
               HelperGuyTile.setText("Si tu prend trop de degats, appui sur la \ntouche 1 pour boire une potion\n et gagner de la vie");
            
        }
          if(HelperGuyTile.getFois()==13){
               HelperGuyTile.setShowDialog(true);
               HelperGuyTile.setText("Tu es doue ! Je pense que je t'ai tout \nmontre. Il est l'heure de dormir. On se \nreverra demain matin pour un autre\n entrainement");
          }
          if(HelperGuyTile.getFois()==14){
               HelperGuyTile.setShowDialog(false);
               kidnap = true;
               executeOnce = true;
          }
          
         
        
    }
        
     
    
    boolean a = false;
    
    @Override

    public void update(GameContainer gc, StateBasedGame s, int d) throws SlickException {

        map.getClavier().load(gc);

        map.getPlayer().update(map.getClavier().keys, d, startMap, gc);
        for (Entity en : startMap.getListeEntities()) {
            en.update(d, startMap, map.getPlayer());
            
        }
       if(kidnap){
            if(startMap.isPlayerTped(startMap, map.getPlayer())){
                
                 s.enterState(Etat.PRISON_ZONE_1, t, t2);
                map.getPlayer().setX(100);
                map.getPlayer().setY(100);
               
            
       }
            }
       
        

        LevelManager.gameOver(s);
        Etat.stateManager(s, gc, map.getClavier());
    }

    @Override
    public int getID() {

        return Etat.START_ZONE;
    }

}
