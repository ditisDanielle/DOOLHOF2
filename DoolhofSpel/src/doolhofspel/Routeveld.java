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
public class Routeveld extends Tempbezetting{
    Bord bord;
    
    public Routeveld() {
        ImageIcon img = new ImageIcon("Pictures//gras.png");  //("Pictures//route.png")
        super.setImage(img.getImage());
        super.setPositie(1,1);
    }
    
    
    public void verdwijnen() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
