package doolhofspel;

import java.awt.Image;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public abstract class Tempbezetting extends Veldbezetting{
    public Tempbezetting() {
    
    }
    
    @Override
    public void setImage(Image tempAfbeelding){
      super.setImage(tempAfbeelding);
    }
    
    @Override
    public Image getImage(){
        return super.getImage();
    }
    
    @Override
     public void setPositie(int x, int y){
        super.setPositie(x,y);
    }
    
    @Override
    public int getX(int x){
        return x = super.getX(x);
    }
    
    @Override
    public int getY(int y){
        return y = super.getY(y);
    }
    
    public void verdwijnen(int veldX,int veldY) {
        
    }
}
