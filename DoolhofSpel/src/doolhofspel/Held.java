package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public class Held extends Veldbezetting{
    boolean bazookaJN;
    String looprichting;
    int schietTargetX;
    int schietTargetY;
    
    public Held(){
        ImageIcon img = new ImageIcon("Pictures//held.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
        bazookaJN = false;
    }
    
    public void setRichting(String richting){
        this.looprichting = richting;
    }
    
    public String getRichting(){
        return this.looprichting;
    }
           
    public int getVeldX(){
        return super.getX(1);
    }
     
    public int getVeldY(){
        return super.getY(1);
    }
    
    public int getSchietTargetX(){
        return this.schietTargetX;
    }
    
    public int getSchietTargetY(){
        return this.schietTargetY;
    }
    
    public void setSchietTargetX(int x){
        this.schietTargetX = x;
    }
    
    public void setSchietTargetY(int y){
        this.schietTargetY = y;
    }
    
    public void lopen (int vx, int vy){
        vx += getX(vx);
        vy += getY(vy);
        super.setPositie(vx,vy);
    }
    
    public void schieten(int X,int Y){
        schietTargetX = X;
        schietTargetY = Y;
    }
    
    public void bazookaPakken(){
        bazookaJN = true;
    }
}
