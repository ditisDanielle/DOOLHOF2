package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */

    public class Muur extends Veldbezetting { 
        Bord bord;
        public Muur() {
        ImageIcon img = new ImageIcon("Pictures//brickwall.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
        
//    public void wordtOpgeblazen(){
//        // dit wordt meteen uitgevoerd bij het schieten. beetje overdreven om heen en te verwijzen via methodes?
//    }
        
        
}


