package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

public class Gras extends Veldbezetting {
    // waarden 1 nog vervangen door variabelenaam
    public Gras() {
        ImageIcon img = new ImageIcon("Pictures//gras.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
}
