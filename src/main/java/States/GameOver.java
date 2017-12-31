/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;


import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author Wail
 */
public class GameOver extends BasicGameState{
  private AngelCodeFont fontTitre;
    private AngelCodeFont fontMenu;
       private Image imgFontTitre;
    private Image imgFontMenu;
    
    @Override
    public int getID() {
        return Etat.GAME_OVER;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame s) throws SlickException {
        imgFontTitre = new Image("res/text/font/font_0.png");
        imgFontMenu = new Image("res/text/font/fontMenu_0.png");
        fontTitre = new AngelCodeFont("res/text/font/font.fnt", imgFontTitre);
        fontMenu = new AngelCodeFont("res/text/font/fontMenu.fnt", imgFontMenu);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
       fontTitre.drawString(0, 0, "You Dieded");
       
       
       
    }

    @Override
    public void update(GameContainer gc, StateBasedGame s, int d) throws SlickException {
        
    }

    
    
}
