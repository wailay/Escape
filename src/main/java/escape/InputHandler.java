/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escape;

import Entities.Player;
import Level.Map;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.util.InputAdapter;

/**
 *
 * @author wail
 */
public class InputHandler  {

    public boolean[] keys = new boolean[1000];
    public boolean up, down, right, left, moving, moving2, e, trigger, i;
    public boolean upA, downA, rightA, leftA, mouseLeftButton;
    public GameContainer gc;

    public InputHandler() {

    }
    /**
     * Initialise toute les touches du jeux dans un tableau de boolean
     * @param gc 
     */
    public void load(GameContainer gc) {
        this.gc = gc;
        //gc.getInput().clearKeyPressedRecord();
        Input input = gc.getInput();

        keys[8] = e = input.isKeyPressed(Input.KEY_E);
        keys[12] = i = input.isKeyPressed(Input.KEY_I);
        keys[0] = up = input.isKeyDown(Input.KEY_W);
        keys[3] = left = input.isKeyDown(Input.KEY_A);
        keys[2] = down = input.isKeyDown(Input.KEY_S);
        keys[1] = right = input.isKeyDown(Input.KEY_D);
        keys[5] = rightA = input.isKeyDown(Input.KEY_RIGHT);
        keys[7] = leftA = input.isKeyDown(Input.KEY_LEFT);
        keys[4] = upA = input.isKeyDown(Input.KEY_UP);
        keys[6] = downA = input.isKeyDown(Input.KEY_DOWN);
        keys[13] = input.isKeyPressed(Input.KEY_ESCAPE);
        keys[14] = input.isKeyDown(Input.KEY_LSHIFT);
        keys[15] = input.isKeyPressed(Input.KEY_F);
        keys[16] =mouseLeftButton =input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON);
        

        if (keys[0] || keys[1] || keys[2] || keys[3]) {
            keys[9] = moving = true;

        } else {
            keys[9] = moving = false;
        }
        if (keys[4] || keys[5] || keys[6] || keys[7]) {
            keys[11] = moving2 = true;
        } else {
            keys[11] = moving2 = false;
        }

        if (keys[8]) {
            keys[10] = trigger = true;
        } else {
            keys[10] = trigger = false;
        }

    }

    public InputHandler(boolean up, boolean down, boolean right, boolean left, boolean moving, boolean moving2, boolean e, boolean trigger, boolean i, boolean upA, boolean downA, boolean rightA, boolean leftA) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
        this.moving = moving;
        this.moving2 = moving2;
        this.e = e;
        this.trigger = trigger;
        this.i = i;
        this.upA = upA;
        this.downA = downA;
        this.rightA = rightA;
        this.leftA = leftA;
    }
    private int offset;
    
    /**
     * Regarde si la souris est sur une surface quelconque.
     * @param gc La fenetre du jeux
     * @param shape La forme qui definit la surface ou la souris va entrer.
     * @param player Le joueur de notre monde, ce parametre est utilise pour calculer 
     * les coordonées de la souris selon le monde est non la fenetre
     * @return true si la souris rentre dans une surface donnée, false si la 
     * souris ne rentre pas.
     */
    public boolean isMouseOver(GameContainer gc, Shape shape, Player player) {
       
        double mouseX =  gc.getInput().getMouseX();
        double mouseY =  gc.getInput().getMouseY();
        
        
        mouseX+= (player.getX()-150);
        mouseY+= (player.getY()-150);
        
        
        
        
        
       
        if ((mouseX >= shape.getMinX() && mouseX <= shape.getMaxX())
                && (mouseY >= shape.getMinY() && mouseY <= shape.getMaxY())) {
            
            return true;

        }
        return false;

    }

    public float getMouseX(){
        return gc.getInput().getMouseX();
    }
    public float getMouseY(){
        return gc.getInput().getMouseY();
    }

    


}
