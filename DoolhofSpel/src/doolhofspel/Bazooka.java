package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public class Bazooka extends Tempbezetting {
    // constructor
    // waarden 1 nog vervangen door variabelenaam
    public Bazooka() {
        ImageIcon img = new ImageIcon("Pictures//bazooka.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
    
}

