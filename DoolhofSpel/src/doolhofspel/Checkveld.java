/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhofspel;

import javax.swing.ImageIcon;

/**
 *
 * @author Danielle
 */
class Checkveld extends Tempbezetting {
    Bord bord;
    public Checkveld() {
        ImageIcon img = new ImageIcon("Pictures//gras.png");
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
    
}
