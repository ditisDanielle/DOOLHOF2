package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

    public class Muur extends Tempbezetting {       
        public Muur() {
        ImageIcon img = new ImageIcon("Pictures//brickwall.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
//        
//    public void wordtOpgeblazen(){
//        //verander plaatje naar gras
//    }
}


