package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public class Vriend extends Veldbezetting {
    Bord bord;
    public Vriend(){
        ImageIcon img = new ImageIcon("Pictures//vriend.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
    
    public void juichen(){
        
    }

}

