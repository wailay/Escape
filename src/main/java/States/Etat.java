/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import escape.InputHandler;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.state.GameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.CrossStateTransition;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.state.transition.Transition;

/**
 *
 * @author Ayad
 */
public class Etat {
     
    public final static int START_ZONE = 1;
    public final static int MENU = 0;
    public final static int INVENTAIRE = 2;

    public final static int GAME_OVER = 3, PRISON_ZONE_1 = 4,PRISON_ZONE_2 = 7,PRISON_ZONE_3 = 8,PRISON_ZONE_4 = 9,PRISON = 6;

    private static Transition transitionFadeOut;
    private static Transition transitionFadeIn;
    private static Transition crossStateTrans;
    private static int previousStateID;
    /**
     * Gere l'action des états principaux lorsque une touche est appuyée
     * @param s
     * @param gc
     * @param clavier Le clavier actuel utilisé par le joueur
     */
    public static void stateManager(StateBasedGame s, GameContainer gc, InputHandler clavier){
         transitionFadeOut = new FadeOutTransition();
        transitionFadeIn = new FadeInTransition();
      
        
        
        if(s.getCurrentStateID()==START_ZONE)previousStateID = START_ZONE;
        if(s.getCurrentStateID()==PRISON_ZONE_1)previousStateID = PRISON_ZONE_1;
        if(s.getCurrentStateID()==PRISON_ZONE_2)previousStateID = PRISON_ZONE_2;
        if(s.getCurrentStateID()==PRISON_ZONE_3)previousStateID = PRISON_ZONE_3;
        if(s.getCurrentStateID()==PRISON_ZONE_4)previousStateID = PRISON_ZONE_4;
        
            
            if(s.getCurrentStateID()!=MENU){
            if(clavier.keys[12]){
                
                s.enterState(INVENTAIRE);
            }
            }
            if(clavier.keys[13]){
                
                s.enterState(MENU,transitionFadeOut, transitionFadeIn);
            }
            
        
        if(s.getCurrentStateID()==INVENTAIRE){
            if(gc.getInput().isKeyPressed(Input.KEY_I)){
                
                s.enterState(previousStateID);
            }
            
            
        }
      
    }
   
    
    
    
}
