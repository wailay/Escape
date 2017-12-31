/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;


import Sound.SoundLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Cursor;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.state.*;
import org.newdawn.slick.state.transition.*;

/**
 *
 * @author Ayad
 */
public class MenuPrincipalState extends BasicGameState {

    private AngelCodeFont fontTitre;
    private AngelCodeFont fontMenu;
    SoundLoader soundLoader;
    private Image imgFontTitre;
    private Image imgFontMenu;
    private Transition transition;
    private Transition transition2;
    private Color colorPlayGame = Color.white;
    private Color colorHowPlay = Color.white;
    private Color colorAbout = Color.white;
    
    private Image imageAbout;
    

    @Override
    public void init(GameContainer gc, StateBasedGame s) throws SlickException {
        imgFontTitre = new Image("res/text/font/font_0.png");
        imgFontMenu = new Image("res/text/font/fontMenu_0.png");
        fontTitre = new AngelCodeFont("res/text/font/font.fnt", imgFontTitre);
        fontMenu = new AngelCodeFont("res/text/font/fontMenu.fnt", imgFontMenu);
       
        imageAbout = new Image("res/text/inutile/about.png");
        soundLoader = new SoundLoader();
    }

    int posCurseurMenuX = 100;
    int posCurseurMenuY = 200;
    int posFontMenu1 = 100;
    int posFontMenu2 = 100;
    int posFontMenu3 = 100;
    @Override
    public void render(GameContainer gc, StateBasedGame s, Graphics g) throws SlickException {
        transition = new FadeInTransition();
        transition2 = new FadeOutTransition();

        fontTitre.drawString(70, 50, "Escape");
        
        fontMenu.drawString(posFontMenu1, 200, "Play Game", colorPlayGame);
        fontMenu.drawString(posFontMenu2, 270, "How to Play", colorHowPlay);
        fontMenu.drawString(posFontMenu3, 340, "About", colorAbout);
        
        int mousePosY = Mouse.getY();
        int mousePosX = Mouse.getX();

        //Fleche deroulante
        fontMenu.drawString(posCurseurMenuX, posCurseurMenuY, "->");
        if (gc.getInput().isKeyPressed(Input.KEY_DOWN)) {
            SoundLoader.menuSound.play();
            if (posCurseurMenuY < 340) {
                posCurseurMenuY += 70;
            }
        }
        if (gc.getInput().isKeyPressed(Input.KEY_UP)) {
            
            SoundLoader.menuSound.play();
            
            if (posCurseurMenuY > 200) {
                posCurseurMenuY -= 70;
            }

        }
        
        //Play game
        if (posCurseurMenuY == 200) {
             posFontMenu1 = 150;
            posFontMenu2 = 100;
            posFontMenu3 = 100;
            colorPlayGame = Color.yellow;
            if (gc.getInput().isKeyDown(Input.KEY_ENTER)) {
                SoundLoader.menuSound.play();
                //SoundLoader.musicJeux.setVolume(-1);
                //SoundLoader.musicJeux.loop();
                
                s.enterState(Etat.START_ZONE,transition2, transition);
            }

        } else {
            colorPlayGame = Color.white;
        }

        //How to play
        if (posCurseurMenuY==270) {
            posFontMenu1 = 100;
            posFontMenu2 = 150;
            posFontMenu3 = 100;
            colorHowPlay = Color.yellow;
            

            g.setColor(Color.red);
            g.drawString("JE C PAS LOL", 300, 300);

        } else {
            colorHowPlay = Color.white;
        }
        //About
        if (posCurseurMenuY==340) {
             posFontMenu1 = 100;
            posFontMenu2 = 100;
            posFontMenu3 = 150;
            colorAbout = Color.yellow;
            
            g.drawString("wassim et wail", 300, 300);

        } else {
            colorAbout = Color.white;
        }

     
        

    }
    

    @Override
    public void update(GameContainer gc, StateBasedGame s, int d) throws SlickException {

    }

    @Override
    public int getID() {

        return Etat.MENU;
    }
}
