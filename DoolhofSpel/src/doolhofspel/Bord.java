package doolhofspel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
    private final int VELDBREEDTE = 32; // breedte afbeelding in pixels
    private final int VELDHOOGTE = 32; // breedte afbeelding in pixels
    private int stapX;
    private int stapY;
    Doolhof doolhof;

    public Bord(Doolhof doolhof) {
        kaart = new Plattegrond();
        held = new Held();
        valsspeler = new Valsspeler();
        bazooka = new Bazooka();
        vriend = new Vriend();
        addKeyListener(new PijltjesListener());
        setFocusable(true);
        this.doolhof = doolhof;
        //printPosities(); // print alle veldbezettingen met hun indexnr en x en y
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
                changeImage(bazookaX, bazookaY);
            }
            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Helper) {
                System.out.println("Helper!!");
                int helperX = held.getVeldX() + stapX;
                int helperY = held.getVeldY() + stapY;
                changeImage(helperX, helperY);
            }
            if (kaart.getMap(held.getVeldX() + stapX, held.getVeldY() + stapY) instanceof Valsspeler) {
                System.out.println("VALSSPELER!!");
                stappenteller = stappenteller - 20;
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

    public void changeImage(int X, int Y) {
        ArrayList copykaart = kaart.getMapObjects();
        int i = 0;
        while (i < copykaart.size()) {
            Veldbezetting A = kaart.mapObjects.get(i);
            int x = A.getX(i);
            int y = A.getY(i);
            if (x == X && y == Y) {
//                System.out.println("positie in arraylist copykaart " + (i));
//                System.out.println("old image : " + copykaart.get(i));
                copykaart.remove(i);
                Gras gras = new Gras();
                copykaart.add(i, gras);
//                System.out.println("positie in arraylist copykaart " + (i));
//                System.out.println("new image :" + copykaart.get(i));
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
                    changeImage(held.getSchietTargetX(), held.getSchietTargetY());
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
                    changeImage(held.getSchietTargetX(), held.getSchietTargetY());
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
