package doolhofspel;

import java.awt.Image;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public abstract class Veldbezetting {
    private Image afbeelding;
    private int veldX, veldY;
    private Bord bord;
    
    public void setImage(Image afbeelding){
      this.afbeelding = afbeelding;
    }
    
    public Image getImage(){
        return afbeelding = this.afbeelding;
    }
    
    public void setPositie(int x, int y){
        this.veldX = x;
        this.veldY = y;
    }
    
    public int getX(int x){
        return x = this.veldX;
    }
    
    public int getY(int y){
        return y = this.veldY;
    }
    
    public int getX() {
        return this.veldX;
    }

    public int getY() {
        return this.veldY;
    }
    
    public void setBord(Bord bord){
        this.bord = bord;
    }
    
    public Bord getBord(){
        return this.bord;
    }
}
