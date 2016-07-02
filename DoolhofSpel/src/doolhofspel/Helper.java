package doolhofspel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.ImageIcon;

/**
 *
 * @author Danielle en Agnita (Groep 7)
 */
public class Helper extends Tempbezetting {

    Bord bord;
    Boolean routeGetoond; 

    public Helper(Bord bord) {
        ImageIcon img = new ImageIcon("Pictures//helper.png");
        super.setImage(img.getImage());
        super.setPositie(12, 6);
        //this.bord = bord;
        //System.out.println("bord van helper:" + bord);
        routeGetoond = false;
    }

    // waarden 1 nog vervangen door variabelenaam
    public int getVeldX() {
        return super.getX(1);
    }

    public int getVeldY() {
        return super.getY(1);
    }

    @Override
    public void setBord(Bord bord) {
        this.bord = bord;
    }

    @Override
    public Bord getBord() {
        return this.bord;
    }

    public void routeTonen(Plattegrond kaart) {
        //System.out.println("route tonen");
        ArrayList routelist = kaart.getMapObjects();
        // arraylist elementen aan set toewijzen
        Set<Veldbezetting> routeSet = new HashSet<>();
        for (Object veld : routelist) {
            routeSet.add((Veldbezetting) veld);
        }
        //set doorlopen
        Iterator<Veldbezetting> iter = routeSet.iterator();
        while (iter.hasNext()) {
            Veldbezetting A = iter.next();
            if (A instanceof Routeveld) {
                //als routeveld, haal x en y
                int x = A.getX();
                int y = A.getY();

                Routeveld routeveld = new Routeveld();
                Gras gras = new Gras();

                bord.changeImage(x, y, gras, routeveld);

            }
            //repaint();
        }
        routeGetoond = true;
        bord.repaint();
    }

    void routeSchonen(Plattegrond kaart) {
        if (routeGetoond == false){
            System.out.println(routeGetoond);
            return;
        }else{
 //System.out.println("route tonen");
        ArrayList routelist = kaart.getMapObjects();
        // arraylist elementen aan set toewijzen
        Set<Veldbezetting> routeSet = new HashSet<>();
        for (Object veld : routelist) {
            routeSet.add((Veldbezetting) veld);
        }
        //set doorlopen
        Iterator<Veldbezetting> iter = routeSet.iterator();
        while (iter.hasNext()) {
            Veldbezetting A = iter.next();
            if (A instanceof Routeveld) {
                //als routeveld, haal x en y
                int x = A.getX();
                int y = A.getY();

                Routeveld routeveld = new Routeveld();
                Gras gras = new Gras();

                bord.changeImage(x, y, routeveld, gras);

            }
            //repaint();
        }
        bord.repaint(); 
        }}
}
