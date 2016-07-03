package doolhofspel;

import java.io.ByteArrayInputStream;
import java.util.Scanner;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Groep 7: Danielle & Agnita
 */
public class BordTest {
    
    Bord bord;
    Held held;
    Doolhof doolhof;
    Plattegrond kaart;
    Veldbezetting veldbezetting;
    
    @Before
    public void setUp(){
        doolhof = new Doolhof();
        bord = new Bord(doolhof);
        held = new Held(bord);
        kaart = new Plattegrond(bord);
        
        
        
    }

    
    
    @Test
    public void changeImage1() {
        
        System.out.println("\n\nTEST changeImage(int X, int Y, Veldbezetting nieuwVeld");
        System.out.println("Testgeval 1");
    int X = 1;
    int Y = 6;
        System.out.println("Positie oude veldbezetting (x,y) = " + X +","+ Y);
    
     // 1,3 is een Muur - index in ArrayList mapObjects voor deze positie = 36
    Veldbezetting veldNieuw = new Gras();
    int pos = 36;
        System.out.println("Bijbehorende index in ArrayList mapObjects (veldbezettingen) = " + pos);
    
    Veldbezetting oud = kaart.mapObjects.get(pos);
        System.out.println("Oude veldbezetting = " + oud);
    Veldbezetting expResult = veldNieuw;
    kaart.mapObjects.remove(pos);
    kaart.mapObjects.add(pos, veldNieuw);
        System.out.println("Nieuwe veldbezetting = " + veldNieuw);
    Veldbezetting nieuw = kaart.mapObjects.get(pos);    
    
         
        Veldbezetting result = nieuw;
        assertEquals(expResult, result);
        System.out.println("Verwacht resultaat: "+  expResult + "\nWerkelijk resultaat: " + result);

}
    
    @Test
    public void changeImage2() {
        
        System.out.println("\n\nTEST changeImage(int X, int Y, Veldbezetting nieuwVeld");
        System.out.println("Testgeval 2");
    int X = 2;
    int Y = 16;
        System.out.println("Positie oude veldbezetting (x,y) = " + X +","+ Y);
    
     // 2,16 is een Gras - index in ArrayList mapObjects voor deze positie = 58
    Veldbezetting veldNieuw = new Gras();
    int pos = 58;
        System.out.println("Bijbehorende index in ArrayList mapObjects (veldbezettingen) = " + pos);
    
    Veldbezetting oud = kaart.mapObjects.get(pos);
        System.out.println("Oude veldbezetting = " + oud);
    Veldbezetting expResult = veldNieuw;
    kaart.mapObjects.remove(pos);
    kaart.mapObjects.add(pos, veldNieuw);
        System.out.println("Nieuwe veldbezetting = " + veldNieuw);
    Veldbezetting nieuw = kaart.mapObjects.get(pos);    
    
         
        Veldbezetting result = nieuw;
        assertEquals(expResult, result);
        System.out.println("Verwacht resultaat: "+  expResult + "\nWerkelijk resultaat: " + result);

}
    @Test
    public void changeImage3() {
        
        System.out.println("\n\nTEST changeImage(int X, int Y, Veldbezetting nieuwVeld");
        System.out.println("Testgeval 3");
    int X = 8;
    int Y = 14;
        System.out.println("Positie oude veldbezetting (x,y) = " + X +","+ Y);
    
     // 2,16 is een Routeveld - index in ArrayList mapObjects voor deze positie = 182
    Veldbezetting veldNieuw = new Routeveld();
    int pos = 182;
        System.out.println("Bijbehorende index in ArrayList mapObjects (veldbezettingen) = " + pos);
    
    Veldbezetting oud = kaart.mapObjects.get(pos);
        System.out.println("Oude veldbezetting = " + oud);
    Veldbezetting expResult = veldNieuw;
    kaart.mapObjects.remove(pos);
    kaart.mapObjects.add(pos, veldNieuw);
        System.out.println("Nieuwe veldbezetting = " + veldNieuw);
    Veldbezetting nieuw = kaart.mapObjects.get(pos);    
    
         
        Veldbezetting result = nieuw;
        assertEquals(expResult, result);
        System.out.println("Verwacht resultaat: "+  expResult + "\nWerkelijk resultaat: " + result);

}
}






