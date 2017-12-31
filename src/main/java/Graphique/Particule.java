/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphique;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;

/**
 *
 * @author Wail
 */
public class Particule{
        private ParticleSystem spellsParticule;
	private ConfigurableEmitter cEmitter;

    public float x, y;
        
        
    public Particule() throws IOException {
        loadParticles();
    }
    public void loadParticles() {
            try {
                spellsParticule = new ParticleSystem("res/text/particule/particle.png",10000);
                
                cEmitter = ParticleIO.loadEmitter("res/text/particule/emitter.xml");
                spellsParticule.addEmitter(cEmitter);
                spellsParticule.setBlendingMode(ParticleSystem.BLEND_ADDITIVE);
            } catch (IOException ex) {
                Logger.getLogger(Particule.class.getName()).log(Level.SEVERE, null, ex);
            }
                
    }
    public void render(){
        spellsParticule.render();
    }
    public void update(int d){
        spellsParticule.update(d);
    }

    public ParticleSystem getSpellsParticule() throws IOException{
        return spellsParticule;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
    
    
}
