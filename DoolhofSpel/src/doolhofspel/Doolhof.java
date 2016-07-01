package doolhofspel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Agnita & Danielle (Groep 7)
 */
class Doolhof implements ActionListener {

    int buttonSizeHeight = 1; // later FINAL maken
    int buttonSizesWidth = 3;// later FINAL maken
    JFrame spelframe;
    JPanel gamepanel;
    JPanel buttonpanel;
    JPanel oostpanel;
    JPanel dummy1;
    JPanel dummy2;
    JPanel dummy3;
    JPanel dummy4;
    JPanel centerdummy;
    JButton reset;
    JButton schietKnop;
    JButton eindeSpelKnop;
    JButton teller;
    JButton tellerlabel;
    Bord bord;

    public Doolhof() {
        // maak een gameframe
        bord = new Bord(this);
        spelframe = new JFrame();
        spelframe.setTitle("Zoek je vriend!");

        spelframe.setExtendedState(Frame.MAXIMIZED_BOTH);
        spelframe.setLocationRelativeTo(null);
        spelframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // set layouts
        // game panel bevat het bord
        // gamepanel bevat oostpanel 
        // oostpanel bevat buttonpanel
        // buttonpanel bevat dummies 1-north,2-south,3-west en 4-west en een centerdummy
        gamepanel = new JPanel(new GridLayout(1, 2));
        buttonpanel = new JPanel(new BorderLayout());
        oostpanel = new JPanel(new BorderLayout());

        //Resetbutton
        reset = new JButton();
        reset.setSize(buttonSizeHeight, buttonSizesWidth);
        reset.setText("Start spel opnieuw");
        reset.addActionListener(this);
        reset.addActionListener(new ResetListener());
        reset.setFocusable(false);

        // Schietknop , niet zichtbaar tot we een bazooka vinden
        schietKnop = new JButton();
        schietKnop.setSize(buttonSizeHeight, buttonSizesWidth);
        schietKnop.setText("Schieten!");
        schietKnop.setVisible(false);
        schietKnop.setFocusable(false);
        schietKnop.addActionListener(this);
        schietKnop.addActionListener(new SchietListener());

        // eindeSpelKnop, niet  zichtbaar tot we onze vriend gevonden hebben
        eindeSpelKnop = new JButton();
        eindeSpelKnop.setFocusable(false);
        eindeSpelKnop.setBackground(Color.pink);
        eindeSpelKnop.setVisible(false);
        eindeSpelKnop.setText("VRIEND GEVONDEN!!!");
        
        // Tellerlabel
        tellerlabel = new JButton();
        tellerlabel.setText("Aantal gezette stappen:");

        //  Teller
        teller = new JButton();
        teller.setText("0");

        // dummy panels toevoegen aan buttonpanel
        dummy1 = new JPanel();
        dummy1.setPreferredSize(new Dimension(50, 300));
        buttonpanel.add(dummy1, BorderLayout.NORTH);
        dummy2 = new JPanel();
        dummy2.setPreferredSize(new Dimension(50, 300));
        buttonpanel.add(dummy2, BorderLayout.SOUTH);
        dummy3 = new JPanel();
        dummy3.setPreferredSize(new Dimension(50, 350));
        buttonpanel.add(dummy3, BorderLayout.EAST);
        dummy4 = new JPanel();
        dummy4.setPreferredSize(new Dimension(50, 350));
        buttonpanel.add(dummy4, BorderLayout.WEST);
        centerdummy = new JPanel(new BorderLayout());
        centerdummy.setPreferredSize(new Dimension(50, 350));
        //centerpanel in buttonpanel inrichten
        centerdummy.add(reset, BorderLayout.NORTH);
        centerdummy.add(schietKnop, BorderLayout.SOUTH);
        centerdummy.add(tellerlabel, BorderLayout.WEST);
        centerdummy.add(teller, BorderLayout.CENTER);
        centerdummy.add(eindeSpelKnop, BorderLayout.EAST);
        
        buttonpanel.add(centerdummy, BorderLayout.CENTER);

        // alle panels toevoegen aan het uiteindelijke gamepanel
        gamepanel.add(bord);
        //System.out.println("aanmaken bord in doolhof regel 104: " + new Bord(this).toString());
        gamepanel.add(oostpanel);
        oostpanel.add(buttonpanel, BorderLayout.CENTER);

        // als laatste voegen we de gamepanel toe aan de gameframe en zorgen we dat het zichtbaar is
        spelframe.add(gamepanel);
        spelframe.setVisible(true);
    }

    public void setTeller(int aantal) {
        String telling = Integer.toString(aantal);
        //System.out.println(telling);
        teller.setText(telling);
    }

    public void switchVisibilitySchietknop(boolean b) {
        if (b == true) {
            schietKnop.setVisible(true);
        } else if (b == false) {
            schietKnop.setVisible(true);
        }
    }
    
    public void eindeSpelKnop() {
        eindeSpelKnop.setVisible(true);
    }

    // deze komt mee vanwege implements ActionListener
    @Override 
    public void actionPerformed(ActionEvent e) {
        return;
    }

    // de resetlistener, deze moet het level gaan resetten
    private class ResetListener implements ActionListener {
        public ResetListener() {

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("resetlistener geactiveerd");
            Doolhof doolhof = new Doolhof();
            gamepanel.add(new Bord(doolhof));
        }
    }

    private class SchietListener implements ActionListener {
        public SchietListener() {

        }
        @Override
        public void actionPerformed(ActionEvent e) {
            //System.out.println("schietknoplistener geactiveerd");
            bord.activeerSchietActie();
        }
    }

}
