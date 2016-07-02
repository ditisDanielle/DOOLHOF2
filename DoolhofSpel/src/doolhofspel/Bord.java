package doolhofspel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */
public class Bord extends JPanel implements ActionListener {

    private Plattegrond kaart;
    private Held held;
    private int stappenteller;
    private Bazooka bazooka;
    private Vriend vriend;
    private Valsspeler valsspeler;
    private Helper helper;
    private Routeveld routeveld;
    private final int VELDBREEDTE = 32; // breedte afbeelding in pixels
    private final int VELDHOOGTE = 32; // breedte afbeelding in pixels
    private int stapX;
    private int stapY;
    Doolhof doolhof;
    private ArrayList<Veldbezetting> veldbezetters;
    private String naam;
    private Muur muur;
    private Gras gras;
    private Checkveld checkveld;

    public Bord(Doolhof doolhof) {
        naam = "ik ben het bord";
        veldbezetters = new ArrayList<>();
        kaart = new Plattegrond(this);
        held = new Held();
        veldbezetters.add(held);
        valsspeler = new Valsspeler();
        veldbezetters.add(valsspeler);
        bazooka = new Bazooka();
        veldbezetters.add(bazooka);
        vriend = new Vriend();
        veldbezetters.add(vriend);
        helper = new Helper(this);
        veldbezetters.add(helper);
        checkveld = new Checkveld();
        muur = new Muur();
        gras = new Gras();

        routeveld = new Routeveld();
        addKeyListener(new PijltjesListener());
        setFocusable(true);
        this.doolhof = doolhof;
        setBordVeldbezetting();

        //printPosities(); // print alle veldbezettingen met hun indexnr en x en y
    }

    @Override
    public String toString() {
        return naam;
    }

    public void setBordVeldbezetting() {
        helper.setBord(this);
        muur.setBord(this);
        valsspeler.setBord(this);
        routeveld.setBord(this);
        vriend.setBord(this);
        gras.setBord(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        // teken het bord
        for (int x = 0; x < 21; x++) {
            for (int y = 0; y < 21; y++) {
                Veldbezetting veld = kaart.getMap(x, y);
                g.drawImage(veld.getImage(), x * VELDBREEDTE, y * VELDHOOGTE, null);
            }
            //plaats held op het bord
            g.drawImage(held.getImage(), held.getVeldX() * 32, held.getVeldY() * 32, null);

        }
    }
    /*
     listener tbv de pijltjestoetsen waarmee je de held kan laten lopen
     */

    public class PijltjesListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            // stap naar rechts of links
            stapX = 0;
            // stap naar boven of naar onderen
            stapY = 0;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_RIGHT:
                    stapX = 1;
                    stapY = 0;
                    held.setRichting("right");
                    break;
                case KeyEvent.VK_LEFT:
                    stapX = -1;
                    stapY = 0;
                    held.setRichting("left");
                    break;
                case KeyEvent.VK_DOWN:
                    stapX = 0;
                    stapY = 1;
                    held.setRichting("down");
                    break;
                case KeyEvent.VK_UP:
                    stapX = 0;
                    stapY = -1;
                    held.setRichting("up");
                    break;
            }
            checkVeld(stapX, stapY);
            repaint();
        }

        /*
         deze methode checkt of het volgende veld geen muur is en of zich er een bazooka, vriend of helper bevindt
         */
        public void checkVeld(int stapX, int stapY) {
            // Hier zorgen  we ervoor dat onze held niet van het bord afloopt bij wijze van
            // want dat veroorzaakt een IndexOutOfBoundsException
            if (held.getVeldX() + stapX == 21) {
                stapX = 0;
            }
            if (held.getVeldY() + stapY == 21) {
                stapY = 0;
            }
            if (held.getVeldX() + stapX < 0) {
                stapX = 0;
            }
            if (held.getVeldY() + stapY < 0) {
                stapY = 0;
            }
            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Held) {
                System.out.println("Niet lopen, je zit bij de start");
            }

            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Vriend) {
                System.out.println("Vriend gevonden!!!");
                eindeSpelKnop();
            }
            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Bazooka) {
                //System.out.println("Found bazooka!");
                activeerSchietknop();
                held.bazookaPakken();
                int bazookaX = held.getVeldX() + stapX;
                int bazookaY = held.getVeldY() + stapY;
                Gras gras = new Gras();
                changeImage(bazookaX, bazookaY, bazooka, gras);
            }
            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Helper) {
                //System.out.println("Helper!!");
                helper.routeTonen(kaart);
                //repaint();
                int helperX = held.getVeldX() + stapX;
                int helperY = held.getVeldY() + stapY;
                Gras gras = new Gras();
                changeImage(helperX, helperY, helper, gras);
                
            }
            
            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Checkveld) {
                int checkveldX = held.getVeldX() + stapX;
                int checkVeldY = held.getVeldY() + stapY;
                helper.routeSchonen(kaart);
                
            }
            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Valsspeler) {
                //System.out.println("VALSSPELER!!");
                stappenteller = stappenteller - 20;
                int valsspelerX = held.getVeldX() + stapX;
                int valsspelerY = held.getVeldY() + stapY;
                Gras gras = new Gras();
                changeImage(valsspelerX, valsspelerY, valsspeler, gras);
            }
            if (!(kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Muur)) {
                held.lopen(stapX, stapY);
                telStap();
            }
        }
          
    }

    /*
     deze methode houdt het aantal genomen stappen bij
     */
    private void telStap() {
        stappenteller++;
        try {
            doolhof.setTeller(stappenteller);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void changeImage(int X, int Y, Veldbezetting veldOud, Veldbezetting veldNieuw) {
        ArrayList copykaart = kaart.getMapObjects();
        int i = 0;
        while (i < copykaart.size()) {
            Veldbezetting V = kaart.mapObjects.get(i);

            int x = V.getX(i);
            int y = V.getY(i);
            if (x == X && y == Y) {
                //copykaart.remove(i);
                if (!(veldNieuw instanceof Routeveld)) {
                    //System.out.println(veldOud + " wordt " + veldNieuw);
                    copykaart.remove(i);
                    Gras gras = new Gras();
                    ImageIcon img = new ImageIcon("Pictures//gras.png");
                    gras.setImage(img.getImage());
                    copykaart.add(i, gras);
                } else{ //veldNieuw instanceof Routeveld 
                    //System.out.println(veldOud + " wordt " + veldNieuw);
                    copykaart.remove(i);
                    
                    Routeveld routeveld = new Routeveld();
                    ImageIcon img = new ImageIcon("Pictures//route.png");
                    routeveld.setImage(img.getImage());
                    copykaart.add(i, routeveld);
                }
                i++;
            } else {
                i++;
            }
        }
        repaint();
    }

    public void activeerSchietknop() {
        doolhof.switchVisibilitySchietknop(true);
    }

    public void deactiveerSchietnkop() {
        doolhof.switchVisibilitySchietknop(false);
    }

    public void activeerSchietActie() {
        // ophalen target
        int tempX = held.getVeldX();
        int tempY = held.getVeldY();
        activeerSchietActie(tempX, tempY);
    }

    /*
     deze methode stelt het schietdoel vast (de eerstvolgende muur)
     */
    public void activeerSchietActie(int tempX, int tempY) {
        // vermijden dat we bij wijze van spreken van het bord af schieten en daardoor
        // een IndexOutOfBoundsException veroorzaken
        if (tempX < 21 || tempX < 21) {
            if (tempX >= 0) {
                if (tempY >= 0) {
                    // ophalen target
                    held.schieten(tempX, tempY);
                    if ((kaart.getMap(held.getSchietTargetX(), held.getSchietTargetY()) instanceof Muur)) {
                        Gras gras = new Gras();
                        Muur muur = new Muur();
                        changeImage(held.getSchietTargetX(), held.getSchietTargetY(), muur, gras);
                        return;
                    }
                    //als target veld != muur, check volgende veld
                    if (!(kaart.getMap(held.getSchietTargetX(), held.getSchietTargetY()) instanceof Muur)) {
                        if (held.getRichting().equals("right")) {
                            tempX++;
                            held.schieten(tempX, tempY);
                        }
                        if (held.getRichting().equals("left")) {
                            tempX--;
                            held.schieten(tempX, tempY);
                        }
                        if (held.getRichting().equals("up")) {
                            tempY--;
                            held.schieten(tempX, tempY);
                        }
                        if (held.getRichting().equals("down")) {
                            tempY++;
                            held.schieten(tempX, tempY);
                        }
                        activeerSchietActie(tempX, tempY);
                    }
                    Gras gras = new Gras();
                    Muur muur = new Muur();
                    changeImage(held.getSchietTargetX(), held.getSchietTargetY(), muur, gras);
                }
            }
        }
    }

    public void eindeSpelKnop() {
        doolhof.eindeSpelKnop();
    }

    /*
     deze methode is een hulpmethode bij het programmeren en print alle veldbezettingen met hun indexnr en x en y 
     activeren in regel 37
     */
    public void printPosities() {
        ArrayList tempprint = kaart.getMapObjects();
        int x = 0;
        int y = 0;
        for (int i = 0; i < tempprint.size(); i++) {
            Veldbezetting A = kaart.mapObjects.get(i);
            x = A.getX(i);
            y = A.getY(i);
            String pos = Integer.toString(x) + Integer.toString(y);
            System.out.println(pos);
//            int index = Integer.parseInt(pos);
            System.out.println("pos :" + i + "/stringxy " + pos + " object= " + tempprint.get(i) + " x: " + x + "y: " + y);
        }
    }

}


//    public void routeVerdwijnen(){
//        
//        System.out.println("route verdijnen");
//        ArrayList routelist = routeKaart.getRouteObjects();
//        // arraylist elementen aan set toewijzen
//        Set<Veldbezetting> routeSet = new HashSet<>();
//        for (Object veld : routelist) {
//            routeSet.add((Veldbezetting) veld);
//        }
//        //set doorlopen
//        Iterator<Veldbezetting> iter = routeSet.iterator();
//        while (iter.hasNext()) {
//            Veldbezetting A = iter.next();
//            if (A instanceof RouteVeld) {
//                //als routeveld, haal x en y
//                int x = A.getX();
//                int y = A.getY();
//                
//                RouteVeld routeveld = new RouteVeld();
//                Gras gras = new Gras();
//
//                changeImage(x, y, routeveld, gras); 
//
//            }
//        }
//
//public void changeImage(int X, int Y) {
//        ArrayList copykaart = kaart.getMapObjects();
//        int i = 0;
//        while (i < copykaart.size()) {
//            Veldbezetting A = kaart.mapObjects.get(i);
//            int x = A.getX(i);
//            int y = A.getY(i);
//            if (x == X && y == Y) {
////                System.out.println("positie in arraylist copykaart " + (i));
////                System.out.println("old image : " + copykaart.get(i));
//                copykaart.remove(i);
//                Gras gras = new Gras();
//                copykaart.add(i, gras);
////                System.out.println("positie in arraylist copykaart " + (i));
////                System.out.println("new image :" + copykaart.get(i));
//            } else {
//                i++;
//            }
//        }
//        repaint();
//    }
//
////
