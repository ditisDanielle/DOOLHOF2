package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public class Valsspeler extends Tempbezetting {
    public Valsspeler(){
        // waarden 1 nog vervangen door variabelenaam
        ImageIcon img = new ImageIcon("Pictures//valsspeler.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
          
    public int getVeldX(){
        return super.getX(1);
    }
    
    public int getVeldY(){
        return super.getY(1);
    }
    
}
