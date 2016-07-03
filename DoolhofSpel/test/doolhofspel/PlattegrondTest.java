/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package doolhofspel;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Danielle
 */
public class PlattegrondTest {
      @Test
    public void testGetMapgrootte() {
        Doolhof doolhof = new Doolhof();
        System.out.println("Test of method getMapgrootte in class Plattegrond.");
        Bord bord = new Bord(doolhof);
        Plattegrond kaart = new Plattegrond(bord);
        int expResult = 21;
        int result = kaart.getMapgrootte();
        assertEquals(expResult, result);
        System.out.println("Het resultaat is " + result + " en het verwachte resultaat is " + expResult + ".");
    }
}
