package escape;

import States.GameOver;
import States.Inventaire;

import States.StartZoneState;

import States.MenuPrincipalState;
import States.PrisonMapState;
import States.PrisonZone1State;
import States.StartZoneState;
import java.io.File;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.lwjgl.openal.AL;

/**
 *
 */
public class Game extends StateBasedGame {
    GameContainer container;
    
   
    
    public static boolean _APPLET = true;
    
    public Game(String title) {
        super(title);
    }
    
    public static void main(String args[]) throws SlickException {
        
        System.setProperty("org.lwjgl.librarypath", new File("target/natives").getAbsolutePath());
       
        
        _APPLET = false;
        AppGameContainer app = new AppGameContainer(new Game("Escape"));
        //app.setDisplayMode(1366, 768, true);
        //app.setFullscreen(true);
        
        app.setDisplayMode(Fenetre.WIDTH*Fenetre.SCALE, Fenetre.HEIGHT*Fenetre.SCALE, false);
        
        app.start();
        
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
       
        gc.setTargetFrameRate(60);
        gc.setAlwaysRender(true);
        gc.setMaximumLogicUpdateInterval(60);
        gc.setVSync(true);
        
       
        gc.setUpdateOnlyWhenVisible(true);
        gc.setSmoothDeltas(true);
        
        addState(new MenuPrincipalState());
        addState(new StartZoneState());
        addState(new Inventaire());
        addState(new GameOver());
        addState(new PrisonZone1State());
        addState(new PrisonMapState());
        
    
        
        
        
        
    }
    
    

   

     
}
